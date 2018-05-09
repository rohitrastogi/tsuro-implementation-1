package tsuro;

import java.util.HashMap;
import java.util.Map;

public class Position {
	private int x;
	private int y;
	private int tilePosn;
	private boolean isPhantom = false;
	
	// Mapping between the positions on the edges of two adjacent tiles 
	public static Map<Integer, Integer> edgeMapping = new HashMap<Integer, Integer>() {{
			put(0, 5); 
			put(1, 4); 
			put(2, 7); 
			put (3, 6); 
			put (4, 1); 
			put (5, 0); 
			put (6, 3); 
			put (7, 2); 
	}};
	
	public Position(int x, int y, int tilePosn, boolean isPhantomTile){
		if (isPhantomTile){
			if (Position.isValidPhantomPosition(x, y, tilePosn)){
				this.x = x;
				this.y = y;
				this.tilePosn = tilePosn;
				this.isPhantom = isPhantomTile;
			}
			else{
				throw new IllegalArgumentException("Not a valid phantom position!");
			}
		}
		else{
			if (Position.isValidPosition(x, y, tilePosn)) {
				this.x = x;
				this.y = y;
				this.tilePosn = tilePosn;
			}
			else{
				throw new IllegalArgumentException("Not a valid position!");
			}
		}
	}
	
	public Position(int x, int y, int tilePosn){
		this(x, y, tilePosn, false);
	}
		
	public static boolean isValidPhantomPosition(int x, int y, int tilePosn){
		if (x == -1 && (y < Board.TILES_PER_ROW && y >=0) && (tilePosn == 2 || tilePosn == 3)) {
			return true; 
		}
		if (x == (Board.TILES_PER_ROW) && (y < Board.TILES_PER_ROW && y >=0) && (tilePosn == 6 || tilePosn == 7)) {
			return true; 
		}
		if (y == -1 && (x < Board.TILES_PER_ROW && x >=0) && (tilePosn == 4 || tilePosn == 5)) {
			return true; 
		}
		if (y == (Board.TILES_PER_ROW) && (x < Board.TILES_PER_ROW && x >=0) && (tilePosn == 0 || tilePosn == 1)) {
			return true; 
		}
		return false; 
	}
	
	public static boolean isValidPosition(int x, int y, int tilePosn){
		return (x < Board.TILES_PER_ROW && x >=0 && y < Board.TILES_PER_ROW && y>=0 && tilePosn <= 7 && tilePosn >=0);
	}
	
	// Returns whether this is a reference to a position on the edge of a board 
	public boolean isEdgePosition() {
		if (x == 0 && (tilePosn == 6 || tilePosn == 7)) {
			return true; 
		}
		if (x == (Board.TILES_PER_ROW - 1) && (tilePosn == 2 || tilePosn == 3)) {
			return true; 
		}
		if (y == 0 && (tilePosn < 2)) {
			return true; 
		}
		if (y == (Board.TILES_PER_ROW - 1) && (tilePosn == 4 || tilePosn == 5)) {
			return true; 
		}
		return false; 
	}

	//checks if other position is adjacent to the tile that 'this' position is on
	public boolean arePosnsAdjacent(Position other){
		// TODO: Refactor this to use getAdjacentPosition 
		//call only in this way (evil): this.arePosnsAdjacent(other)
		int xDiff = other.x - this.x;
		int yDiff = other.y - this.y;
		if (xDiff == 1 && yDiff == 0){
			if (other.tilePosn == 6 || other.tilePosn == 7) {
				return true;
			}
			return false;
		}
		else if (xDiff == -1 && yDiff == 0){
			if (other.tilePosn == 2 || other.tilePosn == 3) {
				return true;
			}
			return false;
	
		}
		else if (xDiff == 0 && yDiff == -1){
			if (other.tilePosn == 4 || other.tilePosn == 5) {
				return true;
			}
			return false;
			
		}
		else if (xDiff == 0 && yDiff == 1){
			if (other.tilePosn == 0 || other.tilePosn == 1) {
				return true;
			}
			return false;
		}
		else{
			return false;
		}
	}
	
	// Returns the position adjacent to this one 
	public Position getAdjacentPosition() {
		if (this.tilePosn == 0 || this.tilePosn == 1) {
			return new Position(this.x, this.y - 1, Position.edgeMapping.get(this.tilePosn)); 
		}
		else if (this.tilePosn == 2 || this.tilePosn == 3) {
			return new Position(this.x + 1, this.y, Position.edgeMapping.get(this.tilePosn)); 
		}
		else if (this.tilePosn == 4 || this.tilePosn == 5) {
			return new Position(this.x, this.y + 1, Position.edgeMapping.get(this.tilePosn)); 
		}
		else {
			return new Position(this.x - 1, this.y, Position.edgeMapping.get(this.tilePosn)); 
		}
	}
	
	// Getters and Setters 
	public int getX() {
		return x; 
	}
	
	public int getY() {
		return y; 
	}
	
	public int getTilePosn() {
		return tilePosn; 
	}
	
	@Override 
	public boolean equals(Object obj) {
		Position p = (Position) obj; 
		
		return ((p.x == this.x) && (p.y == this.y) && (p.tilePosn == this.tilePosn));
	}
	
	@Override
	public String toString(){
		return "(" + this.x + ", " + this.y + ", " + this.tilePosn + ").";
	}
}
