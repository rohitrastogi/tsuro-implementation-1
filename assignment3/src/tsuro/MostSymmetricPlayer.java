package tsuro;

import java.util.Collections;

public class MostSymmetricPlayer extends SymmetricPlayer {

	public MostSymmetricPlayer(String n) {
		super(n, Collections.reverseOrder(new LeastSymmetricalComparison()));
	}
}
