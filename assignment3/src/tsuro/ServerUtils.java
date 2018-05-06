package tsuro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//class that holds server utility methods

public class ServerUtils {
	
	//prevent instantiation of ServerUtils class
	private ServerUtils() {}
	
	//~~~~~~TURN FUNCTIONS~~~~~~ // 
	// Makes sure a play is legal for a given player, board, and tile, as defined in the homework spec 
	public static boolean isLegalPlay(SPlayer player, Board board, Tile tile) {
		return (player.hasTile(tile)) && (board.isValidMove(tile, player));
	} 
	
	
	/**
	 * Assumption: Copies of server state are passed from server class as arguments to ServerUtil playATurn()
	 * playATurnTurn occurs in 5 phases:
	 * 1. Place tile, and move curr player accordingly (check for eliminations)
	 * 2. Move players on adjacent tiles accordingly (check for eliminations)
	 * 3. Curr player draws a tile
	 * 4. Check if game is over 
	 * 5. Bookkeep to prepare next turn
	**/
	//TODO pass dragon tile if player with dragon tile is eliminated [causing exception]
	public static BoardState playATurn(ArrayList<Tile> deck, ArrayList<SPlayer> cPlayers, ArrayList<SPlayer> ePlayers,
			Board currBoard, Tile toPlay){
	
		//~~~~~STEP 1~~~~~
		SPlayer actingPlayer = cPlayers.get(0);
		//moved acting player to tile being placed
		System.out.print(actingPlayer.getPlayer().getName() + " is at " + actingPlayer.getToken().getPosition().toString());
		actingPlayer.setPosition(actingPlayer.getPosition().getAdjacentPosition());
		System.out.println(" They have moved onto " + actingPlayer.getToken().getPosition());
		
		// place the tile 
		System.out.println(actingPlayer.getPlayer().getName() + " places their tile."); 
		currBoard.placeTile(toPlay, actingPlayer.getPosition().getX(), actingPlayer.getPosition().getY());
		
		Position finalActingPosition = currBoard.getFinalPosition(toPlay, actingPlayer.getPosition());
		System.out.println(actingPlayer.getPlayer().getName() + " moves to " + finalActingPosition);
		if (finalActingPosition.isEdgePosition()){
			System.out.println(actingPlayer.getPlayer().getName() + " has eliminated themself!");
			eliminatePlayer(actingPlayer, cPlayers, ePlayers, deck);
		}
		
		//~~~~~STEP 2~~~~~
		List<SPlayer> adjacentPlayers = getAdjacentPlayers(actingPlayer, cPlayers);
		
		//move adjacent players to tile being placed and set final position
		for (SPlayer p : adjacentPlayers){
			p.setPosition(p.getPosition().getAdjacentPosition());
			Position finalPosition = currBoard.getFinalPosition(toPlay, p.getPosition());
			System.out.print("\n" + p.getPlayer().getName() + " moves to " + finalPosition);
			p.setPosition(finalPosition);
			if (finalPosition.isEdgePosition()){
				System.out.print(" This move eliminates them!");
				eliminatePlayer(p, cPlayers, ePlayers, deck);  
			}
		}
		
		actingPlayer.setPosition(finalActingPosition);
		
		//~~~~~STEP 3~~~~~
		//only draw a tile if you haven't been eliminated 
		if (!ePlayers.contains(actingPlayer)){
			drawTile(actingPlayer, deck, cPlayers); 
		}
		
		//~~~~~STEP 4~~~~~
		// See if the game is over 
		ArrayList<SPlayer> winningPlayers = new ArrayList<SPlayer>(); 
		if (isGameOver(cPlayers, deck)) {
			System.out.println("The game has ended!");
			// if the game is over, generate a list of winning players 
			winningPlayers = cPlayers; 
		}
		//~~~~~STEP 5~~~~~
		// only advance player if acting player is not eliminated - avoids double popping from currList
		else if (!ePlayers.contains(actingPlayer)){
			advanceTurn(cPlayers); 
		}
		
		// Generate a new board state and return
		return (new BoardState(deck, cPlayers, ePlayers, currBoard, winningPlayers));
	}
	
	//~~~~~~GAME HELPER FUNCTIONS (for tile functions)~~~~~~//
	// returns true if the game either:
	//   a. Has only one remaining player 
	//   b. Has no remaining tiles to be placed 
	public static boolean isGameOver(ArrayList<SPlayer> cPlayers, ArrayList<Tile> deck){
		if (cPlayers.size() <= 1) 
			return true; 
		
		if (deck.size() <= 0) {
			// No tiles left in pile, check if any players have a tile 
			for(SPlayer p : cPlayers) {
				if (p.hasTiles()) {
					// Some player, somewhere, has a tile, so we're not done 
					return false; 
				}
			}
			//nobody has any tiles left
			return true;
		}
		else {
			// Tiles still left in tile pile, game is not over 
			return false; 
		}
	}


