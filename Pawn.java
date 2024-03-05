package chess;

import java.util.ArrayList;

public class Pawn extends Piece{
    public boolean canMove(String initial, String destination, PieceType type, boolean desthaspiece){
        int fileI = initial.charAt(0) - '0';
        int rankI = initial.charAt(1) - '0';
        int fileD = destination.charAt(0) - '0';
        int rankD = destination.charAt(1) - '0';
        
        switch(type){
            case WP:
                if (!desthaspiece && rankI == 2 && rankD - rankI > 0 && rankD - rankI <= 2 && fileD == fileI){
                    return true;
                }
                else if (rankI != 2 && rankD - rankI == 1 && fileD == fileI && !desthaspiece){
                    return true;
                }
                else if (desthaspiece && ((fileD - fileI == 1) || (fileD - fileI == -1)) && rankD - rankI == 1){
                    return true;
                }
                else if (((fileD - fileI == 1) || (fileD - fileI == -1)) && rankD - rankI == 1){
                    return true;
                }
                else {
                    return false;
                }
            case BP:
                if(!desthaspiece && rankI == 7 && rankD - rankI < 0 && rankD - rankI >= -2 && fileD == fileI){
                    return true;
                }
                else if(rankI != 7 && rankD - rankI == -1 && fileD == fileI && !desthaspiece){
                    return true;
                }
                else if (desthaspiece && ((fileD - fileI == 1) || (fileD - fileI == -1)) && rankD - rankI == -1){
                    return true;
                }
                else if (((fileD - fileI == 1) || (fileD - fileI == -1)) && rankD - rankI == -1){
                    return true;
                }
                else {
                    return false;
                }
            default:
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


        if ((Math.abs(rankd - ranki) <= 2) && filei == filed){
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
