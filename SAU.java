public class SAU extends Piece { //Hoisiang
    
    private int[][] moves = {{-1, -1},{-1, 0},{-1, 1},{0,-1},{0, 1},{1,-1},{1, 0},{1, 1}};
    
    public SAU(int pos, boolean isBlue){
        super(pos, isBlue);
        setType("SAU");
        if(isBlue){
            setIcon("./img/blueSAU.png");
        }else{
            setIcon("./img/redSAU.png");
        }
    }
    
    @Override
    public void updateValid(Piece[] p){
        getValid().clear();
        int[] currentPos = numToPos(getPos()); // Convert 1D position to 2D coordinates

        for (int[] move : moves) {
            int newX = currentPos[0] + move[0];
            int newY = currentPos[1] + move[1];

            if (newX < 0 || newX >= 8 || newY < 0 || newY >= 5) {
                continue; // Skip moves that go out of bounds
            }

            int validPos = newX * 5 + newY; // Convert back to 1D position
            if (p[validPos] == null || checkEnemy(p[validPos])) {
                getValid().add(validPos); 
            }
        }
    
    }
}