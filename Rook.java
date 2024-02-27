package chess;

import java.util.ArrayList;

public class Rook extends Piece{
    
    private boolean moved = false;
    Rook(){}
    

    Rook(PieceFile file, int rank, PieceType pieceType){
        this.pieceFile = file;
        this.pieceRank = rank;
        this.pieceType = pieceType;
    }

    public boolean canMove(String initial, String destination, PieceType type, boolean desthaspiece){
        int fileI = initial.charAt(0) - '0';
        int rankI = initial.charAt(1) - '0';
        int fileD = destination.charAt(0) - '0';
        int rankD = destination.charAt(1) - '0';
        
        if(fileI == fileD || rankI == rankD){
            moved = true;
            return true;
        }
        else{
            return false;
        }
    }
    
    public void move(String p, String m, ArrayList<ReturnPiece> l){
        super.move(p, m, l);
    }

    public boolean canTake(String firstSquare, String secondSquare, ArrayList<ReturnPiece> p){
        String diag = firstSquare;
		
		String diag2 = diag;
		

        

        char filei = diag.charAt(0);
        char filed = secondSquare.charAt(0);
        char ranki = diag.charAt(1);
        char rankd = secondSquare.charAt(1);


        if (ranki == rankd && filei < filed){
            while (filei < filed){
                filei++;
                diag2 = "" + filei + ranki;
                for (ReturnPiece z : p){
                    String s = z.toString();
                    String[] sl = s.split(":");
                    if(sl[0].equalsIgnoreCase(diag2)){
                        return false;
                    }
                }
            }
            return true;
        }
        else if (ranki == rankd && filei > filed){
            while (filei > filed){
                filei--;
                diag2 = "" + filei + ranki;
                for (ReturnPiece z : p){
                    String s = z.toString();
                    String[] sl = s.split(":");
                    if(sl[0].equalsIgnoreCase(diag2)){
                        return false;
                    }
                }
            }
            return true;
        }
        else if (filei == filed && ranki < rankd){
            while (ranki < rankd){
                ranki++;
                diag2 = "" + filei + ranki;
                for (ReturnPiece z : p){
                    String s = z.toString();
                    String[] sl = s.split(":");
                    if(sl[0].equalsIgnoreCase(diag2)){
                        return false;
                    }
                }
            }
            return true;
        }
        else if (filei == filed && ranki > rankd){
            while (ranki > rankd){
                ranki--;
                diag2 = "" + filei + ranki;
                for (ReturnPiece z : p){
                    String s = z.toString();
                    String[] sl = s.split(":");
                    if(sl[0].equalsIgnoreCase(diag2)){
                        return false;
                    }
                }
            }
            return true;
        }

        return false;
    }

    public void take(){

    }
}
