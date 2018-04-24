package assignment3;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class BoardTest {
	
	private Board testBoard1;
	private Board testBoard2;
	ArrayList<Tile> pTiles;
	SPlayer testPlayer;

	@Test
	public void testPlaceTile() {
		setupTest();
		Tile toPlace = new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 3), new Tuple(2, 6), new Tuple(4, 7)});
		testBoard1.placeTile(toPlace, 5, 5);
		assertTrue(testBoard1.getTile(5, 5).equals(toPlace));
	}

	@Test
	public void testIsValidMove() {
		setupTest(); 
		assertTrue(testBoard3.isValidMove(testPlayer.getTiles().get(0), testPlayer)); 
		assertTrue(testBoard3.isValidMove(testPlayer.getTiles().get(1), testPlayer)); 
		assertTrue(testBoard3.isValidMove(testPlayer.getTiles().get(2), testPlayer)); 
	}

	@Test
	public void testIsEliminationMove() {
		setupTest();
		Tile newTile = new Tile(new Tuple[] {new Tuple(0, 3), new Tuple(1, 4), new Tuple(2, 5), new Tuple(6, 7)});
		boolean test = testBoard2.isEliminationMove(newTile, testPlayer);
		assertTrue(testBoard2.isEliminationMove(newTile, testPlayer));
	}

	@Test
	public void testGetFinalPosition() {
		setupTest();
		Tile newTile = new Tile(new Tuple[] {new Tuple(0, 3), new Tuple(1, 4), new Tuple(2, 5), new Tuple(6, 7)});
		Position finalposn = testBoard1.getFinalPosition(newTile, new Position(0, 0, 5));
		assertTrue(finalposn.equals(new Position(1, 0, 2)));
		
		finalposn = testBoard2.getFinalPosition(newTile, new Position(0, 0, 5));
		assertTrue(finalposn.equals(new Position(5, 0, 2)));
	}

	@Test
	public void testGetTile() {
		setupTest();
		assertTrue(testBoard1.getTile(1, 0).equals(new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 4), new Tuple(2, 7), new Tuple(3, 6)})));
		assertNull(testBoard1.getTile(3, 3));
		assertNull(testBoard1.getTile(6, 6));
		assertNull(testBoard1.getTile(-1, -1));
	}
	
	
	public void setupTest(){
		Tile [][] testlayout1 = {{null, (new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 4), new Tuple(2, 7), new Tuple(3, 6)})), null, null, null, null},
				{(new Tile(new Tuple[] {new Tuple(0, 7), new Tuple(1, 6), new Tuple(2, 5), new Tuple(3, 4)})), null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null}};
		
		Tile [][] testlayout2 = {{null, (new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 4), new Tuple(2, 7), new Tuple(3, 6)})), 
			(new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 4), new Tuple(2, 7), new Tuple(3, 6)})),
			(new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 4), new Tuple(2, 7), new Tuple(3, 6)})),
			(new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 4), new Tuple(2, 7), new Tuple(3, 6)})), 
			(new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 4), new Tuple(2, 7), new Tuple(3, 6)}))},
				{(new Tile(new Tuple[] {new Tuple(0, 7), new Tuple(1, 6), new Tuple(2, 5), new Tuple(3, 4)})), null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null}};

		Tile [][] testlayout3 = {{null, (new Tile(new Tuple[] {new Tuple(0, 7), new Tuple(1, 6), new Tuple(2, 5), new Tuple(3, 4)})), null, null, null, null},
				{(new Tile(new Tuple[] {new Tuple(0, 7), new Tuple(1, 6), new Tuple(2, 5), new Tuple(3, 4)})), null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null}};
		
		testBoard1 = new Board(testlayout1);
		testBoard2 = new Board(testlayout2);
		testBoard3 = new Board(testlayout3); 
		
		pTiles = new ArrayList<Tile>(); 
		pTiles.add(new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 3), new Tuple(4, 5), new Tuple(6, 7)}));
		pTiles.add(new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 4), new Tuple(3, 6), new Tuple(5, 7)}));
		pTiles.add(new Tile(new Tuple[] {new Tuple(0, 6), new Tuple(1, 5), new Tuple(2, 4), new Tuple(3, 7)}));
		
		testPlayer = new SPlayer(pTiles, Color.GREEN, new Position(0, 0, 5));
	}
}
