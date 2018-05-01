package tsuro;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class BoardTest {
	
	private Board testBoard1;
	private Board testBoard2;
	private Board testBoard3;
	private Board testBoard4;
	ArrayList<Tile> pTiles;
	SPlayer testPlayer;
	Tile toPlay1;
	Tile toPlay2;
	Tile toPlay3;
	Tile toPlay4;

	@Test
	public void testPlaceTile() {
		setupTest();
		Tile toPlace = new Tile(new Path[] {new Path(0, 5), new Path(1, 3), new Path(2, 6), new Path(4, 7)});
		testBoard1.placeTile(toPlace, 5, 5);
		assertTrue(testBoard1.getTile(5, 5).equals(toPlace));
	}

	@Test
	public void testIsValidMove() {
		setupTest(); 
		
		//case 1: toPlay is a valid move (true)
		assertTrue(testBoard1.isValidMove(toPlay2, testPlayer));
		
		//case 2: toPlay in current rotation is invalid, but after rotation is valid move (false)
		assertFalse(testBoard1.isValidMove(toPlay3, testPlayer));
		
		//case 3: toPlay and all hand tiles are invalid moves after all possible rotations (true)
		assertTrue(testBoard3.isValidMove(toPlay1, testPlayer));
	
		//case 4: toPlay in all rotations is invalid, but there is one rotation of a hand tile that is valid (false)
		assertFalse(testBoard1.isValidMove(toPlay4, testPlayer));
	}

	@Test
	public void testIsEliminationMove() {
		setupTest();
		assertTrue(testBoard2.isEliminationMove(toPlay2, testPlayer));
		assertFalse(testBoard1.isEliminationMove(toPlay2, testPlayer));
	}

	@Test
	public void testGetFinalPosition() {
		setupTest();
		Tile newTile = new Tile(new Path[] {new Path(0, 3), new Path(1, 4), new Path(2, 5), new Path(6, 7)});
		Position finalposn = testBoard1.getFinalPosition(newTile, new Position(0, 0, 5));
		assertTrue(finalposn.equals(new Position(1, 0, 2)));
		
		finalposn = testBoard2.getFinalPosition(newTile, new Position(0, 0, 5));
		assertTrue(finalposn.equals(new Position(5, 0, 2)));
	}

	@Test
	public void testGetTile() {
		setupTest();
		assertTrue(testBoard1.getTile(1, 0).equals(new Tile(new Path[] {new Path(0, 5), new Path(1, 4), new Path(2, 7), new Path(3, 6)})));
		assertNull(testBoard1.getTile(3, 3));
		assertNull(testBoard1.getTile(6, 6));
		assertNull(testBoard1.getTile(-1, -1));
	}
	
	@Test
	public void testEquals(){
		setupTest();
		assertTrue(testBoard1.equals(testBoard4));
		assertFalse(testBoard1.equals(testBoard2));
		assertFalse(testBoard2.equals(testBoard3));
	}
	
	
	public void setupTest(){
		//used to test if player will not be eliminated 
		Tile [][] testlayout1 = {{null, (new Tile(new Path[] {new Path(0, 5), new Path(1, 4), new Path(2, 7), new Path(3, 6)})), null, null, null, null},
				{(new Tile(new Path[] {new Path(0, 7), new Path(1, 6), new Path(2, 5), new Path(3, 4)})), null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null}};
		
		//used to test if player will be eliminated
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

		Tile [][] testlayout3 = {{null, (new Tile(new Path[] {new Path(0, 7), new Path(1, 6), new Path(2, 5), new Path(3, 4)})), null, null, null, null},
				{(new Tile(new Path[] {new Path(0, 7), new Path(1, 6), new Path(2, 5), new Path(3, 4)})), null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null}};
		
		Tile [][] testlayout4 = {{null, (new Tile(new Path[] {new Path(0, 5), new Path(1, 4), new Path(2, 7), new Path(3, 6)})), null, null, null, null},
				{(new Tile(new Path[] {new Path(0, 7), new Path(1, 6), new Path(2, 5), new Path(3, 4)})), null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null}};
		
		testBoard1 = new Board(testlayout1);
		testBoard2 = new Board(testlayout2);
		testBoard3 = new Board(testlayout3); 
		testBoard4 = new Board(testlayout4);
		toPlay1 = new Tile(new Path[] {new Path(0, 1), new Path(2, 4), new Path(3, 6), new Path(5, 7)});
		toPlay2 = new Tile(new Path[] {new Path(0, 3), new Path(1, 4), new Path(2, 5), new Path(6, 7)});
		toPlay3 = new Tile(new Path[] {new Path(0, 3), new Path(1, 2), new Path(4, 5), new Path(6, 7)});
		toPlay4 = new Tile(new Path[] {new Path(0, 1), new Path(2, 3), new Path(4, 5), new Path(6, 7)});
		
		pTiles = new ArrayList<Tile>(); 
		pTiles.add(new Tile(new Path[] {new Path(0, 7), new Path(1, 6), new Path(2, 5), new Path(3, 4)}, 1));
		pTiles.add(new Tile(new Path[] {new Path(0, 6), new Path(1, 5), new Path(2, 4), new Path(3, 7)})); 
		
		testPlayer = new SPlayer(pTiles, new Position(0, 0, 5), "rohit", Color.BLUE);
	}
}
