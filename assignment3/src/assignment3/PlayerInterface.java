package assignment3;

import java.util.*;

interface PlayerInterface {
  // Returns the player's name 
  public String getName();
  
  // Called to indicate a game is starting.
  // The first argument is the player's color
  // and the second is all of the players'
  // colors, in the order that the game will be played.
  public void initialize(Color c, List<Color> playerColors); 
  
  // Called at the first step in a game; indicates where
  // the player wishes to place their pawn. The pawn must
  // be placed along the edge in an unoccupied space.
  public Position placePawn(Board b); 
  
  // Called to ask the player to make a move. The tiles
  // are the ones the player has, the number is the
  // count of tiles that are not yet handed out to players.
  // The result is the tile the player should place,
  // suitably rotated.
  public Tile playTurn(Board b, List<Tile> hand, int tilesRemaining); 
  
  // Called to inform the player of the final board
  // state and which players won the game.
  public void endGame(Board b, List<Color> winners);
}