	//removes a player from cPlayers list to ePlayers list, and shuffles eliminated player's tiles into deck
	public static void eliminatePlayer(SPlayer player, ArrayList<SPlayer>cPlayers, ArrayList<SPlayer> ePlayers, ArrayList<Tile> deck){		
		//if player being eliminated has dragon tile, pass it clockwise to first player after it with < 3 tiles
		if (player.hasDragonTile()){
			player.loseDragonTile();
			int playerIndex = cPlayers.indexOf(player);
			for (int i = 0; i<cPlayers.size() - 1; i++){
				int startIndex = i + playerIndex + 1;
				SPlayer currPlayer = cPlayers.get(startIndex % cPlayers.size());
				if (currPlayer.getTiles().size() < 3){
					currPlayer.takeDragonTile();
					System.out.println("Dragon Tile is passed to " + currPlayer.toString() + ".");
					break;
				}
			}
		}
		//remove player from current players list, and add to eliminated players list
		cPlayers.remove(player);
		ePlayers.add(player);
		
		addEliminatedPlayerTiles(player, deck, cPlayers);	
	}
	
	//returns a list of all players that can be moved as a result of a new tile being placed
	public static List<SPlayer> getAdjacentPlayers(SPlayer player, ArrayList<SPlayer> cPlayers){
		List<SPlayer> adjacentPlayers = new ArrayList<SPlayer>();
		for (SPlayer other : cPlayers){
			if (other == player){
				continue;
			}
			else {
				if (player.getPosition().arePosnsAdjacent(other.getPosition())){
					adjacentPlayers.add(other);
				}
			}
		}
		return adjacentPlayers;
	}
	
	//updates cPlayers list to maintain turn order
	public static void advanceTurn(ArrayList<SPlayer> cPlayers){
		SPlayer currPlayer = cPlayers.remove(0);
		System.out.println("The turn advances to " + cPlayers.get(0).getPlayer().getName() + ".\n");
		cPlayers.add(currPlayer);
	}
	
	public static void drawTile(SPlayer player, ArrayList<Tile> deck, ArrayList<SPlayer> currPlayers){
		if (deck.size() != 0) {
			System.out.println(player.getPlayer().getName() + " draws: " + deck.get(0)); 
			player.addTile(deck.remove(0));
		}
		else {
			System.out.println(player.getPlayer().getName() + " attempts to draw the Dragon Tile... ");
			// no tiles left in deck, check whether any other players have the dragon tile 
			for (SPlayer p : currPlayers) {
				if (p.hasDragonTile()) {
					System.out.println("but " + p.getPlayer().getName() + " already has it!");
					// someone already has the dragon tile, no tiles can be drawn 
					return; 
				}
			}
			
			// no tiles left and no player has taken the dragon tile: give the tile to this player 
			System.out.println("and succeeds!");
			player.takeDragonTile(); 
		}
	}
	
	//returns eliminated player's tiles to the deck
	public static void addEliminatedPlayerTiles(SPlayer player, ArrayList<Tile> deck, ArrayList<SPlayer> currPlayers){
		deck.addAll(player.getTiles());
		System.out.println(" After " + player.getPlayer().getName() + " was eliminated, " + 
				player.getTiles().size() + " tiles were added to the deck.");
		shuffleTiles(deck);
		 
		if (getDragTilePlayerIndex(currPlayers) != -1) {
			drawLoop(deck, currPlayers); 
		}
	}
	
	//shuffles tiles
	public static void shuffleTiles(ArrayList<Tile> deck){
		Random rand = new Random();
		Collections.shuffle(deck, rand);
	}
	
	// Get the index of the current player holding the dragon tile 
	public static int getDragTilePlayerIndex(ArrayList<SPlayer> players) {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).hasDragonTile()) {
				return i; 
			}
		}
		// -1 means no one has the dragon tile 
		return -1; 
	}
	
	// Draw from pile until everyone has three tiles
	// TODO maybe put this stuff in a Deck Class
	public static void drawLoop(ArrayList<Tile> deck, ArrayList<SPlayer> currPlayers) {
		System.out.println("Starting Draw Loop...");
		int offset = getDragTilePlayerIndex(currPlayers); 
		currPlayers.get(offset).loseDragonTile();
		System.out.println(currPlayers.get(offset).getPlayer().getName() + " gives up dragon tile!");
		for(int i = 0; i < (currPlayers.size() * 3); i++) {
			//DO WRAPAROUND AND GET DRAGON TILE WHEN NO TILES ARE LEFT IN TILEPILE
			if (deck.size() == 0 && getDragTilePlayerIndex(currPlayers) != -1){
				break; //no need to keep looping
			}
			SPlayer drawPlayer = currPlayers.get((i + offset) % currPlayers.size()); 
			if (drawPlayer.getTiles().size() < 3) {
				drawTile(drawPlayer, deck, currPlayers); 
			}
		}
		System.out.println("Ending Draw Loop...");
	}

}
