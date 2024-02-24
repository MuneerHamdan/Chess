package chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * add illegal moves
 *  - has to be in order, white black white black moves
 *  - each piece has a certain moveset
 *  - cant move emptys
 *  - cant move piece somewhere unless empty or capturable or in their able moves
 * 
 * piece class should be abstract
 *  - then make individual classes for all pieces
 */

public class Chess{
    public static void main(String[] args) throws IOException{

		Board board = new Board();
		board.makeBoard();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		while(true){
			board.printBoard();

			System.out.println("what piece to move:");
			String input = bufferedReader.readLine();
			if (input.isEmpty() || input.length() != 2){
				System.err.println("wrong input");
				break;
			}
			char afile = input.charAt(0);
			int arank = input.charAt(1) - '0';
			if (!Character.isLetter(afile)){
				System.err.println("wrong input");
				break;
			}
			else if(arank < 1 || arank > 8){
				System.err.println("wrong input");
				break;
			}
			if (arank == 1 || arank == 2){

			}
			else {
				System.err.println("must start with white pieces");
				break;
			}



			System.out.println("to where:");
			String input2 = bufferedReader.readLine();
			if (input2.isEmpty() || input2.length() <= 1){
				System.err.println("wrong input");
				break;
			}
			char bfile = input2.charAt(0);
			int brank = input2.charAt(1) - '0';
			if (!Character.isLetter(bfile)){
				System.err.println("wrong input");
				break;
			}
			else if(brank < 1 || brank > 8){
				System.err.println("wrong input");
				break;
			}
			
			board.movePiece(arank, afile, brank, bfile);
			bufferedReader.close();
		}		
	}
}