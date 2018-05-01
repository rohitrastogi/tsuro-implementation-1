package assignment3;

public class Tournament {
	public static void main(String argv[]){
		Server s = Server.server;
		String players[] = new String[] {"rohit", "chris", "robby", "christos", "rastogi", "serpico", "findler", "dimoulas"};
	
		for (int i = 0; i<players.length; i++){
			PlayerInterface pl;
			if (i%3 == 0){
				pl = new RandomPlayer(players[i]);
			}
			else if (i%3 == 1){
				pl = new MostSymmetricPlayer(players[i]);
			}
			else {
				pl = new LeastSymmetricPlayer(players[i]);
			}
			s.registerPlayer(pl);
		}
		s.playGame();
	}
}
