package chess;

public class Tile{
    
    private Piece piece;
    private char file;
    private int rank;

    Tile(char file, int rank){
        this.piece = new Empty(file, rank);
    }

    void setType(String type){
        this.piece.setType(type);
    }

    char getfile(){
        return this.file;
    }

    int getrank(){
        return this.rank;
    }

    String getType(){
        return this.piece.getType();
    }

    void swap(){
        
    }
}
