package assignment3;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ServerUtilsTest {
	private Server testServer1;

	@Test
	public void testIsLegalPlay() {
		fail("Not yet implemented");
	}
	
	@Test
	//tests edge move, moving several tiles, and also playing a rotated tile
	public void testPlayATurn1() {
		
		//create board argument
		Tile [][] testLayout = {{null, (new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 4), new Tuple(2, 7), new Tuple(3, 6)})), 
			(new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 4), new Tuple(2, 7), new Tuple(3, 6)})),
			(new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 4), new Tuple(2, 7), new Tuple(3, 6)})),
			(new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 4), new Tuple(2, 7), new Tuple(3, 6)})), 
			null},
				{null, null, null, null, null, null},
				{(new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 7), new Tuple(3, 6), new Tuple(4, 5)})), null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null}};
		Board testBoard = new Board(testLayout);
		
		//generate tile argument
		Tile toPlay = new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 4), new Tuple(2, 3), new Tuple(6, 7)}, 1);
		
		//generate players for currPlayers argument
		List<Tile> player1hand = new ArrayList();
		List<Tile> player2hand = new ArrayList();
		player1hand.add(new Tile(new Tuple[] {new Tuple(0, 4), new Tuple(1, 3), new Tuple(2, 7), new Tuple(5, 6)}));
		player2hand.add(new Tile(new Tuple[] {new Tuple(0, 3), new Tuple(1, 7), new Tuple(2, 6), new Tuple(4, 5)}));
		Position player1posn = new Position(-1, 0, 2); //lmao
		Position player2posn = new Position(0, 2, 3);
		SPlayer player1 = new SPlayer(player1hand,Color.RED, player1posn);
		SPlayer player2 = new SPlayer(player2hand, Color.BLACK, player2posn);
		
		ArrayList<SPlayer> currPlayers = new ArrayList<SPlayer>();
		currPlayers.add(player1);
		currPlayers.add(player2);
		
		//generate tilePile for deck argument
		ArrayList<Tile> tilePile = new ArrayList<Tile>();
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 6), new Tuple(2, 7), new Tuple(3, 4)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 3), new Tuple(2, 6), new Tuple(4, 7)}));
		
		//generate new board layout after move
		Tile [][] newLayout = {{(new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 4), new Tuple(2, 3), new Tuple(6, 7)}, 1)),
				(new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 4), new Tuple(2, 7), new Tuple(3, 6)})), 
			(new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 4), new Tuple(2, 7), new Tuple(3, 6)})),
			(new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 4), new Tuple(2, 7), new Tuple(3, 6)})),
			(new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 4), new Tuple(2, 7), new Tuple(3, 6)})), 
			null},
				{null, null, null, null, null, null},
				{(new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 7), new Tuple(3, 6), new Tuple(4, 5)})), null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null}};
		Board newBoard = new Board(newLayout);
		
		ArrayList<Tile> newTilePile = new ArrayList<Tile>();
		newTilePile.add(new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 3), new Tuple(2, 6), new Tuple(4, 7)}));
		Position newPlayer1Posn = new Position(4, 0, 2);
		Position newPlayer2Posn = new Position(0, 2, 3); 
		ArrayList<SPlayer> newCurrPlayers = new ArrayList<SPlayer>();
		newCurrPlayers.add(new SPlayer(player2hand, Color.BLACK, newPlayer2Posn)); 
		player1hand.add(new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 6), new Tuple(2, 7), new Tuple(3, 4)})); 
		newCurrPlayers.add(new SPlayer(player1hand, Color.RED, newPlayer1Posn));
		
		BoardState returnedBS = ServerUtils.playATurn(tilePile, currPlayers, new ArrayList<SPlayer>(), testBoard, toPlay); 
		BoardState compareBS = new BoardState(newTilePile, newCurrPlayers, new ArrayList<SPlayer>(), newBoard, new ArrayList<SPlayer>());
		
		assertTrue(returnedBS.equals(compareBS));
	}
	
	// Tests multiple players moving and multiple players being eliminated
	@Test
	public void testPlayATurn2() {
		// Create original board 
		Tile[][] testLayout = {{null, new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 4), new Tuple(2, 7), new Tuple(3, 6)}), null, null, null, null}, // row 1
							{new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 4), new Tuple(2, 7), new Tuple(3, 6)}), null, 
							new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 4), new Tuple(2, 7), new Tuple(3, 6)}), null, null, null}, // row 2
							{null, new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 4), new Tuple(2, 7), new Tuple(3, 6)}), null, null, null, null}, // row 3
							{null, null, null, null, null, null},
							{null, null, null, null, null, null},
							{null, null, null, null, null, null}}; 
		Board testBoard = new Board(testLayout); 
		
		// Tile to be placed on board in position (1, 1) 
		Tile toPlay = new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 5), new Tuple(3, 6), new Tuple(4, 7)}); 
		
		// generate players 
		SPlayer p1 = new SPlayer(new ArrayList<Tile>(), Color.RED, new Position(0, 1, 2)); 
		SPlayer p2 = new SPlayer(new ArrayList<Tile>(), Color.BLUE, new Position(1, 2, 0)); 
		SPlayer p3 = new SPlayer(new ArrayList<Tile>(), Color.YELLOW, new Position(2, 1, 6)); 
		SPlayer p4 = new SPlayer(new ArrayList<Tile>(), Color.ORANGE, new Position(1, 0, 5)); 
		
		ArrayList<SPlayer> currPlayers = new ArrayList<SPlayer>(); 
		currPlayers.add(p1); 
		currPlayers.add(p2); 
		currPlayers.add(p3); 
		currPlayers.add(p4); 
		
		// generate tilePile
		ArrayList<Tile> tilePile = new ArrayList<Tile>(); 
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 6), new Tuple(2, 7), new Tuple(3, 4)}));
		tilePile.add(new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 3), new Tuple(2, 6), new Tuple(4, 7)}));
		
		// Create board layout for after move 
		Tile[][] newLayout = {{null, new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 4), new Tuple(2, 7), new Tuple(3, 6)}), null, null, null, null}, // row 1
							{new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 4), new Tuple(2, 7), new Tuple(3, 6)}), 
							new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 5), new Tuple(3, 6), new Tuple(4, 7)}), 
							new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 4), new Tuple(2, 7), new Tuple(3, 6)}), null, null, null}, // row 2
							{null, new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 4), new Tuple(2, 7), new Tuple(3, 6)}), null, null, null, null}, // row 3
							{null, null, null, null, null, null},
							{null, null, null, null, null, null},
							{null, null, null, null, null, null}}; 
		Board newBoard = new Board(newLayout); 
		
		// Generate other results 
		ArrayList<Tile> newTilePile = new ArrayList<Tile>(); 
		newTilePile.add(new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 3), new Tuple(2, 6), new Tuple(4, 7)}));
		
		// p1 picks up a tile during the turn 
		ArrayList<Tile> p1Hand = new ArrayList<Tile>();
		p1Hand.add(new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 6), new Tuple(2, 7), new Tuple(3, 4)})); 
		
		// Make new players in the proper positions 
		SPlayer newP1 = new SPlayer(p1Hand, Color.RED, new Position(1, 2, 4)); 
		SPlayer newP2 = new SPlayer(new ArrayList<Tile>(), Color.BLUE, new Position(2, 1, 2)); 
		SPlayer newP3 = new SPlayer(new ArrayList<Tile>(), Color.YELLOW, new Position(0, 1, 6)); 
		SPlayer newP4 = new SPlayer(new ArrayList<Tile>(), Color.ORANGE, new Position(1, 0, 1)); 
		
		// Only p2 and p1 survived elimination
		ArrayList<SPlayer> newCurrPlayers = new ArrayList<SPlayer>(); 
		newCurrPlayers.add(newP2);  // because of turn order, p2 should be at the top of the list 
		newCurrPlayers.add(newP1); 
		
		// p3 and p4 have been eliminated 
		ArrayList<SPlayer> newElimPlayers = new ArrayList<SPlayer>(); 
		newElimPlayers.add(newP3); 
		newElimPlayers.add(newP4); 
		
		BoardState returnedBS = ServerUtils.playATurn(tilePile, currPlayers, new ArrayList<SPlayer>(), testBoard, toPlay); 
		BoardState compareBS = new BoardState(newTilePile, newCurrPlayers, newElimPlayers, newBoard, new ArrayList<SPlayer>()); 
		
		assertTrue(returnedBS.equals(compareBS)); 
	}

	@Test
	public void testIsGameOver() {
		
		setupTest(2); 
		assertFalse(ServerUtils.isGameOver(testServer1.getCurrPlayers(), testServer1.getTilePile())); 
		
		testServer1.setTilePile(new ArrayList<Tile>());
		testServer1.getCurrPlayers().get(0).setTiles(new ArrayList<Tile>());
		testServer1.getCurrPlayers().get(1).setTiles(new ArrayList<Tile>());
		
		assertTrue(ServerUtils.isGameOver(testServer1.getCurrPlayers(), testServer1.getTilePile()));  // over because no one has any tiles 
		
		// add a tile to the tile pool 
		ArrayList<Tile> newPile = new ArrayList<Tile>(); 
		newPile.add(new Tile(new Tuple[] {new Tuple(0, 2), new Tuple(1, 3), new Tuple(4, 6), new Tuple(5, 7)})); 
		testServer1.setTilePile(newPile);
		
		// tiles left and multiple players, game is not over 
		assertFalse(ServerUtils.isGameOver(testServer1.getCurrPlayers(), testServer1.getTilePile())); 
		
		ServerUtils.eliminatePlayer(testServer1.getCurrPlayers().get(0), testServer1.getCurrPlayers(), testServer1.getElimPlayers(), testServer1.getTilePile());
		
		assertTrue(ServerUtils.isGameOver(testServer1.getCurrPlayers(), testServer1.getTilePile()));  // over because only one player left 
	}

	@Test
	public void testEliminatePlayer() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAdjacentPlayers() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdvanceTurn() {
		setupTest(3);
		SPlayer first = testServer1.getCurrPlayers().get(0);
		ServerUtils.advanceTurn(testServer1.getCurrPlayers());
		
		assertFalse(first.equals(testServer1.getCurrPlayers().get(0)));
		assertTrue(first.equals(testServer1.getCurrPlayers().get(2)));	
	}

	@Test
	public void testDrawTile() {
		setupTest(1); 
		assertEquals(32, testServer1.getTilePile().size()); 
		
		Tile toBeDrawn = testServer1.getTilePile().get(0);
		List <Tile> firstPlayerTiles = testServer1.getCurrPlayers().get(0).getTiles();
		
		//something is wrong with tile distribution
		assertFalse(firstPlayerTiles.contains(toBeDrawn));
		
		ServerUtils.drawTile(testServer1.getCurrPlayers().get(0),testServer1.getTilePile(), testServer1.getCurrPlayers());
		firstPlayerTiles = testServer1.getCurrPlayers().get(0).getTiles();
		assertTrue(firstPlayerTiles.contains(toBeDrawn));
		
		//check sizes 
		assertEquals(31, testServer1.getTilePile().size()); 
		assertEquals(4, testServer1.getCurrPlayers().get(0).getTiles().size());
	}

	@Test
	public void testAddEliminatedPlayerTiles() {
		setupTest(1); 
		assertEquals(32, testServer1.getTilePile().size());
		
		List <Tile> elimTiles = testServer1.getCurrPlayers().get(0).getTiles();
		ServerUtils.addEliminatedPlayerTiles(testServer1.getCurrPlayers().get(0), testServer1.getTilePile(), testServer1.getCurrPlayers());
		assertEquals(35, testServer1.getTilePile().size());
		
		boolean test = true;
		for (Tile t : elimTiles){
			if (!testServer1.getTilePile().contains(t)){
				test = false;
			}
		}
		assertTrue(test);;
	}

	@Test
	public void testShuffleTiles() {
		fail("Not yet implemented");
	}
	
	public void setupTest(int numPlayers){
		testServer1 = new Server(numPlayers); 
		Tile [][] testlayout1 = {{null, (new Tile(new Tuple[] {new Tuple(0, 7), new Tuple(1, 6), new Tuple(2, 5), new Tuple(3, 4)})), null, null, null, null},
				{(new Tile(new Tuple[] {new Tuple(0, 7), new Tuple(1, 6), new Tuple(2, 5), new Tuple(3, 4)})), null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null}};
		Board board1 = new Board(testlayout1);
	}

	@Test
	public void testGetDragTilePlayerIndex() {
		ArrayList<SPlayer> currPlayers = new ArrayList<SPlayer>();
		SPlayer player1 = new SPlayer(new ArrayList<Tile>(), Color.BLACK, new Position(1, 2, 3));
		currPlayers.add(player1);
		SPlayer player2 = new SPlayer(new ArrayList<Tile>(), Color.GREEN, new Position(2, 4, 6));
		currPlayers.add(player2);
		player2.takeDragonTile();
		SPlayer player3 = new SPlayer(new ArrayList<Tile>(), Color.RED, new Position(5, 4, 3));
		currPlayers.add(player3);
		assertEquals(1, ServerUtils.getDragTilePlayerIndex(currPlayers));
		
		player2.loseDragonTile();
		assertEquals(-1, ServerUtils.getDragTilePlayerIndex(currPlayers));
	}
	
	@Test
	public void testDrawLoop() {
		ArrayList<Tile> tilePile = new ArrayList<Tile>();
		Tile tile1 = new Tile(new Tuple[] {new Tuple(0, 3), new Tuple(1, 2), new Tuple(4, 6), new Tuple(5, 7)});
		Tile tile2 = new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 3), new Tuple(2, 6), new Tuple(4, 7)});
		Tile tile3 = new Tile(new Tuple[] {new Tuple(0, 3), new Tuple(1, 5), new Tuple(2, 6), new Tuple(4, 7)});
		Tile tile4 = new Tile(new Tuple[] {new Tuple(0, 7), new Tuple(1, 6), new Tuple(2, 5), new Tuple(3, 4)});
		Tile tile5 = new Tile(new Tuple[] {new Tuple(0, 2), new Tuple(1, 3), new Tuple(4, 6), new Tuple(5, 7)});
		Tile tile6 = new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 6), new Tuple(2, 7), new Tuple(3, 4)});
		
		tilePile.add(tile1);
		tilePile.add(tile2);
		tilePile.add(tile3);
		tilePile.add(tile4);
		tilePile.add(tile5);
		tilePile.add(tile6);
		
		ArrayList<SPlayer> currPlayers = new ArrayList<SPlayer>();
		SPlayer player1 = new SPlayer(new ArrayList<Tile>(), Color.BLACK, new Position(1, 2, 3));
		currPlayers.add(player1);
		SPlayer player2 = new SPlayer(new ArrayList<Tile>(), Color.GREEN, new Position(2, 4, 6));
		player2.takeDragonTile();
		currPlayers.add(player2);
		SPlayer player3 = new SPlayer(new ArrayList<Tile>(), Color.RED, new Position(5, 4, 3));
		currPlayers.add(player3);
		
		ServerUtils.drawLoop(tilePile, currPlayers);
		
		ArrayList<Tile> player1hand = new ArrayList<Tile>();
		player1hand.add(tile3);
		player1hand.add(tile6);
		
		ArrayList<Tile> player2hand = new ArrayList<Tile>();
		player2hand.add(tile1);
		player2hand.add(tile4);
		
		ArrayList<Tile> player3hand = new ArrayList<Tile>();
		player3hand.add(tile2);
		player3hand.add(tile5);
		
		assertTrue(currPlayers.get(0).getTiles().equals(player1hand));
		assertFalse(currPlayers.get(0).hasDragonTile());
		assertTrue(currPlayers.get(1).getTiles().equals(player2hand));
		assertFalse(currPlayers.get(1).hasDragonTile());
		assertTrue(currPlayers.get(2).getTiles().equals(player3hand));
		assertFalse(currPlayers.get(2).hasDragonTile());
	}
}
