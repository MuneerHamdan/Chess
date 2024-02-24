package chess;

public class Piece{

    private Tile position;
    private char file;
    private int rank;
    private char color;
    private char type;
    private String combined;

    Piece(){

    }

    Piece(Tile position, char color, char type){
        this.position = position;
        this.color = color;
        this.type = type;
        this.combined = "" + color + type;
    }
    Piece(char file, int rank, char color, char type){
        this.file = file;
        this.rank = rank;
        this.color = color;
        this.type = type;
        this.combined = "" + color + type;
    }

    Piece(Tile position, String combined){
        this.position = position;
        this.combined = combined;
    }
    Piece(char file, int rank, String combined){
        this.file = file;
        this.rank = rank;
        this.combined = combined;
    }

    void setPiece(char color, char type){
        this.color = color;
        this.type = type;
        this.setCombined(this.color, this.type);
    }

    void setCombined(char color, char type){
        this.combined = "" + color + type;
    }

    void setCombined(String combined){
        this.combined = combined;
    }

    String getpiece(){
        return this.combined;
    }

    void setPosition(char a, int b){
        this.file = a;
        this.rank = b;
    }
}