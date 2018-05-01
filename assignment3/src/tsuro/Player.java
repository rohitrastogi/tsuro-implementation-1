package tsuro;

import java.util.List;
import java.util.Random;

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
		while(true) {
			Position nextPosn = createRandomPhantomPosn(new Random()); 
			
			if (!b.isPositionTaken(nextPosn)) {
				// position isn't taken, so we're good 
				return nextPosn; 
			}
		}
	}
	
	public void initialize(Color c, Color[] playerColors) {
		// TODO what does this need to do? Because we assign color to token 
	}
	
	public abstract Tile playTurn(Board b, List<Tile> hand, int tilesRemaining); 
	
	public abstract void endGame(Board b, List<Color> winners);
	
	private Position createRandomPhantomPosn(Random r) {
		// Get a random number from 1 to 48
		int rand = r.nextInt(48) + 1; 
		int x, y, tp; 
		
		if (rand <= 12) {
			x = -1; 
			y = rand % 6; 
			tp = rand % 2 + 6; // 6 or 7 
		}
		else if (rand <= 24) {
			x = 6; 
			y = rand % 6; 
			tp = rand % 2 + 2; // 2 or 3
		}
		else if (rand <= 36) {
			y = -1; 
			x = rand % 6; 
			tp = rand % 2; // 0 or 1
		}
		else {
			y = 6; 
			x = rand % 6; 
			tp = rand % 2 + 4; // 4 or 5 
		}
		
		return new Position(x, y, tp, true); 
	}
}
