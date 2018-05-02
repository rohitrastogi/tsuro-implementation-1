package tsuro;

import java.util.*;

public class SPlayer {
	private PlayerInterface player;
	private List<Tile> myTiles;
	private boolean hasDragonTile; 
	private Token token;
	
	//for testing only
	public SPlayer(List<Tile> myTiles, Position posn, String name, Color color){
		this.myTiles = myTiles;
		this.hasDragonTile = false; 
		
		Token testPlayerToken = new Token(color);
		this.token = testPlayerToken;
		PlayerInterface testPlayer = new TestPlayer(name);
		this.player = testPlayer;
		this.setPosition(posn);
	}
	
	//used for actual game play
	public SPlayer(PlayerInterface player, Color c){
		this.player = player;
		Token token = new Token(c);
		this.token = token;
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
		return (p.myTiles.equals(this.myTiles) && (p.getPosition().equals(this.getPosition())) 
				&& p.hasDragonTile == this.hasDragonTile) && (p.getColor() == this.getColor())
				&& p.getPlayer().getName().equals(this.getPlayer().getName());
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
//		Token t = player.getToken();
		return token.getPosition();
	}
	
	public PlayerInterface getPlayer(){
		return player;
	}
	
	public Token getToken(){
		return token;
	}
	
	public void setPosition(Position p){
//		Token t = player.getToken();
		token.setPosition(p);
	}
	
	public Color getColor(){
//		Token t = player.getToken();
		return token.getColor();
	}
	
	public void addTile(Tile t){
		myTiles.add(t);
	}
	
	public void takeDragonTile() {
		this.hasDragonTile = true; 
	}
	
	public void removeTileFromHand(Tile toRemove){
		Tile removed = null;
		for (Tile t: myTiles){
			Tile rotated = t.rotate(0);
			if (rotated.equals(toRemove.rotate(0))){
				removed = t;
			}
		}
		if (removed != null){
			myTiles.remove(removed);
		}
		else{
			throw new IllegalArgumentException("Trying to delete a tile not currently held!");
		}
	}
	
	public void loseDragonTile() {
		this.hasDragonTile = false; 
	}
	
	public boolean hasDragonTile() {
		return this.hasDragonTile; 
	}
	
	@Override
	public String toString(){
		return "\nName: " + this.getPlayer().getName() + " Color: " + 
				this.getColor() + " Position: " + this.getPosition() + "\n" + 
				"Tiles: " + myTiles;
	}
}
