package chess;

import java.util.ArrayList;

public class Queen extends Piece{

    Queen(){}
    

    Queen(PieceFile file, int rank, PieceType pieceType){
        this.pieceFile = file;
        this.pieceRank = rank;
        this.pieceType = pieceType;
    }

    public boolean canMove(String initial, String destination, PieceType type, boolean desthaspiece){
        int fileI = initial.charAt(0) - '0';
        int rankI = initial.charAt(1) - '0';
        int fileD = destination.charAt(0) - '0';
        int rankD = destination.charAt(1) - '0';
        
        if (fileI == fileD || rankI == rankD ||  Math.abs(fileI-fileD) == Math.abs(rankI-rankD) ){
            return true;
        }
        else {
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
        else if (Math.abs(filed - filei) == Math.abs(rankd - ranki)){
            while (filei < filed && ranki < rankd){
                filei++;
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
            while (filei > filed && ranki > rankd){
                filei--;
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
            while (filei < filed && ranki > rankd){
                ranki--;
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
            while (filei > filed && ranki < rankd){
                ranki++;
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

        return false;
    }

    public boolean checkKing(String queenSquare, String kingSquare, ArrayList<ReturnPiece> p){

        String diag = queenSquare;
            
            String diag2 = kingSquare;

            
            
    
            
    
            char filei = diag.charAt(0);
            char filed = kingSquare.charAt(0);
            char ranki = diag.charAt(1);
            char rankd = kingSquare.charAt(1);
    
    
            if (ranki == rankd && filei < filed){
                while (filei < filed){
                    filei++;
                    diag2 = "" + filei + ranki;
                    for (ReturnPiece z : p){
                        String s = z.toString();
                        String[] sl = s.split(":");
                        if(sl[1].equalsIgnoreCase("WK") || sl[1].equalsIgnoreCase("BK")){
                            return true;
                        }
                    }
                }
                return false;
            }
            else if (ranki == rankd && filei > filed){
                while (filei > filed){
                    filei--;
                    diag2 = "" + filei + ranki;
                    for (ReturnPiece z : p){
                        String s = z.toString();
                        String[] sl = s.split(":");
                        if(sl[1].equalsIgnoreCase("WK") || sl[1].equalsIgnoreCase("BK")){
                            return true;
                        }
                    }
                }
                return false;
            }
            else if (filei == filed && ranki < rankd){
                while (ranki < rankd){
                    ranki++;
                    diag2 = "" + filei + ranki;
                    for (ReturnPiece z : p){
                        String s = z.toString();
                        String[] sl = s.split(":");
                        if(sl[1].equalsIgnoreCase("WK") || sl[1].equalsIgnoreCase("BK")){
                            return true;
                        }
                    }
                }
                return false;
            }
            else if (filei == filed && ranki > rankd){
                while (ranki > rankd){
                    ranki--;
                    diag2 = "" + filei + ranki;
                    for (ReturnPiece z : p){
                        String s = z.toString();
                        String[] sl = s.split(":");
                        if(sl[1].equalsIgnoreCase("WK") || sl[1].equalsIgnoreCase("BK")){
                            return true;
                        }
                    }
                }
                return false;
            }
            else if (Math.abs(filed - filei) == Math.abs(rankd - ranki)){
                while (filei < filed && ranki < rankd){
                    filei++;
                    ranki++;
                    diag2 = "" + filei + ranki;
                    for (ReturnPiece z : p){
                        String s = z.toString();
                        String[] sl = s.split(":");
                        if(sl[1].equalsIgnoreCase("WK") || sl[1].equalsIgnoreCase("BK")){
                            return true;
                        }
                    }
                }
                while (filei > filed && ranki > rankd){
                    filei--;
                    ranki--;
                    diag2 = "" + filei + ranki;
                    for (ReturnPiece z : p){
                        String s = z.toString();
                        String[] sl = s.split(":");
                        if(sl[1].equalsIgnoreCase("WK") || sl[1].equalsIgnoreCase("BK")){
                            return true;
                        }
                    }
                }
                while (filei < filed && ranki > rankd){
                    ranki--;
                    filei++;
                    diag2 = "" + filei + ranki;
                    for (ReturnPiece z : p){
                        String s = z.toString();
                        String[] sl = s.split(":");
                        if(sl[1].equalsIgnoreCase("WK") || sl[1].equalsIgnoreCase("BK")){
                            return true;
                        }
                    }
                }
                while (filei > filed && ranki < rankd){
                    ranki++;
                    filei--;
                    diag2 = "" + filei + ranki;
                    for (ReturnPiece z : p){
                        String s = z.toString();
                        String[] sl = s.split(":");
                        if(sl[1].equalsIgnoreCase("WK") || sl[1].equalsIgnoreCase("BK")){
                            return true;
                        }
                    }
                }
                return false;
            }
    
            return true;
        }
    }

