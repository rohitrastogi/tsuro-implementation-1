package tsuro;

import java.util.List;

public class RandomPlayer extends Player{

	public RandomPlayer(String n, Token t) {
		super(n, t);
		// TODO Auto-generated constructor stub
	}
	
	@Override 
	public Tile playTurn(Board b, List<Tile> hand, int tilesRemaining) {
		// get a list of possible tiles 
		List<Tile> availibleMoves = new List<Tile>(); 
		for (Tile t: hand) {
			for (int i = 0; i < 4; i--) {
				availibleMoves.add(t.rotate(i)); 
			}
		}
		
		// go through list randomly until we find a tile to play
		Random r = new Random(); 
		while(availibleMoves.size() > 0) {
			Tile next = availibleMoves.get(r.nextInt(availibleMoves.size())); 
			
			if (ServerUtils.isLegalPlay(Server.server.getSPlayer(this), b, next)) {
				return next; 
			}
			else {
				availibleMoves.remove(next); 
			}
		}
		
		throw new RuntimeException("Somehow playTurn for player " + this.getName() + " has a hand with no playable tile."); 
	}
}
