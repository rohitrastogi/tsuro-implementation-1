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
	public static BoardState playATurn(ArrayList<Tile> deck, ArrayList<SPlayer> cPlayers, ArrayList<SPlayer> ePlayers,
			Board currBoard, Tile toPlay){
	
		//~~~~~STEP 1~~~~~
		SPlayer actingPlayer = cPlayers.get(0);
		//moved acting player to tile being placed
		actingPlayer.setPosition(actingPlayer.getPosition().getAdjacentPosition());
		
		// place the tile there in the board representation 
		currBoard.placeTile(toPlay, actingPlayer.getPosition().getX(), actingPlayer.getPosition().getY());
		
		Position finalActingPosition = currBoard.getFinalPosition(toPlay, actingPlayer.getPosition());
		if (finalActingPosition.isEdgePosition()){
			eliminatePlayer(actingPlayer, cPlayers, ePlayers, deck);
		}
		
		//~~~~~STEP 2~~~~~
		List<SPlayer> adjacentPlayers = getAdjacentPlayers(actingPlayer, cPlayers);
		
		//move adjacent players to tile being placed and set final position
		for (SPlayer p : adjacentPlayers){
			p.setPosition(p.getPosition().getAdjacentPosition());
			Position finalPosition = currBoard.getFinalPosition(toPlay, p.getPosition());
			p.setPosition(finalPosition);
			if (finalPosition.isEdgePosition()){
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
			// if the game is over, generate a list of winning players 
			winningPlayers = cPlayers; 
		}
		
		//~~~~~STEP 5~~~~~
		advanceTurn(cPlayers); 
		
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
		}
		else {
			// Tiles still left in tile pile, game is not over 
			return false; 
		}
		
		// Multiple players are still playing, but no one has any tiles, game is over 
		return true; 
	}


	//removes a player from cPlayers list to ePlayers list, and shuffles eliminated player's tiles into deck
	public static void eliminatePlayer(SPlayer player, ArrayList<SPlayer>cPlayers, ArrayList<SPlayer> ePlayers, ArrayList<Tile> deck){
		player.loseDragonTile();
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
		cPlayers.add(currPlayer);
	}
	
	//
	public static void drawTile(SPlayer player, ArrayList<Tile> deck, ArrayList<SPlayer> currPlayers){
		if (deck.size() != 0) {
			player.addTile(deck.remove(0));
		}
		else {
			// no tiles left in deck, check whether any other players have the dragon tile 
			for (SPlayer p : currPlayers) {
				if (p.hasDragonTile()) {
					// someone already has the dragon tile, no tiles can be drawn 
					return; 
				}
			}
			
			// no tiles left and no player has taken the dragon tile: give the tile to this player 
			player.takeDragonTile(); 
		}
	}
	
	//returns eliminated player's tiles to the deck
	public static void addEliminatedPlayerTiles(SPlayer player, ArrayList<Tile> deck, ArrayList<SPlayer> currPlayers){
		deck.addAll(player.getTiles());
		shuffleTiles(deck);
		 
		if (getDragTilePlayerIndex(currPlayers) != -1) {
			// Someone has the dragon tile 
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
		int offset = getDragTilePlayerIndex(currPlayers); 
		currPlayers.get(offset).loseDragonTile();
		for(int i = 0; i < (currPlayers.size() * 3); i++) {
			if (deck.size() == 0){
				break; //don't want to draw dragon tile 
			}
			//System.out.println((i+offset) % currPlayers.size());
			SPlayer drawPlayer = currPlayers.get((i + offset) % currPlayers.size()); 
			if (drawPlayer.getTiles().size() < 3) {
				drawTile(drawPlayer, deck, currPlayers); 
			}
		}
	}

}
