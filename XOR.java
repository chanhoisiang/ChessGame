import java.util.*;

public class XOR extends Piece { //Tzeryu

    boolean reversed = false;

    public XOR(int pos, boolean isBlue){
        super(pos, isBlue);
        setType("XOR");
        if(isBlue){
            setIcon("./img/blueXOR.png");
            reversed = true;
        }else{
            setIcon("./img/redXOR.png");
        }
    }

    @Override
    public void updateValid(Piece[] p){
        getValid().clear();
        int[] directions = {-4, 4, -6, 6};
        for (int dir : directions) {
            int currentPos = getPos();
            int newCurrent = currentPos;
            for (int i = 1; i <= 4; i++) {
                int newPos = currentPos + dir * i;
                int tempRow = newPos/5 - newCurrent/5;
                if (newPos < 0 || newPos >= 40) { // Out of bounds
                    break;
                }
                if (tempRow != 1 && tempRow != -1)
                {
                    break;
                }
                if (p[newPos] == null) { 
                    getValid().add(newPos);
                    newCurrent = newPos;
                } else if (checkEnemy(p[newPos])) {
                    getValid().add(newPos);
                    break;
                } else { // Own piece blocks the way
                    break;
                }
            }
            newCurrent = -1;
        }
    }
}