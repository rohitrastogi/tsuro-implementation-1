package assignment3;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class SPlayerTest {

	@Test
	public void testHasTile() {
		ArrayList<Tile> pTiles = new ArrayList<Tile>(); 
		pTiles.add(new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 3), new Tuple(4, 5), new Tuple(6, 7)}));
		pTiles.add(new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 4), new Tuple(3, 6), new Tuple(5, 7)}));
		pTiles.add(new Tile(new Tuple[] {new Tuple(0, 6), new Tuple(1, 5), new Tuple(2, 4), new Tuple(3, 7)}));
		
		SPlayer test1 = new SPlayer(pTiles, Color.GREEN, new Position(0, 0, 0)); 
		
		Tile testTile = pTiles.get(1); 
		Tile testTile2 = new Tile(new Tuple[] {new Tuple(0, 7), new Tuple(1, 2), new Tuple(3, 4), new Tuple(5, 6)});
		
		assertTrue(test1.hasTile(testTile));
		assertFalse(test1.hasTile(testTile2)); 
	}

}
