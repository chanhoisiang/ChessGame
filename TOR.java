import java.util.*;

public class TOR extends Piece { //Tzeryu

    boolean reversed = false;

    public TOR(int pos, boolean isBlue){
        super(pos, isBlue);
        setType("TOR");
        if(isBlue){
            setIcon("./img/blueTOR.png");
            reversed = true;
        }else{
            setIcon("./img/redTOR.png");
        }
    }

    @Override
    public void updateValid(Piece[] p){
        getValid().clear();
        int currentPos = getPos();
        int newPos;
        for (int u = 0; u < 4; u++)
        {
            for (int i = 1; i < 8; i++) { // Maximum of 8 rows upward
                if (u == 0)
                {   newPos = currentPos - i * 5;
                    if (newPos < 0) break; // Out of bounds (top of the board)
                }
                else if (u == 1)
                {
                    newPos = currentPos + i * 5;
                    if (newPos > 39) break; // Out of bounds (bottom of the board)
                }
                else if (u == 2)
                {
                    newPos = currentPos + i;
                    if (newPos < 0 || (newPos / 5) != (currentPos / 5)) break; // Out of bounds
                }
                else if (u == 3)
                {
                    newPos = currentPos - i;
                    if (newPos < 0 || (newPos / 5) != (currentPos / 5)) break; // Out of bounds
                }
                else break;
                if (p[newPos] == null) {
                    getValid().add(newPos);
                } else if (checkEnemy(p[newPos])) {
                    getValid().add(newPos); // Can capture enemy
                    break; // Stop further movement in this direction
                } else {
                    break; // Own piece blocks the path
                }
            }
        }    
    }
}