package assignment3;

import java.util.ArrayList;

public class BoardState {
	ArrayList<Tile> tilePile;
	ArrayList<SPlayer> currPlayers;
	ArrayList<SPlayer> elimPlayers;
	Board board;
	//empty list is treated as false
	ArrayList<SPlayer> winners;
	
	public BoardState(ArrayList<Tile> tilePile, ArrayList<SPlayer> currPlayers, ArrayList<SPlayer> elimPlayers, 
			Board board, ArrayList<SPlayer> winners){
		this.tilePile = tilePile;
		this.currPlayers = currPlayers;
		this.elimPlayers = elimPlayers;
		this.board = board;
		this.winners = winners;
	}
}
