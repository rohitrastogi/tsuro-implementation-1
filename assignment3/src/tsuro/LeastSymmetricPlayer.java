package tsuro;

public class LeastSymmetricPlayer extends SymmetricPlayer {

	public LeastSymmetricPlayer(String n) {
		super(n, new LeastSymmetricalComparison());
	}
}
