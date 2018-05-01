package tsuro;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LeastSymmetricPlayer extends Player {
	
	public LeastSymmetricPlayer(String n, Token t) {
		super(n, t);
		// TODO Auto-generated constructor stub
	}

	@Override 
	public Tile playTurn(Board b, List<Tile> hand, int tilesRemaining) {
		// make a priority queue for the tiles, based on symmetry 
		PriorityQueue<Tile> queue = new PriorityQueue<Tile>(3, new LeastSymmetricalComparison());
		
		// TODO go through queue and try rotations until one is legal 
		return hand.get(0); 
	}
}

class LeastSymmetricalComparison implements Comparator<Tile> {

	@Override
	public int compare(Tile o1, Tile o2) {
		if (o1.getSymmetry() < o2.getSymmetry()) {
			return 1; 
		}
		else if (o2.getSymmetry() > o1.getSymmetry()) {
			return -1; 
		}
		else {
			return 0; 
		}
	}
}