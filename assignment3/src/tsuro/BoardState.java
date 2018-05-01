package tsuro;

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
	
	public boolean equals(Object obj) {
		BoardState bs = (BoardState) obj; 
		return (this.tilePile.equals(bs.tilePile) && this.currPlayers.equals(bs.currPlayers) && 
				this.elimPlayers.equals(bs.elimPlayers) && this.board.equals(bs.board) &&
				this.winners.equals(bs.winners));
	}
	
	public ArrayList<SPlayer> getWinners(){
		return winners;
	}
}
