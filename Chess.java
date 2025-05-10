import java.util.*;
import javax.swing.*;
import java.awt.Image;
import java.awt.Font;

public class Chess {
    private Piece[] pieces = new Piece[40];
    private int pieceNum;
    private boolean selected= false;
    private boolean turnBlue;
    private boolean gameOver= false;
    private int counter = 0;
    private File file;
    
     // Constructor
    public Chess(){
         file = new File();
    }
    
    // Initializes the game, loading from a file or creating new pieces
    public void init(boolean loadStatus){
        //Hoisiang
        if(loadStatus){
            turnBlue = file.loadFile(pieces);
        }else{
            turnBlue = true; 
            file.clearFile();
            pieces = newPieces();
        }
    }
    
    // Creates and initializes a new set of pieces
    public static Piece[] newPieces(){
        //Hoisiang
        Piece[] newPieces = new Piece[40];
        newPieces[0] = new TOR(0, false);
        newPieces[1] = new BIZ(1, false);
        newPieces[2] = new SAU(2, false);
        newPieces[3] = new BIZ(3, false);
        newPieces[4] = new XOR(4, false);
        for(int i = 5; i < 10; i++){
            newPieces[i] = new RAM(i,false);
        }
        newPieces[35] = new TOR(35, true);
        newPieces[36] = new BIZ(36, true);
        newPieces[37] = new SAU(37, true);
        newPieces[38] = new BIZ(38, true);
        newPieces[39] = new XOR(39, true);
        for(int j = 30; j < 35; j++){
            newPieces[j] = new RAM(j,true);
        }
        return newPieces;
    }
    
    // Handles the selection and movement of pieces
    public void selectPiece(int num){
        if(selected){
            //JieSheng
            if(pieces[pieceNum].checkValid(num)){
                pieces[pieceNum].setPos(num);
                if(pieces[num] != null && pieces[num].getType()=="SAU"){
                    gameOver = true;//Hoisiang
                }
                pieces[num] = pieces[pieceNum];
                pieces[pieceNum] = null;
                counter++;
                turnBlue = !turnBlue;
                if (counter == 2) {//Tzer yu
                    switchTORXOR();
                    counter = 0;
                }
                pieces[num].getValid().clear();
            }
        }
        this.pieceNum = num;//JieSheng                                         
        if(pieces[pieceNum] != null && pieces[pieceNum].getBlue() == turnBlue){
            selected = true;
            pieces[pieceNum].updateValid(pieces);
        }else{
            selected = false;
        }
    }
    
    // Switch TOR to XOR
    private void switchTORXOR() {//Tzer Yu
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i] instanceof TOR) {
                pieces[i] = new XOR(pieces[i].getPos(), pieces[i].getBlue());
            } else if (pieces[i] instanceof XOR) {
                pieces[i] = new TOR(pieces[i].getPos(), pieces[i].getBlue());
            }
        }
    }
    
    // Returns the array of pieces
    public Piece[] getPieces() {
        return pieces;
    }

    // Returns the color of turn 
    public boolean getTurnBlue() {
        return turnBlue;
    }

    // Checks if the game is over
    public boolean getGameOver() {
        return gameOver; //Hoisiang
    }
    
    // Gets the valid moves for a piece at the given position
    public ArrayList<Integer> getValid(int buttonNum){//JieSheng
        if(pieces[buttonNum] == null){
            return null;
        }else{
            return pieces[buttonNum].getValid();
        }
    }
    
     // Saves the current game state to a file
    public void saveFile(){
        file.saveFile(pieces, turnBlue);
    }
}