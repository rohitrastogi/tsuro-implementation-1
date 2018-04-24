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
	//tests edge move, moving several turns, and also playing a rotated tile
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
		Position player1posn = new Position(0, 0, 7);
		Position player2posn = new Position(0, 2, 6);
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
		Position newPlayer1Posn = new Position();
		Position newPlayer2Posn = new Position(); 
		ArrayList<SPlayer> newCurrPlayers = new ArrayList<SPlayer>();
		newCurrPlayers.add(new SPlayer(player2hand, Color.BLACK, newPlayer2Posn)); 
		newCurrPlayers.add(new SPlayer(player1hand, Color.RED, newPlayer1Posn));
		
		BoardState returnedBS = ServerUtils.playATurn(tilePile, currPlayers, new ArrayList<SPlayer>(), testBoard, toPlay); 
		BoardState compareBS = new BoardState(newTilePile, newCurrPlayers, new ArrayList<SPlayer>(), newBoard, new ArrayList<SPlayer>());
		
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
		
		ServerUtils.drawTile(testServer1.getCurrPlayers().get(0),testServer1.getTilePile());
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
		ServerUtils.addEliminatedPlayerTiles(testServer1.getCurrPlayers().get(0), testServer1.getTilePile());
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

}
