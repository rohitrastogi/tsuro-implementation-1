package assignment3;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ServerTest {

	private Server testServer;
	
	@Test
	public void testCreatePlayers() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsLegalPlay() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlayATurn() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsGameOver() {
		setupTest(2); 
		
		assertFalse(testServer.isGameOver()); 
		
		testServer.setTilePile(new ArrayList<Tile>());
		testServer.getCurrPlayers().get(0).setTiles(new ArrayList<Tile>());
		testServer.getCurrPlayers().get(1).setTiles(new ArrayList<Tile>());
		
		assertTrue(testServer.isGameOver());  // over because no one has any tiles 
		
		// add a tile to the tile pool 
		ArrayList<Tile> newPile = new ArrayList<Tile>(); 
		newPile.add(new Tile(new Tuple[] {new Tuple(0, 2), new Tuple(1, 3), new Tuple(4, 6), new Tuple(5, 7)})); 
		testServer.setTilePile(newPile);
		
		testServer.eliminatePlayer(testServer.getCurrPlayers().get(0), testServer.getCurrPlayers(), testServer.getElimPlayers());
		
		assertTrue(testServer.isGameOver());  // over because only one player left 
	}

	@Test
	public void testDrawTile() {
		setupTest(1); 
		
		assertEquals(32, testServer.getTilePile().size()); 
		
		testServer.drawTile(testServer.getCurrPlayers().get(0));
		
		assertEquals(31, testServer.getTilePile().size()); 
		assertEquals(4, testServer.getCurrPlayers().get(0).getTiles().size()); 
	}

	@Test
	public void testAddEliminatedPlayerTiles() {
		setupTest(1); 
		
		assertEquals(32, testServer.getTilePile().size());
		
		testServer.addEliminatedPlayerTiles(testServer.getCurrPlayers().get(0));
		
		assertEquals(35, testServer.getTilePile().size());
	}
	
	public void setupTest(int numPlayers) {
		testServer = new Server(numPlayers); 
	}

}
