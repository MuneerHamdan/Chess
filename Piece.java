package chess;

public abstract class Piece {

    String type = "##";
    char file;
    int row;

    Piece(char file, int row){
        this.file = file;
        this.row = row;
    }

    Piece makePiece(){
        return new Pawn();
    }

    abstract void move();

    String getType(){
        return this.type;
    }

    void setType(String type){
        this.type = type;
    }

    String getTile(){
        return "" + file + row;
    }

    abstract void swap();
}