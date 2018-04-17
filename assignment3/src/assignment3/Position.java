package assignment3;

public class Position {
	private int x;
	private int y;
	private int tilePosn;
	
	public Position(int x, int y, int tilePosn){
		this.x = x;
		this.y = y;
		this.tilePosn = tilePosn;
	}
	
	// Returns whether this is a reference to a position on the edge of a board 
	public boolean isEdgePosition() {
		if (x == 0 && (tilePosn == 6 || tilePosn == 7)) {
			return true; 
		}
		if (x == Board.TILES_PER_ROW && (tilePosn == 2 || tilePosn == 3)) {
			return true; 
		}
		if (y == 0 && (tilePosn < 3)) {
			return true; 
		}
		if (y == Board.TILES_PER_ROW && (tilePosn == 4 || tilePosn == 5)) {
			return true; 
		}
		return false; 
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
}
