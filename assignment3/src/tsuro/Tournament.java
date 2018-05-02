package tsuro;

public class Tournament {
	public static void main(String argv[]){
		Server s = Server.server;
		String players[] = new String[] {"rohit", "chris", "robby", "christos", "rastogi", "serpico", "findler", "dimoulas"};
		PlayerInterface pl;
		Token t;
		for (int i = 0; i<players.length; i++){
			if (i%3 == 0){
				pl = new RandomPlayer(players[i]);
			}
			else if (i%3 == 1){
				pl = new MostSymmetricPlayer(players[i]);
			}
			else {
				pl = new LeastSymmetricPlayer(players[i]);
			}
			s.registerPlayer(pl, Color.values()[i]);
		}
		s.playGame();
	}
}
