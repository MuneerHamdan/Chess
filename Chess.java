package chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Chess{
    public static void main(String[] args) throws IOException{

		Board board = new Board();
		board.makeBoard();
		board.printBoard();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			System.out.println("what piece to move:");
			String input = bufferedReader.readLine();
			if (input.isEmpty() || input.length() <= 1){
				System.err.println("wrong input");
				break;
			}
			char afile = input.charAt(0);
			char arank = input.charAt(1);
			int arankint = arank - '0';

			System.out.println("to where:");
			String input2 = bufferedReader.readLine();
			if (input2.isEmpty() || input2.length() <= 1){
				System.err.println("wrong input");
				break;
			}

			char bfile = input2.charAt(0);
			char brank = input2.charAt(1);
			int brankint = brank - '0';
			
			
			board.movePiece(arankint, afile, brankint, bfile);
			
			board.printBoard();
		}		
	}
}