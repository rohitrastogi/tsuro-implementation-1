package tsuro;

public class Tournament {
	public static void main(String argv[]){
		Server s = Server.server;
		String players[] = new String[] {"rohit", "chris", "robby", "christos", "rastogi", "serpico", "findler", "dimoulas"};
		PlayerInterface pl;
		Token t;
		for (int i = 0; i<players.length; i++){
			t = new Token(Color.values()[i]);
			if (i%3 == 0){
				pl = new RandomPlayer(players[i], t);
			}
			else if (i%3 == 1){
				pl = new MostSymmetricPlayer(players[i], t);
			}
			else {
				pl = new LeastSymmetricPlayer(players[i], t);
			}
			s.registerPlayer(pl);
		}
		s.playGame();
	}
}
