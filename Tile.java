package chess;

public class Tile extends Board{
    
    private char file;
    private int rank;
    private Piece piece;

    Tile(){
        // position = new Str;
    }

    void setposition(char f, int r){
        this.file = f;
        this.rank = r;
    }

    char getfile(){
        return this.file;
    }

    int getrank(){
        return this.rank;
    }

    void setpiece(Piece piece){
        this.piece = piece;
    }

    Piece getPiece(){
        return this.piece;
    }

    void setType(char color, char type){
        this.piece.setPiece(color, type);
    }

    void setType(String combined){
        this.piece.setCombined(combined);
    }

    String getType(){
        return this.piece.getpiece();
    }
}
