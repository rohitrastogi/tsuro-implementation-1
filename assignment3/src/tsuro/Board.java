package tsuro;

import java.util.*;

public class Board {
	
	// The number of tiles that fit on a single row of the board 
	public static final int TILES_PER_ROW = 6; 
	
	// The actual layout of tiles on the board, each location starts out as null. 
	// Once a tile is placed in a location, that index will refer to the tile object 
	private Tile[][] tileLayout; 
	
	private ArrayList<Token> tokens = new ArrayList<Token>();
	
	/*
	// TODO: Remember to toggle this to false once it's possible to take multiple turns 
	// Should be toggled after every player has gone once 
	private boolean isFirstTurn = true; 
	*/ 

	public Board(){
		tileLayout = new Tile[TILES_PER_ROW][TILES_PER_ROW];
	}
	
	public Board(Tile[][] layout) {
		// TODO tbh not sure if this works (is clone necessary here)
		tileLayout = layout.clone(); 
	}
	
	public boolean isPositionTaken(Position posn) {
		// loop through arraylist of tokens, check to see if they are already occupying posn 
		for (Token t : tokens) {
			if (t.getPosition().equals(posn)) {
				return true; 
			}
		}
		
		return false; 
	}
	
	public boolean equals(Object obj) {
		Board b = (Board) obj; 
		boolean length_equal = (b.tileLayout.length == this.tileLayout.length) ;
		if (!length_equal){
			return false;
		}

		for (int i = 0; i < b.tileLayout.length; i++){
			length_equal = (b.tileLayout[i].length == this.tileLayout[i].length);
			if (!length_equal){
				return false;
			}
			for (int j=0; j< b.tileLayout[i].length; j++){
				if ((b.tileLayout[i][j] == null) && (this.tileLayout[i][j] == null)){ //both are null return, continue
					continue;
				}
				else if (b.tileLayout[i][j] == null || this.tileLayout[i][j] == null){ //if one is null, return false
					return false;
				}
				// neither are null
				else if (b.tileLayout[i][j].equals(this.tileLayout[i][j])){ //equal tile internals
					continue;
					}
				else {
					return false; //unequal tile internals
				}
			}
		}
		return true; 
	}
	
	public Tile[][] getLayout(){
		return tileLayout;
	}
	
	public void placeTile(Tile toPlace, int x, int y){
		tileLayout[y][x] = toPlace;
	}
	
	// Checks whether a move is valid meaning it is either: 
	// a. Not an elimination move 
	// b. The player only has elimination moves available 
	public boolean isValidMove(Tile tile, SPlayer player) {
		//check if tile with rotation specified given is valid move
		if (!isEliminationMove(tile, player)) {
			return true; 
		}
		//state: current move you are trying to make will eliminate you
		// check if other rotations of current tile won't eliminate you
		// if there is one rotation that does not eliminate you, it is not a valid move
		for (int i=0; i< Tile.NUM_SIDES; i++){
			Tile rotated = tile.rotate(i);
			if(!isEliminationMove(rotated, player)){
				return false;
			}
		}
		// state: all rotations of argument tile will eliminate player
		// check all other possible moves of the player (including rotations)
		// if they are ALL elimination moves, return true, otherwise return false 
		for (Tile t : player.getTiles()){
			for (int i=0; i<Tile.NUM_SIDES; i++){
				Tile rotated = t.rotate(i);
				if (!isEliminationMove(rotated, player)){
					return false;
				}
			}
		}
		return true; 
	}
	
	// Returns true if a move is going to eliminate the player making the move 
	public boolean isEliminationMove(Tile t, SPlayer p) {
		Position playerPosn = p.getPosition();
		Position finalPosn = getFinalPosition(t, playerPosn.getAdjacentPosition());
		if (finalPosn == null){
			return true;
		}
		return finalPosn.isEdgePosition();
	}
	
	// Gets the final Position given an input Position and a tile to move onto 
	// t: The tile that is being moved onto (not the tile the player is currently on) 
	// start: The position of the player relative to t, NOT THE TILE THEY'RE CURRENTLY ON 
	// e.g. A player on tile (1, 2) at position 0 -> a tile at (1, 1)
	//      Pass the tile at (1, 1) and a position of (1, 1, 5) 
	public Position getFinalPosition(Tile t, Position start) {
		// Get the position after moving along the path on the passed tile from our starting position 
		Position intermediatePosn = new Position(start.getX(), start.getY(), t.getOutPath(start.getTilePosn())); 
		
		// If this intermediate position is on the edge of the board, it's not possible to go any further
		if (intermediatePosn.isEdgePosition()) {
			return intermediatePosn; 
		}
		
		// Now, check where our current position is, and what the next tile is 
		int currTilePosn = intermediatePosn.getTilePosn(); 
		Tile nextTile; 
		
		if (currTilePosn < 2) {
			// At the top of our current tile, move upwards if possible 
			nextTile = getTile(intermediatePosn.getX(), intermediatePosn.getY() - 1); 
		}
		else if (currTilePosn < 4) {
			// At the right side of our current tile
			nextTile = getTile(intermediatePosn.getX() + 1, intermediatePosn.getY()); 
		}
		else if (currTilePosn < 6) {
			// At the bottom of our current tile 
			nextTile = getTile(intermediatePosn.getX(), intermediatePosn.getY() + 1); 
		}
		else {
			// At the left side of our current tile 
			nextTile = getTile(intermediatePosn.getX() - 1, intermediatePosn.getY()); 
		}
		
		// Don't try to move onto a tile that hasn't actually been placed yet 
		if (nextTile == null) {
			return intermediatePosn; 
		}
		Position nextPosn = intermediatePosn.getAdjacentPosition();
		
		// Move onto the next tile and get the final position from there 
		return getFinalPosition(nextTile, nextPosn); 
	}
	
	
	// Getters and Setters
	// Get a tile at an x, y position 
	public Tile getTile(int x, int y) {
		// Look for index-out-of-bounds errors 
		if (x < 0 || y < 0 || x >= TILES_PER_ROW || y >= TILES_PER_ROW) {
			return null; 
		}
		return tileLayout[y][x]; 
	}
	
	public void addToken(Token t) {
		tokens.add(t); 
	}
}
