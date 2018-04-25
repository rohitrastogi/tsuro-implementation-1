package assignment3;

import java.util.*;

enum Color
{
	BLUE, RED, YELLOW, ORANGE, GREEN, PINK, PURPLE, BLACK;	
}

public class SPlayer {
	private List<Tile> myTiles;
	private Color color;
	private Position posn;
	private boolean hasDragonTile; 
	
	public SPlayer(List<Tile> myTiles, Color color, Position posn){
		this.myTiles = myTiles;
		this.color = color;
		this.posn = posn;
		this.hasDragonTile = false; 
	}
	
	// Checks whether a player has a certain tile in their possession 
	public boolean hasTile(Tile tile) {
		// loop through tiles in player's possession, checking for array equality between path values 
		for(Tile t : myTiles) {
			if (Arrays.equals(t.getPaths(), tile.getPaths())) {
				// If two tiles have the same paths, they're the same tile 
				return true; 
			}
		}
		return false;
	}
	
	@Override 
	public boolean equals(Object obj) {
		SPlayer p = (SPlayer) obj; 
		return (p.myTiles.equals(this.myTiles) && (p.color == this.color) && (p.posn.equals(this.posn)) && p.hasDragonTile == this.hasDragonTile);
	}
	
	// Checks whether a player is currently holding any tiles 
	public boolean hasTiles() {
		return (myTiles.size() > 0); 
	}
	
	// Getters and Setters 
	public List<Tile> getTiles() {
		return myTiles; 
	}
	
	public void setTiles(ArrayList<Tile> newHand) {
		myTiles = newHand; 
	}
	
	public Position getPosn(){
		return posn;
	}
	
	public void setPosn(Position p){
		posn = p;
	}
	
	public void addTile(Tile t){
		myTiles.add(t);
	}
	
	public void takeDragonTile() {
		this.hasDragonTile = true; 
	}
	
	public void loseDragonTile() {
		this.hasDragonTile = false; 
	}
	
	public boolean hasDragonTile() {
		return this.hasDragonTile; 
	}
}
