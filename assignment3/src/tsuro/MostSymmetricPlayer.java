package tsuro;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MostSymmetricPlayer extends Player {

	public MostSymmetricPlayer(String n, Token t) {
		super(n, t);
		// TODO Auto-generated constructor stub
	}
	
	public Tile playTurn(Board b, List<Tile> hand, int tilesRemaining) {
		// make a priority queue for the tiles, based on symmetry 
		PriorityQueue<Tile> queue = new PriorityQueue<Tile>(3, new MostSymmetricalComparison());
		for (Tile t : hand) {
			queue.add(t); 
		}
		
		// TODO go through queue and try rotations until one is legal 
		Tile toPlay; 
		while(queue.peek() != null) {
			toPlay = queue.poll(); 
			
			for (int i = 0; i < Tile.NUM_SIDES; i++) {
				toPlay = toPlay.rotate(i); 
				if (ServerUtils.isLegalPlay(Server.server.getSPlayer(this), b, toPlay)) {
					return toPlay; 
				}
			}
		}
		
		// Hypothetically by this point no hand should ever have an unplayable tile 
		// Since isLegalPlay checks other possible moves 
		throw new RuntimeException("Somehow playTurn for player " + this.getName() + " has a hand with no playable tile."); 
	}

	public void endGame(Board b, List<Color> winners) {
		// TODO does this need to do anything? 
	}
}

class MostSymmetricalComparison implements Comparator<Tile> {

	@Override
	public int compare(Tile o1, Tile o2) {
		if (o1.getSymmetry() < o2.getSymmetry()) {
			return -1; 
		}
		else if (o2.getSymmetry() > o1.getSymmetry()) {
			return 1; 
		}
		else {
			return 0; 
		}
	}
}
