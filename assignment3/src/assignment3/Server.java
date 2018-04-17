package assignment3; 

public class Server {
	private board currBoard; 
	private ArrayList<SPlayer> currPlayers;
	private ArrayList<SPlayer> eliminatedPlayers;
	private ArrayList<Tile> tilePile = new ArrayList<Tile>();
	
	// The number of tiles a player can hold in their hand at one time 
	public static final int TILES_PER_PLAYER = 3;
	
	public Server(int numPlayers) {
		initializeGame(numPlayers); 
	}
	
	// GAME INIT FUNCTIONS // 
	public void initializeGame(int numPlayers) {
		generateTilePile(); 
		createPlayers(numPlayers);
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
	
	public BoardState playATurn(ArrayList<Tile> tilePile, ArrayList<SPlayer> currPlayers, ArrayList<SPlayer> elimPlayers,
			Board currBoard, Tile toPlay){
		SPlayer actingPlayer = currPlayers.get(0);
		
		// A list of players that are eliminated during this turn, so we don't modify any lists until the end 
		// of the method 
		ArrayList<SPlayer> toBeEliminated = new ArrayList<SPlayer>(); 
		
		// Move player's position onto the new tile being placed 
		actingPlayer.setPosn(actingPlayer.getPosn().getAdjacentPosition());
		
		// Check whether this player is eliminating themselves 
		if (isEliminationMove(toPlay, actingPlayer)){
			toBeEliminated.add(actingPlayer); 
		}
		
		// Get adjacent players and check whether they're being eliminated 
		List<SPlayer> adjacentPlayers = getAdjacentPlayers(actingPlayer);
		for (SPlayer adjacentPlayer : adjacentPlayers){
			adjacentPlayer.setPosn(adjacentPlayer.getPosn().getAdjacentPosition());
			if (isEliminationMove(toPlay, adjacentPlayer)) {
				toBeEliminated.addAll(adjacentPlayers);
			}
		}
		
		for (SPlayer eliminatedPlayer : toBeEliminated){
			currPlayers.remove(eliminatedPlayer);
			elimPlayers.add(eliminatedPlayer);
			addEliminatedPlayerTiles(eliminatedPlayer);
		}
		
		Position finalPosn = getFinalPosition(toPlay, actingPlayer.getPosn());
		actingPlayer.setPosn(finalPosn);
		
		for (SPlayer adjacentPlayer : adjacentPlayers){
			finalPosn = getFinalPosition(toPlay, adjacentPlayer.getPosn());
			actingPlayer.setPosn(finalPosn);
		}
		
		drawTile(actingPlayer);
		
		

		//first play tile
			// get position, update position for player - if elimination move, move player from currPlayer to elimPlayers
			// shuffle tiles
		//second move players with posns at adjacent tiles
			// loop through all players
			// if adjacent
			// get final posn workflow
		//drawTile(currPlayers[0]);
		// winners is empty list 
		//check if game is over, winners = currPlayers
		// return new BoardState(tilePile, currPlayers, elimPlayers, board, winners);
		return null;
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
			player.addTile(tilePile.remove(tilePile.size() - 1));
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
}

