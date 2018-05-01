package tsuro; 

import java.util.*; 

public class Server {
	private Board board; 
	private ArrayList<SPlayer> currPlayers;
	private ArrayList<SPlayer> elimPlayers;
	private ArrayList<Tile> tilePile = new ArrayList<Tile>();
	
	// The number of tiles a player can hold in their hand at one time 
	public static final int TILES_PER_PLAYER = 3;
	
	Position[] posns = {new Position(0, 0, 5), new Position(0, 3, 6), new Position(0, 5, 5), new Position(5, 0, 3), new Position(5, 3, 3),
			new Position(5, 5, 5), new Position(0, 3, 1), new Position(0, 5, 1)};
	
	
	private Server() {};
	
	public static Server server = new Server();
	
	// GAME INIT FUNCTIONS // 
	public void registerPlayer(PlayerInterface p){
		currPlayers.add(new SPlayer(p));
	}
	
	public void initializeServer(){
		generateTilePile();
		elimPlayers = new ArrayList<SPlayer>();
		initializePlayers();
		board = new Board(); 
	}
	
	public void initializePlayers(){
		ServerUtils.shuffleTiles(tilePile);
		for (int i=0; i<currPlayers.size(); i++){
			PlayerInterface player = currPlayers.get(i).getPlayer();
			//TODO clean up call to initialize if possible?
			player.initialize(currPlayers.get(i).getColor(), Color.values());
			ArrayList<Tile> myTiles = new ArrayList<Tile>(tilePile.subList(0, TILES_PER_PLAYER));
			for (int j=0; j<TILES_PER_PLAYER; j++){
				tilePile.remove(0);
			}
			currPlayers.get(i).setTiles(myTiles);
			currPlayers.get(i).setPosition(player.placePawn(board));
		}
	}
	
	public ArrayList<SPlayer> playGame(){ 
		BoardState state = null;
		//setup server state
		initializeServer();
		
		//main game loop
		while(!ServerUtils.isGameOver(currPlayers, tilePile)){
			SPlayer currPlayer = currPlayers.get(0);
			PlayerInterface currPlayerInterface = currPlayer.getPlayer();
			Tile toPlay = currPlayerInterface.playTurn(board, currPlayer.getTiles(), 0);//why is tiles remaining important?
			state = ServerUtils.playATurn(tilePile, currPlayers, elimPlayers, board, toPlay); //mutates
		}
		//when game is over extract winners from boardState
		ArrayList<SPlayer> winners = state.getWinners();
		
		//extract winner colors from winners list
		ArrayList<Color> winnerColors = new ArrayList<Color>();
		for (int i=0; i< winners.size(); i++){
			winnerColors.add(winners.get(i).getColor());
		}
		
		//call endgame on all players and pass in winner colors
		for (int i = 0; i< currPlayers.size(); i++){ 
			PlayerInterface p = currPlayers.get(i).getPlayer();
			p.endGame(board, winnerColors); 
		}
		
		for (int i=0; i<elimPlayers.size(); i++){
			PlayerInterface p = elimPlayers.get(i).getPlayer();
			p.endGame(board, winnerColors); 
		}
		return winners;
	}
	
	// Getters and Setters
	public ArrayList<Tile> getTilePile() {
		return tilePile; 
	}
	
	public void setTilePile(ArrayList<Tile> newTilePile) {
		tilePile = newTilePile; 
	}
	
	public ArrayList<SPlayer> getCurrPlayers() {
		return currPlayers; 
	}
	
	public ArrayList<SPlayer> getElimPlayers() {
		return elimPlayers; 
	}
	
	public void setCurrPlayers(ArrayList<SPlayer> currPlayers){
		this.currPlayers = currPlayers;
	}
	
	public void setElimPlayers(ArrayList<SPlayer> elimPlayers){
		this.elimPlayers = elimPlayers;
	}
	
	// Gets the SPlayer associated with a given Player 
	public SPlayer getSPlayer(PlayerInterface player) {
		for (SPlayer sp : currPlayers) {
			if (sp.getColor() == player.getToken().getColor()) {
				return sp; 
			}
		}
		
		// TODO throw an exception somewhere 
		return null; 
	}
	
