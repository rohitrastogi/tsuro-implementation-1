package assignment3;

import java.util.ArrayList;

enum Color
{
	BLUE, RED, GREEN, ORANGE, SIENNA, HOTPINK, PURPLE, DARKGREEN;	
}

public abstract class Player implements PlayerInterface{
	private String name; 
	private Color color; 
	private ArrayList<Color> playerOrder; 
	
	// Constructor 
	public Player(String n) {
		name = n; 
	}
	
	public String getName() {
		return name; 
	}
	
	public void initialize(Color c, ArrayList<Color> playerColors) {
		color = c; 
		playerOrder = playerColors; 
	}
	
	public Position placePawn(Board b) {
		// choose a random position 
		// check board to see if position is occupied
		// if not, return position, else try again 
		return new Position(0, 0, 0); 
	}
	
	public Tile playTurn(Board b, ArrayList<Tile> hand, int tilesRemaining) {
		return hand.get(0); 
	}
	
	public void endGame(Board b, ArrayList<Color> winners) {
		
	}
}
