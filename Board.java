package chess;

public class Board{

	private Tile tiles[][];
	private int sizex;
	private int sizey;
	private char[] files; 
	private int[] ranks;

	String bP = "bP";
	String wP = "wP";

	String bR = "bR";
	String wR = "wR";

	String bK = "bK";
	String wK = "wK";

	String bQ = "bQ";
	String wQ = "wQ";

	String bB = "bB";
	String wB = "wB";

	String bN = "bN";
	String wN = "wN";


	Board(){
		this.sizex = 8;
		this.sizey = 8;
		this.tiles = new Tile[sizex][sizey];
		this.files = new char[]{'h', 'g', 'f', 'e', 'd', 'c', 'b', 'a'};
		this.ranks = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
	}

	Board makeBoard(){
		for (int i = 0; i < this.tiles.length; ++i){
			for (int j = 0; j < this.tiles[0].length; ++j){
				this.tiles[i][j] = new Tile();
				this.tiles[i][j].setposition(this.files[i], this.ranks[j]);
				this.tiles[i][j].setpiece(new Piece(this.files[i], this.ranks[j], '#', '#'));
			}
		}

		for (int i = 0; i < 8; ++i){
			this.tiles[1][i].setType(bP);
			this.tiles[6][i].setType(wP);
		}

		this.tiles[0][0].setType(bR);
		this.tiles[0][7].setType(bR);
		this.tiles[7][0].setType(wR);
		this.tiles[7][7].setType(wR);

		this.tiles[0][4].setType(bK);
		this.tiles[7][4].setType(wK);
		
		this.tiles[0][3].setType(bQ);
		this.tiles[7][3].setType(wQ);
		
		this.tiles[0][2].setType(bB);
		this.tiles[0][5].setType(bB);
		this.tiles[7][2].setType(wB);
		this.tiles[7][5].setType(wB);
		
		this.tiles[0][1].setType(bN);
		this.tiles[0][6].setType(bN);
		this.tiles[7][1].setType(wN);
		this.tiles[7][6].setType(wN);
		
		

        return this;
    }

	void printBoard(Board board){
		for (int i = 0; i < board.sizex; ++i){
			for (int j = 0; j < board.sizey; ++j){
				System.out.print("" + tiles[i][j].getType() + " ");
			}
			System.out.println();
		}
	}
}