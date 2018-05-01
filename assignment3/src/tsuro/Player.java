package tsuro;

import java.util.List;

public abstract class Player implements PlayerInterface{
	private String name; 
	private Token token; 
	
	// Constructor 
	public Player(String name, Token token) {
		this.name = name; 
		this.token = token;
	}
	
	public String getName() {
		return name; 
	}
	
	public Token getToken(){
		return token;
	}
	
	public Position placePawn(Board b) {
		// choose a random position 
		// check board to see if position is occupied
		// if not, return position, else try again 
		return new Position(0, 0, 0); 
	}
	
	public void initialize(Color c, Color[] playerColors) {
		// TODO what does this need to do? Because we assign color to token 
	}
	
	public abstract Tile playTurn(Board b, List<Tile> hand, int tilesRemaining); 
	
	public abstract void endGame(Board b, List<Color> winners);
}
