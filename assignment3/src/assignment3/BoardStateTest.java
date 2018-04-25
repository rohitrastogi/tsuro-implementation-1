package assignment3;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BoardStateTest {

	@Test
	public void testEqualsObject() {
		Tile [][] testlayout1 = {{null, (new Tile(new Path[] {new Path(0, 5), new Path(1, 4), new Path(2, 7), new Path(3, 6)})), null, null, null, null},
				{(new Tile(new Path[] {new Path(0, 7), new Path(1, 6), new Path(2, 5), new Path(3, 4)})), null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null}};
		
		Tile [][] testlayout2 = {{null, (new Tile(new Path[] {new Path(0, 5), new Path(1, 4), new Path(2, 7), new Path(3, 6)})), 
			(new Tile(new Path[] {new Path(0, 5), new Path(1, 4), new Path(2, 7), new Path(3, 6)})),
			(new Tile(new Path[] {new Path(0, 5), new Path(1, 4), new Path(2, 7), new Path(3, 6)})),
			(new Tile(new Path[] {new Path(0, 5), new Path(1, 4), new Path(2, 7), new Path(3, 6)})), 
			(new Tile(new Path[] {new Path(0, 5), new Path(1, 4), new Path(2, 7), new Path(3, 6)}))},
				{(new Tile(new Path[] {new Path(0, 7), new Path(1, 6), new Path(2, 5), new Path(3, 4)})), null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null}};
		
		Board board1 = new Board(testlayout1);
		Board board2 = new Board(testlayout1);
		Board board3 = new Board(testlayout2);
		
		List<Tile> player1hand = new ArrayList();
		List<Tile> player2hand = new ArrayList();
		List<Tile> player3hand = new ArrayList();
		player1hand.add(new Tile(new Path[] {new Path(0, 4), new Path(1, 3), new Path(2, 7), new Path(5, 6)}));
		player2hand.add(new Tile(new Path[] {new Path(0, 3), new Path(1, 7), new Path(2, 6), new Path(4, 5)}));
		player3hand.add(new Tile(new Path[] {new Path(0, 4), new Path(1, 5), new Path(2, 6), new Path(3, 7)}));
		Position player1posn = new Position(0, 0, 7);
		Position player2posn = new Position(0, 2, 6);
		Position player3posn = new Position(5, 0, 3); //eliminated position
		SPlayer player1 = new SPlayer(player1hand,Color.RED, player1posn);
		SPlayer player2 = new SPlayer(player2hand, Color.BLACK, player2posn);
		SPlayer player3 = new SPlayer(player3hand, Color.BLUE, player3posn);
		
		ArrayList<SPlayer> currPlayers = new ArrayList<SPlayer>();
		currPlayers.add(player1);
		currPlayers.add(player2);
		
		ArrayList<SPlayer> currPlayers2 = new ArrayList<SPlayer>(); 
		currPlayers2.add(player1); 
		currPlayers2.add(player2); 
		
		ArrayList<SPlayer> currPlayers3 = new ArrayList<SPlayer>(); 
		
		ArrayList<SPlayer> elimPlayers1 = new ArrayList<SPlayer>();
		ArrayList<SPlayer> elimPlayers2 = new ArrayList<SPlayer>();
		elimPlayers1.add(player3);
		elimPlayers2.add(player3); 
		
		//generate tilePile for deck argument
		ArrayList<Tile> tilePile1 = new ArrayList<Tile>();
		tilePile1.add(new Tile(new Path[] {new Path(0, 5), new Path(1, 6), new Path(2, 7), new Path(3, 4)}));
		tilePile1.add(new Tile(new Path[] {new Path(0, 5), new Path(1, 3), new Path(2, 6), new Path(4, 7)}));
		
		ArrayList<Tile> tilePile2 = new ArrayList<Tile>();
		tilePile2.add(new Tile(new Path[] {new Path(0, 5), new Path(1, 6), new Path(2, 7), new Path(3, 4)}));
		
		ArrayList<Tile> tilePile3 = new ArrayList<Tile>();
		tilePile3.add(new Tile(new Path[] {new Path(0, 5), new Path(1, 6), new Path(2, 7), new Path(3, 4)}));
		tilePile3.add(new Tile(new Path[] {new Path(0, 5), new Path(1, 3), new Path(2, 6), new Path(4, 7)}));
		
		assertTrue(new BoardState(tilePile1, currPlayers, elimPlayers1, board1, elimPlayers1).equals(
				new BoardState(tilePile3, currPlayers2, elimPlayers2, board2, elimPlayers1))); 
		
		assertFalse(new BoardState(tilePile2, currPlayers, elimPlayers1, board1, elimPlayers1).equals(
				new BoardState(tilePile3, currPlayers2, elimPlayers2, board2, elimPlayers1))); 
		
		assertFalse(new BoardState(tilePile1, currPlayers, elimPlayers1, board1, elimPlayers1).equals(
				new BoardState(tilePile3, currPlayers2, elimPlayers2, board3, elimPlayers1))); 
		
		assertFalse(new BoardState(tilePile1, currPlayers, elimPlayers1, board1, elimPlayers1).equals(
				new BoardState(tilePile3, currPlayers3, elimPlayers2, board2, elimPlayers1))); 
	}
}
