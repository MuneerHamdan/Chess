package chess;

import java.util.ArrayList;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;
import chess.ReturnPlay.Message;

class ReturnPiece {
	static enum PieceType {WP, WR, WN, WB, WQ, WK, 
		            BP, BR, BN, BB, BK, BQ};
	static enum PieceFile {a, b, c, d, e, f, g, h};
	
	PieceType pieceType;
	PieceFile pieceFile;
	int pieceRank;  // 1..8
	public String toString() {
		return ""+pieceFile+pieceRank+":"+pieceType;
	}
	public boolean equals(Object other) {
		if (other == null || !(other instanceof ReturnPiece)) {
			return false;
		}
		ReturnPiece otherPiece = (ReturnPiece)other;
		return pieceType == otherPiece.pieceType &&
				pieceFile == otherPiece.pieceFile &&
				pieceRank == otherPiece.pieceRank;
	}
}

class ReturnPlay {
	enum Message {ILLEGAL_MOVE, DRAW, 
				  RESIGN_BLACK_WINS, RESIGN_WHITE_WINS, 
				  CHECK, CHECKMATE_BLACK_WINS,	CHECKMATE_WHITE_WINS, 
				  STALEMATE};
	
	ArrayList<ReturnPiece> piecesOnBoard;
	Message message;
}

public class Chess {
	
	enum Player { white, black }
	public static ReturnPlay p;
	public static int i = 0;
	public static boolean reset = false; 
	
	/**
	 * Plays the next move for whichever player has the turn.
	 * 
	 * @param move String for next move, e.g. "a2 a3"
	 * 
	 * @return A ReturnPlay instance that contains the result of the move.
	 *         See the section "The Chess class" in the assignment description for details of
	 *         the contents of the returned ReturnPlay instance.
	 */

	//the board is already created
	//just moves piece
	public static ReturnPlay play(String move) {

		/* FILL IN THIS METHOD */
		//make = new ArrayList<ReturnPiece>();
		/* FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY */
		/* WHEN YOU FILL IN THIS METHOD, YOU NEED TO RETURN A ReturnPlay OBJECT */
		//Chess.p.message = null;
		
		ReturnPlay.Message m = Board.move(p.piecesOnBoard, move);

		if (reset){
			p.message = null;
			Board.allmoves = null;
			Board.turn = 0;
			start();
			return p;
		}
		if(m == null){
			p.message = null;
			return p;
		}
		switch (m) {
			case ILLEGAL_MOVE:
				p.message = ReturnPlay.Message.ILLEGAL_MOVE;
				break;
			case RESIGN_BLACK_WINS:
				p.message = ReturnPlay.Message.RESIGN_BLACK_WINS;
				break;
			case RESIGN_WHITE_WINS:
				p.message = ReturnPlay.Message.RESIGN_WHITE_WINS;
				break;
			case CHECK:
				p.message = ReturnPlay.Message.CHECK;
				break;
			case CHECKMATE_BLACK_WINS:
				p.message = ReturnPlay.Message.CHECKMATE_BLACK_WINS;
				break;
			case CHECKMATE_WHITE_WINS:
				p.message = ReturnPlay.Message.CHECKMATE_WHITE_WINS;
				break;
			case DRAW:
				p.message = ReturnPlay.Message.DRAW;
				break;
			case STALEMATE:
				p.message = ReturnPlay.Message.STALEMATE;
				break;
			default:
				p.message = null;
				break;
		}

		// if (Board.move(p.piecesOnBoard, move) == ReturnPlay.Message.ILLEGAL_MOVE){
		// 	p.message = ReturnPlay.Message.ILLEGAL_MOVE;
		// }
		// else {
		// 	p.message = null;
		// }
		
		return p;
	}
	
	
	/**
	 * This method should reset the game, and start from scratch.
	 */
	//makes the return play in the chess field
	//and fills the board
	public static void start() {
		/* FILL IN THIS METHOD */
		//makePlay();
		p = new ReturnPlay();
		p.piecesOnBoard = Board.createBoard();
		p.message = null;
		reset = false;
		i = 0;
		
	}
}

class Board {

