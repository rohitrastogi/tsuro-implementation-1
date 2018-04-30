package assignment3;

import static org.junit.Assert.*;

import org.junit.Test;

public class TileTest {
	
	@Test
	public void testEquals(){
		Tile t1 = new Tile(new Path[] {new Path(0, 1), new Path(2, 3), new Path(4, 5), new Path(6, 7)});
		Tile t2 = new Tile(new Path[] {new Path(0, 1), new Path(2, 3), new Path(4, 5), new Path(6, 7)});
		assertTrue(t1.equals(t2));
		
		Tile t3 = new Tile(new Path[] {new Path(1, 3), new Path(2, 4), new Path(0, 5), new Path(6, 7)});
		assertFalse(t1.equals(t3));
	}

	@Test
	public void testGetOutPath() {
		Tile test1 = new Tile(new Path[] {new Path(0, 1), new Path(2, 3), new Path(4, 5), new Path(6, 7)}, 2);
		assertEquals(2, test1.getOutPath(3));
		
		Tile test2 = new Tile(new Path[] {new Path(0, 5), new Path(1, 3), new Path(2, 6), new Path(4, 7)}, 3);
		assertEquals(7, test2.getOutPath(1));
	}
}