	// Other helpers
	// Makes 35 unique Tiles and adds each one to the tile pile 
	public void generateTilePile(){
		tilePile.add(new Tile(new Path[] {new Path(0, 1), new Path(2, 3), new Path(4, 5), new Path(6, 7)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 1), new Path(2, 4), new Path(3, 6), new Path(5, 7)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 6), new Path(1, 5), new Path(2, 4), new Path(3, 7)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 5), new Path(1, 4), new Path(2, 7), new Path(3, 6)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 2), new Path(1, 4), new Path(3, 7), new Path(5, 6)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 4), new Path(1, 7), new Path(2, 3), new Path(5, 6)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 1), new Path(2, 6), new Path(3, 7), new Path(4, 5)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 2), new Path(1, 6), new Path(3, 7), new Path(4, 5)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 4), new Path(1, 5), new Path(2, 6), new Path(3, 7)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 1), new Path(2, 7), new Path(3, 4), new Path(5, 6)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 2), new Path(1, 7), new Path(3, 4), new Path(5, 6)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 3), new Path(1, 5), new Path(2, 7), new Path(4, 6)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 4), new Path(1, 3), new Path(2, 7), new Path(5, 6)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 3), new Path(1, 7), new Path(2, 6), new Path(4, 5)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 1), new Path(2, 5), new Path(3, 6), new Path(4, 7)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 3), new Path(1, 6), new Path(2, 5), new Path(4, 7)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 1), new Path(2, 7), new Path(3, 5), new Path(4, 6)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 7), new Path(1, 6), new Path(2, 3), new Path(4, 5)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 7), new Path(1, 2), new Path(3, 4), new Path(5, 6)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 2), new Path(1, 4), new Path(3, 6), new Path(5, 7)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 7), new Path(1, 3), new Path(2, 5), new Path(4, 6)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 7), new Path(1, 5), new Path(2, 6), new Path(3, 4)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 4), new Path(1, 5), new Path(2, 7), new Path(3, 6)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 1), new Path(2, 4), new Path(3, 5), new Path(6, 7)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 2), new Path(1, 7), new Path(3, 5), new Path(4, 6)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 7), new Path(1, 5), new Path(2, 3), new Path(4, 6)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 4), new Path(1, 3), new Path(2, 6), new Path(5, 7)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 6), new Path(1, 3), new Path(2, 5), new Path(4, 7)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 1), new Path(2, 7), new Path(3, 6), new Path(4, 5)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 3), new Path(1, 2), new Path(4, 6), new Path(5, 7)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 3), new Path(1, 5), new Path(2, 6), new Path(4, 7)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 7), new Path(1, 6), new Path(2, 5), new Path(3, 4)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 2), new Path(1, 3), new Path(4, 6), new Path(5, 7)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 5), new Path(1, 6), new Path(2, 7), new Path(3, 4)}));
		tilePile.add(new Tile(new Path[] {new Path(0, 5), new Path(1, 3), new Path(2, 6), new Path(4, 7)}));
	}
	
	//~~~~~~~FOR TESTS ONLY~~~~~~~~~
	
	//constructor for tests only
	public Server(int numPlayers) {
		initializeGame(numPlayers); 
	}
	
	//constructor for tests only 
	public Server(Board testBoard){
		board =  testBoard;
	}
	public void initializeGame(int numPlayers) {
		generateTilePile(); 
		currPlayers = new ArrayList<SPlayer>(); 
		elimPlayers = new ArrayList<SPlayer>(); 
		createPlayers(numPlayers);
		board = new Board(); 
	}
	
	public void createPlayers(int num){
		ServerUtils.shuffleTiles(tilePile);
		for(int i=0; i<num; i++){
			ArrayList<Tile> myTiles = new ArrayList<Tile>(tilePile.subList(0, TILES_PER_PLAYER));
			for (int j=0; j<TILES_PER_PLAYER; j++){
				tilePile.remove(0);
			}
			currPlayers.add(new SPlayer(myTiles, posns[i]));		
		}
	}
}