	public static int turn = 0;
	public static ArrayList<String> allmoves = new ArrayList<>();
	public static boolean kingMoved = false;
	public static boolean rookMoved = false;

	//this method returns a "board" aka the arraylist of return pieces
	//calls all the addPiece methods
	public static ArrayList<ReturnPiece> createBoard(){
		ArrayList<ReturnPiece> p = new ArrayList<>();
		addPawn(p);
		addQueen(p);
		addRook(p);
		addKnight(p);
		addBishop(p);
		addKing(p);
		
		return p;
	}

	public static String[] parseMove(String move){
		String[] moveList = move.split(" ");

		return moveList;
	}

	/*public static String isolateStrings(String s, ){
		s.split(":");
	}*/
	//this should call both canMovePiece and canMoveBoard
	//like a if(canMovePiece) then if(canMoveBoard), then the piece moves.
	public static ReturnPlay.Message move(ArrayList<ReturnPiece> p, String move){

		// boolean check = false;

		String[] list = parseMove(move);
		allmoves.add(move);
		if(list.length == 0){
			return ReturnPlay.Message.ILLEGAL_MOVE;
		}
		String firstSquare = list[0];
		String secondSquare = null;
		
		String specifier = null;
		String draw = null;
		if (list.length == 3){
			if (list[2].length() == 1){
				specifier = list[2];
			}
			else {
				draw = list[2];
			}
		}
		if (firstSquare.equalsIgnoreCase("resign") && Chess.i == 0){
			return Message.RESIGN_WHITE_WINS; //ADD WHITE BLACK ONCE ADD TURNS
		}
		if (firstSquare.equalsIgnoreCase("resign") && Chess.i == 1){
			return Message.RESIGN_BLACK_WINS; //ADD WHITE BLACK ONCE ADD TURNS
		}

		if (firstSquare.equalsIgnoreCase("reset")){
			Chess.reset = true;
			return null;
		}
		
		if(firstSquare.length() != 2){
			return ReturnPlay.Message.ILLEGAL_MOVE;
		}
		if (list.length >= 2){
			secondSquare = list[1];
			if(secondSquare.length() != 2){
				return ReturnPlay.Message.ILLEGAL_MOVE;
			}
		}
		if(!hasTile(firstSquare, p)){
			return ReturnPlay.Message.ILLEGAL_MOVE;
		}

		ReturnPiece initialPiece = null;
		ReturnPiece takenpiece = null;
		
		if(canMovePiece(move, p)){
			for(ReturnPiece piece : p){
				String s = piece.toString();
				String[] sl = s.split(":");
				if(sl[0].equalsIgnoreCase(firstSquare)){
					initialPiece = piece;
					break;
				}
			}

			for (ReturnPiece piece : p){
				String s = piece.toString();
				String[] sl = s.split(":");
				if (sl[0].equalsIgnoreCase(secondSquare) && !sl[1].equalsIgnoreCase(firstSquare)){
					takenpiece = piece;
					break;
				}
			}

			
		if(typeMove(initialPiece, Chess.i)){
			switch (initialPiece.pieceType) {
				case WP:
					if (Character.getNumericValue(secondSquare.charAt(1)) == 8){
						
						p.remove(initialPiece);

							if (specifier.equalsIgnoreCase("N")){
								Knight n = new Knight(takenpiece.pieceFile, ((int)takenpiece.pieceRank), PieceType.WN);
								p.add(n);
							}
							else if (specifier.equalsIgnoreCase("R")){
								Rook r = new Rook(takenpiece.pieceFile, ((int)takenpiece.pieceRank), PieceType.WR);
								p.add(r);
							}
							else if (specifier.equalsIgnoreCase("B")){
								Bishop b = new Bishop(takenpiece.pieceFile, ((int)takenpiece.pieceRank), PieceType.WB);
								p.add(b);
							}
							else if (specifier.equalsIgnoreCase("Q")){
								Queen q = new Queen(takenpiece.pieceFile, ((int)takenpiece.pieceRank), PieceType.WQ);
								p.add(q);
							}
							else {
								Queen q = new Queen(takenpiece.pieceFile, ((int)takenpiece.pieceRank), PieceType.WQ);
								p.add(q);
							}
					}
					else{
						new Pawn().move(firstSquare, secondSquare, p);
					}
					break;
				case BP:
					if (Character.getNumericValue(secondSquare.charAt(1)) == 1){
							
						p.remove(initialPiece);

						if (specifier.equalsIgnoreCase("N")){
							Knight n = new Knight(takenpiece.pieceFile, ((int)takenpiece.pieceRank), PieceType.BN);
							p.add(n);
						}
						else if (specifier.equalsIgnoreCase("R")){
							Rook r = new Rook(takenpiece.pieceFile, ((int)takenpiece.pieceRank), PieceType.BR);
							p.add(r);
						}
						else if (specifier.equalsIgnoreCase("B")){
							Bishop b = new Bishop(takenpiece.pieceFile, ((int)takenpiece.pieceRank), PieceType.BB);
							p.add(b);
						}
						else if (specifier.equalsIgnoreCase("Q")){
							Queen q = new Queen(takenpiece.pieceFile, ((int)takenpiece.pieceRank), PieceType.BQ);
							p.add(q);
						}
						else {
							Queen q = new Queen(takenpiece.pieceFile, ((int)takenpiece.pieceRank), PieceType.BQ);
							p.add(q);
						}
					}
					else {
						new Pawn().move(firstSquare, secondSquare, p);
					}
					break;
				case WR, BR:
					new Rook().move(firstSquare, secondSquare, p);
					rookMoved = true;
					break;
				case WN, BN:
					new Knight().move(firstSquare, secondSquare, p);
					break;
				case WB, BB:
					new Bishop().move(firstSquare, secondSquare, p);
					break;
				case WK: // make sure that rook is unmoved also
					if (!hasTile("f1", p) && !hasTile("g1", p) && secondSquare.equalsIgnoreCase("g1")  && !kingMoved && !rookMoved){
						new King().move(firstSquare, secondSquare, p);
						new Rook().move("h1", "f1", p);
					}
					else if (!hasTile("d1", p) && !hasTile("c1", p) && !hasTile("b1", p) && secondSquare.equalsIgnoreCase("c1")  && !kingMoved && !rookMoved){
						new King().move(firstSquare, secondSquare, p);
						new Rook().move("a1", "d1", p);
					}
					else {
						new King().move(firstSquare, secondSquare, p);
						kingMoved = true;
					}

					break;
				case BK:
					if (!hasTile("f8", p) && !hasTile("g8", p) && secondSquare.equalsIgnoreCase("g8") && !kingMoved && !rookMoved){
						new King().move(firstSquare, secondSquare, p);
						new Rook().move("h8", "f8", p);
					}
					else if (!hasTile("d8", p) && !hasTile("c8", p) && !hasTile("b8", p) && secondSquare.equalsIgnoreCase("c8") && !kingMoved && !rookMoved){
						new King().move(firstSquare, secondSquare, p);
						new Rook().move("a8", "d8", p);
					}
					else {
						new King().move(firstSquare, secondSquare, p);
						kingMoved = true;
					}

					break;
				case WQ, BQ:
					new Queen().move(firstSquare, secondSquare, p);
				default:
					break;
			}
			// if (!traversable){
			// 	return ReturnPlay.Message.ILLEGAL_MOVE;
			// }
			if (takenpiece != null){
				p.remove(takenpiece);
			}

			
			if (draw != null){
				if (draw.equalsIgnoreCase("draw?")){
					return ReturnPlay.Message.DRAW;
				}

			}




			turn++;
			if (queenCheck(getWQueen(p), p)){
				return ReturnPlay.Message.CHECK;
			}
			return null;
		}
		else{
			allmoves.remove(move);
			return ReturnPlay.Message.ILLEGAL_MOVE;
		}
		}
		else {
			return ReturnPlay.Message.ILLEGAL_MOVE;
		}
	}

