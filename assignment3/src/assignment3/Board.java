package assignment3;

import java.util.*;

public class Board {
	//hardcoded start positions
	Position[] posns = {new Position(0, 0, 5), new Position(0, 3, 6), new Position(0, 5, 5), new Position(5, 0, 3), new Position(5, 3, 3),
			new Position(5, 5, 5), new Position(0, 3, 1), new Position(0, 5, 1)};
	
	// The number of tiles that fit on a single row of the board 
	public static final int TILES_PER_ROW = 5; 
	
	// The actual layout of tiles on the board, each location starts out as null. 
	// Once a tile is placed in a location, that index will refer to the tile object 
	private Tile[][] tileLayout; 
	
	/*
	// TODO: Remember to toggle this to false once it's possible to take multiple turns 
	// Should be toggled after every player has gone once 
	private boolean isFirstTurn = true; 
	*/ 

	public Board(){
		tileLayout = new Tile[TILES_PER_ROW][TILES_PER_ROW];
	}
	
	public Board(Tile[][] layout) {
		tileLayout = layout.clone(); 
	}
	
	// Checks whether a move is valid meaning it is either: 
	// a. Not an elimination move 
	// b. The player only has elimination moves available 
	private boolean isValidMove(Tile tile, SPlayer player) {
		if (!isEliminationMove(tile, player)) {
			return true; 
		}
		// Otherwise, check all other possible moves of the player (including rotations)
		// If they are ALL elimination moves, return true, otherwise return false 
		// TODO 
		return false; 
	}
	
	// Returns true if a move is going to eliminate the player making the move 
	private boolean isEliminationMove(Tile t, SPlayer p) {
		Position playerPosn = p.getPosn();
		Position finalPosn = getFinalPosition(t, playerPosn);
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
	private Position getFinalPosition(Tile t, Position start) {
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
		
		// Error null position (error code) to signify that this move would cause a double elimination
		if (arePosnsSame(nextPosn)){
			return null;
		}
		
		// Move onto the next tile and get the final position from there 
		return getFinalPosition(nextTile, nextPosn); 
	}
	
	// Check whether other players are at the same position as the input position
	public boolean arePosnsSame(Position intermediatePosn){
		for (SPlayer p : currPlayers){
			if (p.getPosn().equals(intermediatePosn)){
					return true;
			}
		}
		return false;
	}
	
	public boolean isGameOver(){
		return false;
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
}
