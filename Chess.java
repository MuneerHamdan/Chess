package chess;

import java.util.ArrayList;

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
	
	/**
	 * Plays the next move for whichever player has the turn.
	 * 
	 * @param move String for next move, e.g. "a2 a3"
	 * 
	 * @return A ReturnPlay instance that contains the result of the move.
	 *         See the section "The Chess class" in the assignment description for details of
	 *         the contents of the returned ReturnPlay instance.
	 */
	public static ReturnPlay play(String move) {

		/* FILL IN THIS METHOD */
		
		/* FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY */
		/* WHEN YOU FILL IN THIS METHOD, YOU NEED TO RETURN A ReturnPlay OBJECT */

		ReturnPlay returnPlay = new ReturnPlay();
		

		if (move.isEmpty() || move.length() < 4){
			returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
			return returnPlay;
		}
		char afile = move.charAt(0);
		int arank = move.charAt(1) - '0';
		if (!Character.isLetter(afile)){
			returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
			return returnPlay;
		}
		else if(arank < 1 || arank > 8){
			returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
			return returnPlay;
		}
		if (arank == 1 || arank == 2){

		}
		else {
			returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
			return returnPlay;
		}

		if (move.charAt(2) != ' '){
			returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
			return returnPlay;
		}

		char bfile = move.charAt(3);
		int brank = move.charAt(4) - '0';
		if (!Character.isLetter(bfile)){
			returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
			return returnPlay;
		}
		else if(brank < 1 || brank > 8){
			returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
			return returnPlay;
		}

		returnPlay.message = null;
		returnPlay.piecesOnBoard = new ArrayList<>();
		
		ReturnPiece wr = new ReturnPiece();
		wr.pieceFile = ReturnPiece.PieceFile.a;
		wr.pieceRank = 1;
		wr.pieceType = ReturnPiece.PieceType.WR;
		returnPlay.piecesOnBoard.add(wr);
		ReturnPiece wr2 = new ReturnPiece();
		wr2.pieceFile = ReturnPiece.PieceFile.h;
		wr2.pieceRank = 1;
		wr2.pieceType = ReturnPiece.PieceType.WR;
		returnPlay.piecesOnBoard.add(wr2);

		ReturnPiece br = new ReturnPiece();
		br.pieceFile = ReturnPiece.PieceFile.a;
		br.pieceRank = 8;
		br.pieceType = ReturnPiece.PieceType.BR;
		returnPlay.piecesOnBoard.add(br);
		ReturnPiece br2 = new ReturnPiece();
		br2.pieceFile = ReturnPiece.PieceFile.h;
		br2.pieceRank = 8;
		br2.pieceType = ReturnPiece.PieceType.BR;
		returnPlay.piecesOnBoard.add(br2);

		ReturnPiece wn = new ReturnPiece();
		wn.pieceFile = ReturnPiece.PieceFile.b;
		wn.pieceRank = 1;
		wn.pieceType = ReturnPiece.PieceType.WN;
		returnPlay.piecesOnBoard.add(wr);
		ReturnPiece wn2 = new ReturnPiece();
		wn2.pieceFile = ReturnPiece.PieceFile.g;
		wn2.pieceRank = 1;
		wn2.pieceType = ReturnPiece.PieceType.WN;
		returnPlay.piecesOnBoard.add(wn2);

		ReturnPiece bn = new ReturnPiece();
		bn.pieceFile = ReturnPiece.PieceFile.b;
		bn.pieceRank = 8;
		bn.pieceType = ReturnPiece.PieceType.BN;
		returnPlay.piecesOnBoard.add(bn);
		ReturnPiece bn2 = new ReturnPiece();
		bn2.pieceFile = ReturnPiece.PieceFile.g;
		bn2.pieceRank = 8;
		bn2.pieceType = ReturnPiece.PieceType.BN;
		returnPlay.piecesOnBoard.add(bn2);

		ReturnPiece wb = new ReturnPiece();
		wb.pieceFile = ReturnPiece.PieceFile.c;
		wb.pieceRank = 1;
		wb.pieceType = ReturnPiece.PieceType.WB;
		returnPlay.piecesOnBoard.add(wb);
		ReturnPiece wb2 = new ReturnPiece();
		wb2.pieceFile = ReturnPiece.PieceFile.f;
		wb2.pieceRank = 1;
		wb2.pieceType = ReturnPiece.PieceType.WB;
		returnPlay.piecesOnBoard.add(wb2);

		ReturnPiece bb = new ReturnPiece();
		bb.pieceFile = ReturnPiece.PieceFile.c;
		bb.pieceRank = 8;
		bb.pieceType = ReturnPiece.PieceType.BB;
		returnPlay.piecesOnBoard.add(bb);
		ReturnPiece bb2 = new ReturnPiece();
		bb2.pieceFile = ReturnPiece.PieceFile.f;
		bb2.pieceRank = 8;
		bb2.pieceType = ReturnPiece.PieceType.BB;
		returnPlay.piecesOnBoard.add(bb2);

		ReturnPiece wq = new ReturnPiece();
		wq.pieceFile = ReturnPiece.PieceFile.d;
		wq.pieceRank = 1;
		wq.pieceType = ReturnPiece.PieceType.WQ;
		returnPlay.piecesOnBoard.add(wq);
		ReturnPiece bq = new ReturnPiece();
		bq.pieceFile = ReturnPiece.PieceFile.d;
		bq.pieceRank = 8;
		bq.pieceType = ReturnPiece.PieceType.BQ;
		returnPlay.piecesOnBoard.add(bq);

		ReturnPiece wk = new ReturnPiece();
		wk.pieceFile = ReturnPiece.PieceFile.e;
		wk.pieceRank = 1;
		wk.pieceType = ReturnPiece.PieceType.WK;
		returnPlay.piecesOnBoard.add(wk);
		ReturnPiece bk = new ReturnPiece();
		bk.pieceFile = ReturnPiece.PieceFile.e;
		bk.pieceRank = 8;
		bk.pieceType = ReturnPiece.PieceType.BK;
		returnPlay.piecesOnBoard.add(bk);
		
		ReturnPiece wp[] = new ReturnPiece[8];
		for (int i = 0; i < 8; ++i){
			wp[i] = new ReturnPiece();
			wp[i].pieceFile = ReturnPiece.PieceFile.values()[i];
			wp[i].pieceRank = 2;
			wp[i].pieceType = ReturnPiece.PieceType.WP;
			returnPlay.piecesOnBoard.add(wp[i]);
		}
		ReturnPiece bp[] = new ReturnPiece[8];
		for (int i = 0; i < 8; ++i){
			bp[i] = new ReturnPiece();
			bp[i].pieceFile = ReturnPiece.PieceFile.values()[i];
			bp[i].pieceRank = 7;
			bp[i].pieceType = ReturnPiece.PieceType.BP;
			returnPlay.piecesOnBoard.add(bp[i]);
		}

		for (ReturnPiece r : returnPlay.piecesOnBoard){
			if (r.pieceFile.name().charAt(0) == afile && r.pieceRank == arank){
				r.pieceRank = brank;
				String sz = r.pieceFile.valueOf("" + bfile).name();
				r.pieceFile = r.pieceFile.valueOf(sz);
				break;
			}
		}

		return returnPlay;
	}
	
	
	/**
	 * This method should reset the game, and start from scratch.
	 */
	public static void start() {
		/* FILL IN THIS METHOD */

		System.out.println("\nWelcome to chess!\nWhite moves first.\n");

		System.out.println("Type \"quit\" to quit or \"reset\" to reset at any time\n\nElse, type a position of a piece followed by where you want to move it.");

		}		
	
	}