package assignment3;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {
	
	private Board testBoard1;
	private Board testBoard2;

	@Test
	public void testPlaceTile() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsValidMove() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsEliminationMove() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFinalPosition() {
		setupTest();
		Tile newTile = new Tile(new Tuple[] {new Tuple(0, 3), new Tuple(1, 4), new Tuple(2, 5), new Tuple(6, 7)});
		Position finalposn = testBoard1.getFinalPosition(newTile, new Position(0, 0, 5));
		assertTrue(testBoard1.getFinalPosition(newTile, new Position(0, 0, 5)).equals(new Position(1, 0, 2)));
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
//		Tile [][] testlayout1 = {{(new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 3), new Tuple(2, 6), new Tuple(4, 7)})), null, null, null, null, null},
//				{(new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 3), new Tuple(2, 6), new Tuple(4, 7)})), null, null, null, null, null},
//				{null, null, null, null, null, null},
//				{null, null, null, null, null, null},
//				{null, null, null, null, null, null},
//				{null, null, null, null, null, null}};
		
		Tile [][] testlayout1 = {{null, (new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 4), new Tuple(2, 7), new Tuple(3, 6)})), null, null, null, null},
				{(new Tile(new Tuple[] {new Tuple(0, 7), new Tuple(1, 6), new Tuple(2, 5), new Tuple(3, 4)})), null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null}};
		
//		testBoard1 = new Board(testlayout1);
		testBoard1 = new Board(testlayout1);
	}
}
