package chess;

public class Empty extends Piece{

    private String type = "##";
    private char file;
    private int rank;

    Empty(char file, int row) {
        super(file, row);
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    void move() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

    @Override
    void swap() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'swap'");
    }    
}