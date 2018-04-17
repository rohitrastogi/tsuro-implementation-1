package assignment3;

import java.util.*;

public class Board {
	int turnIndex;
	
	ArrayList<SPlayer> currPlayers;
	ArrayList<SPlayer> eliminatedPlayers;
	
	//hardcoded start positions
	Position[] posns = {new Position(0, 0, 5), new Position(0, 3, 6), new Position(0, 5, 5), new Position(5, 0, 3), new Position(5, 3, 3),
			new Position(5, 5, 5), new Position(0, 3, 1), new Position(0, 5, 1)};
	public static final int TILES_PER_PLAYER = 3;
	
	ArrayList<Tile> tilePile = new ArrayList<Tile>();
	
	public static final int TILES_PER_ROW = 5; 
	// The actual layout of tiles on the board, each location starts out as null. 
	// Once a tile is placed in a location, that index will refer to the tile object 
	private Tile[][] tileLayout = new Tile[TILES_PER_ROW][TILES_PER_ROW]; 
	
	/*
	// TODO: Remember to toggle this to false once it's possible to take multiple turns 
	// Should be toggled after every player has gone once 
	private boolean isFirstTurn = true; 
	*/ 
	
	// Mapping between the positions on the edges of two adjacent tiles 
	Map<Integer, Integer> edgeMapping = new HashMap<Integer, Integer>() {{
		put(0, 5); 
		put(1, 4); 
		put(2, 7); 
		put (3, 6); 
		put (4, 1); 
		put (5, 0); 
		put (6, 3); 
		put (7, 2); 
	}};

	public Board(int numPlayers){
		generateTilePile();
		createPlayers(numPlayers);
	}
	
