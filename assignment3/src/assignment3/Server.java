package assignment3; 

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
	
	public Server(int numPlayers) {
		initializeGame(numPlayers); 
	}
	
	//constructor for tests only 
	public Server(Board testBoard){
		board =  testBoard;
	}
	
	// GAME INIT FUNCTIONS // 
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
			currPlayers.add(new SPlayer(myTiles, Color.values()[i], posns[i]));		
		}
	}
	
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
		tilePile.add(new Tile(new Path[] {new Path(0, 1), new Path(2, 7), new Path(3, 5), new Path(4, 7)}));
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
	
}

