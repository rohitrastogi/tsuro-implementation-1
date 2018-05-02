package tsuro;

import java.util.List;

public class TestPlayer extends Player{

	public TestPlayer(String n) {
		super(n);
	}

	public Tile playTurn(Board b, List<Tile> hand, int tilesRemaining) {
		// don nothing
		return null;
	}

	public void endGame(Board b, List<Color> winners) {
		// do nothing
	}
	
}
