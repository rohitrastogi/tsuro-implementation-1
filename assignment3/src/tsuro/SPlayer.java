package tsuro;

import java.util.*;

public class SPlayer {
	private PlayerInterface player;
	private List<Tile> myTiles;
	private Position posn;
	private boolean hasDragonTile; 
	
	//for testing only
	public SPlayer(List<Tile> myTiles, Position posn){
		this.myTiles = myTiles;
		this.posn = posn;
		this.hasDragonTile = false; 
	}
	
	//used for actual game play
	public SPlayer(PlayerInterface player){
		this.player = player;
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
		return (p.myTiles.equals(this.myTiles) && (p.posn.equals(this.posn)) && p.hasDragonTile == this.hasDragonTile);
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
	
	public Position getPosition(){
		Token t = player.getToken();
		return t.getPosition();
	}
	
	public PlayerInterface getPlayer(){
		return player;
	}
	
	public void setPosition(Position p){
		Token t = player.getToken();
		t.setPosition(p);
	}
	
	public Color getColor(){
		Token t = player.getToken();
		return t.getColor();
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
