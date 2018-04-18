package assignment3;

import static org.junit.Assert.*;

import org.junit.Test;

public class TileTest {
	
	@Test
	public void testEquals(){
		Tile t1 = new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 3), new Tuple(4, 5), new Tuple(6, 7)});
		Tile t2 = new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 3), new Tuple(4, 5), new Tuple(6, 7)});
		assertTrue(t1.equals(t2));
	}

	@Test
	public void testGetOutPath() {
		Tile test1 = new Tile(new Tuple[] {new Tuple(0, 1), new Tuple(2, 3), new Tuple(4, 5), new Tuple(6, 7)}, 2);
		assertEquals(2, test1.getOutPath(3));
		
		Tile test2 = new Tile(new Tuple[] {new Tuple(0, 5), new Tuple(1, 3), new Tuple(2, 6), new Tuple(4, 7)}, 3);
		assertEquals(7, test2.getOutPath(1));
	}
}
