package chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * todo: add illegal moves
 *  - has to be in order, white black white black moves
 *  - each piece has a certain moveset
 *  - cant move emptys
 *  - cant move piece somewhere unless empty or capturable or in their able moves
 *  - game must start with a white move
 */

public class Chess{
    public static void main(String[] args) throws IOException{

		Board board = new Board();
		board.makeBoard();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int turn = 0;
		while(true){
			board.printBoard();

			System.out.println("what piece to move:");
			String input = bufferedReader.readLine();
			if (input.isEmpty() || input.length() != 2){
				System.err.println("wrong input");
				break;
			}
			char afile = input.charAt(0);
			char arank = input.charAt(1);
			int arankint = arank - '0';
			if (!Character.isLetter(afile) || !Character.isDigit(arank)){
				System.err.println("wrong input");
				break;
			}
			if (arankint == 1 || arankint == 2){

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
			char brank = input2.charAt(1);
			int brankint = brank - '0';
			if (!Character.isLetter(bfile) || !Character.isDigit(brank)){
				System.err.println("wrong input");
				break;
			}
			
			board.movePiece(arankint, afile, brankint, bfile);
			
		}		
	}
}