	public static ReturnPiece getPiece(String tile, ArrayList<ReturnPiece> p){
		ReturnPiece z = null;
		for(ReturnPiece piece : p){
			String s = piece.toString();
			String[] sl = s.split(":");
			if(sl[0].equalsIgnoreCase(tile)){
				z = piece;
			}
		}
		return z;
	}

	//this manages the take turns and the white goes first
	public static boolean typeMove(ReturnPiece p, int i){
		PieceType pt = p.pieceType;
		String s = pt.name();

		if(s.charAt(0) == 'W' && i == 0){
			Chess.i++;
			return true;
		}
		else if(s.charAt(0) == 'B' && i == 1){
			Chess.i--;
			return true;

		}
		else{
			return false;
		}	
	}

	public static boolean hasTile(String tile, ArrayList<ReturnPiece> p){

		for(ReturnPiece piece : p){
			String s = piece.toString();
			String[] sl = s.split(":");
			if(sl[0].equalsIgnoreCase(tile)){
				return true;
			}
		}
		return false;
	}
	
	//i intend this method to detect if there is something in its path
	//lowkey need to figure out how to do this
	//maybe a getPath method in the diff pieces to calcualte the places in between the piece and the desired spot
	//ill get back to this
	public static boolean canMoveBoard(String move){

		return false;
	}