	// Makes 35 unique Tiles and adds each one to the tile pile 
	public void generateTilePile(){
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 3), new Tuple(4, 5), new Tuple(6, 7)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 4), new Tuple(3, 6), new Tuple(5, 7)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 6), new Tuple(1, 5), new Tuple(2, 4), new Tuple(3, 7)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 4), new Tuple(2, 7), new Tuple(3, 6)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 2), new Tuple(1, 4), new Tuple(3, 7), new Tuple(5, 6)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 4), new Tuple(1, 7), new Tuple(2, 3), new Tuple(5, 6)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 6), new Tuple(3, 7), new Tuple(4, 5)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 2), new Tuple(1, 6), new Tuple(3, 7), new Tuple(4, 5)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 4), new Tuple(1, 5), new Tuple(2, 6), new Tuple(3, 7)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 7), new Tuple(3, 4), new Tuple(5, 6)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 2), new Tuple(1, 7), new Tuple(3, 4), new Tuple(5, 6)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 3), new Tuple(1, 5), new Tuple(2, 7), new Tuple(4, 6)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 4), new Tuple(1, 3), new Tuple(2, 7), new Tuple(5, 6)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 3), new Tuple(1, 7), new Tuple(2, 6), new Tuple(4, 5)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 5), new Tuple(3, 6), new Tuple(4, 7)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 3), new Tuple(1, 6), new Tuple(2, 5), new Tuple(4, 7)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 7), new Tuple(3, 5), new Tuple(4, 7)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 7), new Tuple(1, 6), new Tuple(2, 3), new Tuple(4, 5)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 7), new Tuple(1, 2), new Tuple(3, 4), new Tuple(5, 6)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 2), new Tuple(1, 4), new Tuple(3, 6), new Tuple(5, 7)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 7), new Tuple(1, 3), new Tuple(2, 5), new Tuple(4, 6)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 7), new Tuple(1, 5), new Tuple(2, 6), new Tuple(3, 4)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 4), new Tuple(1, 5), new Tuple(2, 7), new Tuple(3, 6)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 4), new Tuple(3, 5), new Tuple(6, 7)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 2), new Tuple(1, 7), new Tuple(3, 5), new Tuple(4, 6)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 7), new Tuple(1, 5), new Tuple(2, 3), new Tuple(4, 6)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 4), new Tuple(1, 3), new Tuple(2, 6), new Tuple(5, 7)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 6), new Tuple(1, 3), new Tuple(2, 5), new Tuple(4, 7)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 7), new Tuple(3, 6), new Tuple(4, 5)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 3), new Tuple(1, 2), new Tuple(4, 6), new Tuple(5, 7)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 3), new Tuple(1, 5), new Tuple(2, 6), new Tuple(4, 7)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 7), new Tuple(1, 6), new Tuple(2, 5), new Tuple(3, 4)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 2), new Tuple(1, 3), new Tuple(4, 6), new Tuple(5, 7)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 6), new Tuple(2, 7), new Tuple(3, 4)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 3), new Tuple(2, 6), new Tuple(4, 7)}));
	}
	
	public void createPlayers(int num){
		Random rand = new Random();
		Collections.shuffle(tilePile, rand);
		
		for(int i=0; i<num; i++){
			ArrayList<Tile> myTiles = new ArrayList<Tile>(tilePile.subList(0, TILES_PER_PLAYER));
			for (int j=0; j<TILES_PER_PLAYER; j++){
				tilePile.remove(0);
			}
			currPlayers.add(new SPlayer(myTiles, Color.values()[i], posns[i]));		
		}
	}
	
	// Makes sure a play is legal for a given player, board, and tile, as defined in the homework spec 
	public boolean isLegalPlay(SPlayer player, Board board, Tile tile) {
		return (hasTile(tile, player)) && (isValidMove(tile, player));
	}
	
	// Checks whether a player has a certain tile in their possession 
	private boolean hasTile(Tile tile, SPlayer player) {
		// loop through tiles in player's position, checking for array equality between path values 
		boolean isEqual = false; 
		for(Tile t : player.getTiles()) {
			if (Arrays.equals(t.getPaths(), tile.getPaths())) {
				// If two tiles have the same paths, they're the same tile 
				isEqual = true; 
			}
		}
		
		return isEqual; 
	}
	
	// Checks whether a move is valid meaning it is either: 
	// a. Not an elimination move 
	// b. The player only has elimination moves available 
	private boolean isValidMove(Tile tile, SPlayer player) {
		if (!isEliminationMove(tile)) {
			return true; 
		}
		// Otherwise, check all other possible moves of the player (including rotations)
		// If they are ALL elimination moves, return true, otherwise return false 
		return false; 
	}
	
	// Returns true if a move is going to eliminate the player making the move 
	private boolean isEliminationMove(Tile t) {
		// TODO: Actually write this
		// Return true if getFinalPosition returns an edge position 
		return true; 
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
		Position nextPosn; 
		if (currTilePosn < 2) {
			// At the top of our current tile, move upwards if possible 
			nextTile = getTile(intermediatePosn.getX(), intermediatePosn.getY() - 1); 
			nextPosn = new Position(intermediatePosn.getX(), intermediatePosn.getY() - 1, edgeMapping.get(currTilePosn)); 
		}
		else if (currTilePosn < 4) {
			// At the right side of our current tile
			nextTile = getTile(intermediatePosn.getX() + 1, intermediatePosn.getY());
			nextPosn = new Position(intermediatePosn.getX() + 1, intermediatePosn.getY(), edgeMapping.get(currTilePosn)); 
		}
		else if (currTilePosn < 6) {
			// At the bottom of our current tile 
			nextTile = getTile(intermediatePosn.getX(), intermediatePosn.getY() + 1); 
			nextPosn = new Position(intermediatePosn.getX(), intermediatePosn.getY() + 1, edgeMapping.get(currTilePosn)); 
		}
		else {
			// At the left side of our current tile 
			nextTile = getTile(intermediatePosn.getX() - 1, intermediatePosn.getY()); 
			nextPosn = new Position(intermediatePosn.getX() - 1, intermediatePosn.getY(), edgeMapping.get(currTilePosn)); 
		}
		
		// Don't try to move onto a tile that hasn't actually been placed yet 
		if (nextTile == null) {
			return intermediatePosn; 
		}
		// Move onto the next tile and get the final position from there 
		return getFinalPosition(nextTile, nextPosn); 
	}
	
	
	// Getters and Setters
	// Get a tile at an x, y position 
	public Tile getTile(int x, int y) {
		return tileLayout[y][x]; 
	}
}
