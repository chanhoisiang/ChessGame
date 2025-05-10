import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Tzeryu
public class Controller {
    private Chess chess;
    private View view;
    
    // Constructor
    public Controller(Chess chess, View view){
        this.chess = chess;
        this.view = view;
        this.chess.init(this.view.loadStatus());
        this.view.setButtonListener(new ButtonListener());
        this.view.setWindowAdapter(new ClosingListener());
    }
    
    // Updates the view
    public void updateView(){
        updateGrid();//Tzeryu
        this.view.viewing();
    }
    
     // Updates the grid
    private void updateGrid(){
        //Tzeryu
        view.updateGrid(chess.getTurnBlue(), chess.getPieces(), chess.getGameOver());
    }
    
    // Listener for button clicks
    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int buttonNum = Integer.parseInt(e.getActionCommand());
            chess.selectPiece(buttonNum);
            view.setValidMove(chess.getValid(buttonNum));//JieSheng
            updateGrid();
            if(chess.getGameOver()){
                chess = new Chess();
                chess.init(false);
                updateGrid();
            }
        }
    }
    
    // Listener for window closing events
    class ClosingListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            // Kuangzhi
            // Save file before exiting
            int choice = JOptionPane.showConfirmDialog(
                    view,
                    "Do you want to save changes before exiting?",
                    "Confirm Exit",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (choice == JOptionPane.YES_OPTION) {
                chess.saveFile();
                view.dispose(); // Close the frame
            } else if (choice == JOptionPane.NO_OPTION) {
                view.dispose(); // Close the frame without saving
            }
        }
    }
}