	/*public static void clearBoard(){

	}*/

	public static void addPawn(ArrayList<ReturnPiece> p){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 2; j++){
				Pawn pawn = new Pawn();
				pawn.pieceFile = PieceFile.values()[i];
				if(j == 0){
					pawn.pieceRank = 2;
					pawn.pieceType = PieceType.values()[j];
				}
				else{
					pawn.pieceRank = 7;
					pawn.pieceType = PieceType.values()[6];
				}
				p.add(pawn);
			}
		}
	}

	public static void addQueen(ArrayList<ReturnPiece> p){
		for(int i = 0; i < 2; i++){
			Queen q = new Queen();
			q.pieceFile = PieceFile.values()[3];
			if(i == 0){
				q.pieceRank = 1;
				q.pieceType = PieceType.values()[4];
			}
			else{
				q.pieceRank = 8;
				q.pieceType = PieceType.values()[11];
			}
			p.add(q);
		}
	}
	
	public static void addRook(ArrayList<ReturnPiece> p){
		for(int i = 0; i < 2; i++){
			Rook r = new Rook();
			Rook r2 = new Rook();
			r.pieceFile = PieceFile.values()[0];
			r2.pieceFile = PieceFile.values()[7];
			if(i == 0){
				r.pieceRank = 1;
				r.pieceType = PieceType.values()[1];
				r2.pieceRank = 1;
				r2.pieceType = PieceType.values()[1];
			}
			else{
				r.pieceRank = 8;
				r.pieceType = PieceType.values()[7];
				r2.pieceRank = 8;
				r2.pieceType = PieceType.values()[7];
			}
			p.add(r);
			p.add(r2);
		}
	}

	public static void addBishop(ArrayList<ReturnPiece> p){
		for(int i = 0; i < 2; i++){
			Bishop  b= new Bishop();
			Bishop b2 = new Bishop();
			b.pieceFile = PieceFile.values()[2];
			b2.pieceFile = PieceFile.values()[5];
			if(i == 0){
				b.pieceRank = 1;
				b.pieceType = PieceType.values()[3];
				b2.pieceRank = 1;
				b2.pieceType = PieceType.values()[3];
			}
			else{
				b.pieceRank = 8;
				b.pieceType = PieceType.values()[9];
				b2.pieceRank = 8;
				b2.pieceType = PieceType.values()[9];
			}
			p.add(b);
			p.add(b2);
		}
	}

	public static void addKing(ArrayList<ReturnPiece> p){
		for(int i = 0; i < 2; i++){
			King k = new King();
			k.pieceFile = PieceFile.values()[4];
			if(i == 0){
				k.pieceRank = 1;
				k.pieceType = PieceType.values()[5];
			}
			else{
				k.pieceRank = 8;
				k.pieceType = PieceType.values()[10];
			}
			p.add(k);
		}
	}

	public static void addKnight(ArrayList<ReturnPiece> p){
		for(int i = 0; i < 2; i++){
			Knight k = new Knight();
			Knight k2 = new Knight();
			k.pieceFile = PieceFile.values()[1];
			k2.pieceFile = PieceFile.values()[6];
			if(i == 0){
				k.pieceRank = 1;
				k.pieceType = PieceType.values()[2];
				k2.pieceRank = 1;
				k2.pieceType = PieceType.values()[2];
			}
			else{
				k.pieceRank = 8;
				k.pieceType = PieceType.values()[8];
				k2.pieceRank = 8;
				k2.pieceType = PieceType.values()[8];
			}
			p.add(k);
			p.add(k2);
		}
	}

	//for every type of piece it calls the canMove for it 
	public static boolean canMovePiece(String move, ArrayList<ReturnPiece> p){
		String[] list = parseMove(move);
		String firstSquare = list[0];
		String secondSquare = list[1];
		PieceType type = null;
		PieceType type2 = null;

		
		for(ReturnPiece piece : p){
			String s = piece.toString();
			String[] sl = s.split(":");
			if(sl[0].equalsIgnoreCase(firstSquare)){
				type = piece.pieceType;
				break;
			}
		}

		for(ReturnPiece piece : p){
			String s = piece.toString();
			String[] sl = s.split(":");
			if(sl[0].equalsIgnoreCase(secondSquare)){
				type2 = piece.pieceType;
				break;
			}
		}

		boolean desthaspiece = false;
		if (type2 != null){
			desthaspiece = true;
			if (type.name().charAt(0) == type2.name().charAt(0)){
				return false;
			}
		}

		/*if (type == null){
			return false;
		}

		if (type.name().charAt(0) == 'B' && Chess.i == 1){
			Chess.i++;
			return true;
		}
		if (type.name().charAt(0) == 'W' && Chess.i == 0){
			Chess.i--;
			return true;
		}*/


		switch(type){
			case WP:
				if (turn > 0){
					char x = allmoves.get(turn-1).charAt(1);
					char y = allmoves.get(turn-1).charAt(4);
					char z = allmoves.get(turn-1).charAt(0);
					int a = (x - '0' + y - '0')/2;
					String intial = ""  + z + a;
					
					if (x == '7' && y == '5' && firstSquare.charAt(1) == '5' && secondSquare.equalsIgnoreCase(intial)){
						String q = allmoves.get(turn-1).substring(3);
						ReturnPiece removedPiece = null;
						for (ReturnPiece piece : p){
							String asdf = piece.toString().substring(0, 2);
							if (asdf.equalsIgnoreCase(q)){
								removedPiece = piece;
								break;
							}
						}
						p.remove(removedPiece);
						return new Pawn().canMove(firstSquare, secondSquare, type, desthaspiece, true);
					}
					else {
						boolean ptraversable = new Pawn().canTake(firstSquare, secondSquare, p);

						if (ptraversable){
							return new Pawn().canMove(firstSquare, secondSquare, type, desthaspiece, false);
						}
						else {
							return false;
						}
					}
				}
				else {
					boolean ptraversable = new Pawn().canTake(firstSquare, secondSquare, p);

					if (ptraversable){
						return new Pawn().canMove(firstSquare, secondSquare, type, desthaspiece, false);
					}
					else {
						return false;
					}
				}
			case BP:
				if (turn > 0){
					char x = allmoves.get(turn-1).charAt(1);
					char y = allmoves.get(turn-1).charAt(4);
					char z = allmoves.get(turn-1).charAt(0);
					int a = (x - '0' + y - '0')/2;
					String intial = ""  + z + a;
					
					if (x == '2' && y == '4' && firstSquare.charAt(1) == '4' && secondSquare.equalsIgnoreCase(intial)){
						String q = allmoves.get(turn-1).substring(3);
						ReturnPiece removedPiece = null;
						for (ReturnPiece piece : p){
							String asdf = piece.toString().substring(0, 2);
							if (asdf.equalsIgnoreCase(q)){
								removedPiece = piece;
								break;
							}
						}
						p.remove(removedPiece);
						return new Pawn().canMove(firstSquare, secondSquare, type, desthaspiece, true);
					}
					else {
						boolean ptraversable = new Pawn().canTake(firstSquare, secondSquare, p);

					if (ptraversable){
						return new Pawn().canMove(firstSquare, secondSquare, type, desthaspiece, false);
					}
					else {
						return false;
					}
					}
				}
				else {
					boolean ptraversable = new Pawn().canTake(firstSquare, secondSquare, p);

					if (ptraversable){
						return new Pawn().canMove(firstSquare, secondSquare, type, desthaspiece, false);
					}
					else {
						return false;
					}
				}
			case WR, BR:
				boolean rtraversable = new Rook().canTake(firstSquare, secondSquare, p);
					
				if (rtraversable){
					return new Rook().canMove(firstSquare, secondSquare, type, desthaspiece);
				}else {
					return false;
				}

			case WB, BB:
				boolean btraversable = new Bishop().canTake(firstSquare, secondSquare, p);
					
				if (btraversable){
					return new Bishop().canMove(firstSquare, secondSquare, type, desthaspiece);
				}else {
					return false;
				}
			case WN, BN:
				return new Knight().canMove(firstSquare, secondSquare, type, desthaspiece);
			case WQ:
				boolean qtraversable = new Queen().canTake(firstSquare, secondSquare, p);

				if (qtraversable){
					queenCheck(secondSquare, p);
					return new Queen().canMove(firstSquare, secondSquare, type, desthaspiece);
				}else {
					return false;
				}
			case BQ:
				boolean qbtraversable = new Queen().canTake(firstSquare, secondSquare, p);

				if (qbtraversable){
					boolean qcheck = new Queen().checkKing(secondSquare, getWKing(p), p);

					if (qcheck){
						System.out.println("check");
					}
					return new Queen().canMove(firstSquare, secondSquare, type, desthaspiece);
				}else {
					return false;
				}
			case WK, BK:

				boolean ktraversable = new King().canTake(firstSquare, secondSquare, p);
				
				// if (k)


				return new King().canMove(firstSquare, secondSquare, type, desthaspiece);
			default:
				return false;
		}
	}

	public static String getWKing(ArrayList<ReturnPiece> p){
        for (ReturnPiece z : p){
            String s = z.toString();
            String[] sl = s.split(":");
            if(sl[1].equalsIgnoreCase("WK")){
                return sl[0];
            }
        }
		return null;
	}
	public static String getWQueen(ArrayList<ReturnPiece> p){
        for (ReturnPiece z : p){
            String s = z.toString();
            String[] sl = s.split(":");
            if(sl[1].equalsIgnoreCase("WQ")){
                return sl[0];
            }
        }
		return null;
	}
	

	public static String getBKing(ArrayList<ReturnPiece> p){
        for (ReturnPiece z : p){
            String s = z.toString();
            String[] sl = s.split(":");
            if(sl[1].equalsIgnoreCase("BK")){
                return sl[0];
            }
        }
		return null;
	}


	public static boolean queenCheck(String secondSquare, ArrayList<ReturnPiece> p){
		boolean qcheck = new Queen().checkKing(secondSquare, getBKing(p), p);

					if (qcheck){
						return true;
					}
					return false;
	}
	public static boolean bishopCheck(String secondSquare, ArrayList<ReturnPiece> p){
		boolean qcheck = new Bishop().checkKing(secondSquare, getBKing(p), p);

					if (qcheck){
						return true;
					}
					return false;

	}
	public static boolean rookCheck(String secondSquare, ArrayList<ReturnPiece> p){
		boolean qcheck = new Rook().checkKing(secondSquare, getBKing(p), p);

					if (qcheck){
						return true;
					}
					return false;

	}
	public static boolean knightCheck(String secondSquare, ArrayList<ReturnPiece> p){
		boolean qcheck = new Knight().checkKing(secondSquare, getBKing(p), p);

					if (qcheck){
						return true;
					}
					return false;

	}
	public static boolean pawnCheck(String secondSquare, ArrayList<ReturnPiece> p){
		boolean qcheck = new Queen().checkKing(secondSquare, getBKing(p), p);

					if (qcheck){
						return true;
					}
					return false;

	}


}

