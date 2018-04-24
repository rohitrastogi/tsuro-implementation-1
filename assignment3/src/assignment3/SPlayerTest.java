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
	
	@Test
	public void testEquals(){
		ArrayList<Tile> pTiles1 = new ArrayList<Tile>(); 
		pTiles1.add(new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 3), new Tuple(4, 5), new Tuple(6, 7)}));
		pTiles1.add(new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 4), new Tuple(3, 6), new Tuple(5, 7)}));
		pTiles1.add(new Tile(new Tuple[] {new Tuple(0, 6), new Tuple(1, 5), new Tuple(2, 4), new Tuple(3, 7)}));
		
		SPlayer test1 = new SPlayer(pTiles1, Color.GREEN, new Position(0, 0, 0));
		
		ArrayList<Tile> pTiles2 = new ArrayList<Tile>(); 
		pTiles2.add(new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 3), new Tuple(4, 5), new Tuple(6, 7)}));
		pTiles2.add(new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 4), new Tuple(3, 6), new Tuple(5, 7)}));
		pTiles2.add(new Tile(new Tuple[] {new Tuple(0, 6), new Tuple(1, 5), new Tuple(2, 4), new Tuple(3, 7)}));
		
		SPlayer test2 = new SPlayer(pTiles2, Color.GREEN, new Position(0, 0, 0));
		assertTrue(test1.equals(test2));
		
		ArrayList<Tile> pTiles3 = new ArrayList<Tile>(); 
		pTiles3.add(new Tile(new Tuple[] {new Tuple(0, 3), new Tuple(1, 7), new Tuple(2, 6), new Tuple(4, 5)}));
		pTiles3.add(new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 4), new Tuple(3, 6), new Tuple(5, 7)}));
		pTiles3.add(new Tile(new Tuple[] {new Tuple(0, 6), new Tuple(1, 5), new Tuple(2, 4), new Tuple(3, 7)}));
		
		SPlayer test3 = new SPlayer(pTiles3, Color.GREEN, new Position(0, 0, 0));
		assertFalse(test3.equals(test1));
		
		SPlayer test4 = new SPlayer(pTiles1, Color.BLACK, new Position(0, 0, 0));
		assertFalse(test4.equals(test1));
		
		SPlayer test5 = new SPlayer(pTiles1, Color.GREEN, new Position(1, 1, 1));
		assertFalse(test5.equals(test1));
		
		// Dragon tile
		test1.takeDragonTile();
		assertFalse(test1.equals(test2)); 
	}

}
