package chess;

public class Board{

	//[row "rank"][col "file"]

	private Tile tiles[][];
	private int sizex;
	private int sizey;
	private String files; 
	private int[] ranks;

	private String empty = "##";
 
	private String bP = "bP";
	private String wP = "wP";

	private String bR = "bR";
	private String wR = "wR";
 
	private String bK = "bK";
	private String wK = "wK";
 
	private String bQ = "bQ";
	private String wQ = "wQ";
 
	private String bB = "bB";
	private String wB = "wB";
 
	private String bN = "bN";
	private String wN = "wN";
 
 
	Board(){
	 	this.sizex = 8;
		this.sizey = 8;
		this.tiles = new Tile[sizex][sizey];
		this.files = "abcdefgh";
		this.ranks = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
	}

	Board makeBoard(){
		for (int i = 0; i < this.tiles.length; ++i){
			for (int j = 0; j < this.tiles[0].length; ++j){
				this.tiles[i][j] = new Tile();
				this.tiles[i][j].setposition(this.files.charAt(i), this.ranks[j]);
				this.tiles[i][j].setpiece(new Piece(this.files.charAt(i), this.ranks[j], empty));
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

	// Piece getPiece(char a, char b){

		
	// }

	Piece getPiece(char a, int b){
		return tiles[files.indexOf(a)][b].getPiece();
	}

	void movePiece(int arank, char afile, int brank, char bfile){
		int i = files.indexOf(afile);
		int j = files.indexOf(bfile);
		String temp = tiles[8-arank][i].getType();
		tiles[8-arank][i].setType(empty);
		tiles[8-brank][j].setType(temp);
	}

	void printBoard(){
		System.out.println();
		for (int i = 0; i < this.sizex; ++i){
			for (int j = 0; j < this.sizey; ++j){
				System.out.print("" + tiles[i][j].getType() + " ");
			}
			System.out.println();
		}
	}
}