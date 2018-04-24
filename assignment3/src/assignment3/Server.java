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
		shuffleTiles();
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
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 3), new Tuple(4, 5), new Tuple(6, 7)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 4), new Tuple(3, 6), new Tuple(5, 7)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 6), new Tuple(1, 5), new Tuple(2, 4), new Tuple(3, 7)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 4), new Tuple(2, 7), new Tuple(3, 6)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 2), new Tuple(1, 4), new Tuple(3, 7), new Tuple(5, 6)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 4), new Tuple(1, 7), new Tuple(2, 3), new Tuple(5, 6)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 6), new Tuple(3, 7), new Tuple(4, 5)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 2), new Tuple(1, 6), new Tuple(3, 7), new Tuple(4, 5)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 4), new Tuple(1, 5), new Tuple(2, 6), new Tuple(3, 7)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 7), new Tuple(3, 4), new Tuple(5, 6)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 2), new Tuple(1, 7), new Tuple(3, 4), new Tuple(5, 6)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 3), new Tuple(1, 5), new Tuple(2, 7), new Tuple(4, 6)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 4), new Tuple(1, 3), new Tuple(2, 7), new Tuple(5, 6)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 3), new Tuple(1, 7), new Tuple(2, 6), new Tuple(4, 5)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 5), new Tuple(3, 6), new Tuple(4, 7)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 3), new Tuple(1, 6), new Tuple(2, 5), new Tuple(4, 7)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 7), new Tuple(3, 5), new Tuple(4, 7)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 7), new Tuple(1, 6), new Tuple(2, 3), new Tuple(4, 5)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 7), new Tuple(1, 2), new Tuple(3, 4), new Tuple(5, 6)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 2), new Tuple(1, 4), new Tuple(3, 6), new Tuple(5, 7)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 7), new Tuple(1, 3), new Tuple(2, 5), new Tuple(4, 6)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 7), new Tuple(1, 5), new Tuple(2, 6), new Tuple(3, 4)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 4), new Tuple(1, 5), new Tuple(2, 7), new Tuple(3, 6)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 4), new Tuple(3, 5), new Tuple(6, 7)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 2), new Tuple(1, 7), new Tuple(3, 5), new Tuple(4, 6)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 7), new Tuple(1, 5), new Tuple(2, 3), new Tuple(4, 6)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 4), new Tuple(1, 3), new Tuple(2, 6), new Tuple(5, 7)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 6), new Tuple(1, 3), new Tuple(2, 5), new Tuple(4, 7)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 7), new Tuple(3, 6), new Tuple(4, 5)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 3), new Tuple(1, 2), new Tuple(4, 6), new Tuple(5, 7)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 3), new Tuple(1, 5), new Tuple(2, 6), new Tuple(4, 7)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 7), new Tuple(1, 6), new Tuple(2, 5), new Tuple(3, 4)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 2), new Tuple(1, 3), new Tuple(4, 6), new Tuple(5, 7)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 6), new Tuple(2, 7), new Tuple(3, 4)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 3), new Tuple(2, 6), new Tuple(4, 7)}));
	}
	
	
	// TURN FUNCTIONS // 
	// Makes sure a play is legal for a given player, board, and tile, as defined in the homework spec 
	public boolean isLegalPlay(SPlayer player, Board board, Tile tile) {
		return (player.hasTile(tile)) && (board.isValidMove(tile, player));
	} 
	
	public void eliminatePlayer(SPlayer player, ArrayList<SPlayer>currentPlayers, ArrayList<SPlayer> eliminatedPlayers){
		currentPlayers.remove(player);
		eliminatedPlayers.add(player);
		addEliminatedPlayerTiles(player);
	}
	/**Turn is in 5 phases:
	 * 1. Place tile, and move curr player accordingly (check for eliminations)
	 * 2. Move players on adjacent tiles accordingly (check for eliminations)
	 * 3. Curr player draws a tile
	 * 4. Check if game is over 
	 * 5. Bookkeep to prepare next turn
	**/
	
	public BoardState playATurn(ArrayList<Tile> tilePile, ArrayList<SPlayer> cPlayers, ArrayList<SPlayer> ePlayers,
			Board currBoard, Tile toPlay){
		
		Board newBoard = new Board(currBoard.getLayout()); 
		
		//~~~~~STEP 1~~~~~
		SPlayer actingPlayer = cPlayers.get(0);
		//moved acting player to tile being placed
		actingPlayer.setPosn(actingPlayer.getPosn().getAdjacentPosition());
		
		// place the tile there in the board representation 
		newBoard.placeTile(toPlay, actingPlayer.getPosn().getX(), actingPlayer.getPosn().getY());
		
		Position finalPosition = currBoard.getFinalPosition(toPlay, actingPlayer.getPosn());
		actingPlayer.setPosn(finalPosition);
		if (finalPosition.isEdgePosition()){
			eliminatePlayer(actingPlayer, cPlayers, ePlayers);
		}
		
		//~~~~~STEP 2~~~~~
		List<SPlayer> adjacentPlayers = getAdjacentPlayers(actingPlayer);
		
		//move adjacent players to tile being placed and set final position
		for (SPlayer p : adjacentPlayers){
			p.setPosn(p.getPosn().getAdjacentPosition());
			finalPosition = currBoard.getFinalPosition(toPlay, p.getPosn());
			p.setPosn(finalPosition);
			if (finalPosition.isEdgePosition()){
				eliminatePlayer(p, cPlayers, ePlayers); //should not mutate 
			}
		}
		
		//~~~~~STEP 3~~~~~
		drawTile(actingPlayer); //should not mutate 
		
		
		//~~~~~STEP 4~~~~~
		// See if the game is over 
		ArrayList<SPlayer> winningPlayers = new ArrayList<SPlayer>(); 
		if (isGameOver()) {
			// if the game is over, generate a list of winning players 
			winningPlayers = currPlayers; 
		}
		
		//~~~~~STEP 5~~~~~
		advanceTurn(); //should not mutate
		
		// Generate a new board state and return
		return (new BoardState(tilePile, currPlayers, elimPlayers, newBoard, winningPlayers));
	}
	
	// returns true if the game either:
	//   a. Has only one remaining player 
	//   b. Has no remaining tiles to be placed 
	public boolean isGameOver(){
		if (currPlayers.size() <= 1) 
			return true; 
		
		if (tilePile.size() <= 0) {
			// No tiles left in pile, check if any players have a tile 
			for(SPlayer p : currPlayers) {
				if (p.hasTiles()) {
					// Some player, somewhere, has a tile, so we're not done 
					return false; 
				}
			}
		}
		else {
			// Tiles still left in tile pile, game is not over 
			return false; 
		}
		
		// Multiple players are still playing, but no one has any tiles, game is over 
		return true; 
	}
	
	public void advanceTurn(){
		SPlayer currPlayer = currPlayers.remove(0);
		currPlayers.add(currPlayer);
	}
	
	public List<SPlayer> getAdjacentPlayers(SPlayer player){
		List<SPlayer> adjacentPlayers = new ArrayList<SPlayer>();
		for (SPlayer other : currPlayers){
			if (other == player){
				continue;
			}
			else {
				if (player.getPosn().arePosnsAdjacent(other.getPosn())){
					adjacentPlayers.add(other);
				}
			}
		}
		return adjacentPlayers;
	}
	
	
	// TILE HELPER FUNCTIONS // 
	public void drawTile(SPlayer player){
		// TODO: Add dragon tile functionality (currently if there are no tiles left in the tile pile, we do nothing)
		if (tilePile.size() != 0) {
			player.addTile(tilePile.remove(0));
		}
	}
	
	// When a player is eliminated, all of their held tiles should be returned to the tile pile 
	public void addEliminatedPlayerTiles(SPlayer p){
		tilePile.addAll(p.getTiles());
		shuffleTiles();
	}
	
	public void shuffleTiles(){
		Random rand = new Random();
		Collections.shuffle(tilePile, rand);
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

