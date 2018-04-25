package assignment3;

import static org.junit.Assert.*;

import org.junit.Test;

public class PositionTest {

	@Test
	public void testIsEdgePosition() {
		Position posn1 = new Position(0, 3, 6); 
		Position posn2 = new Position(3, 5, 4); 
		Position posn3 = new Position(3, 3, 2); 
		
		assertTrue(posn1.isEdgePosition());
		assertTrue(posn2.isEdgePosition());
		assertFalse(posn3.isEdgePosition()); 
	}

	@Test
	public void testArePosnsAdjacent() {
		Position posn1 = new Position(3, 3, 4); 
		Position posn2 = new Position(3, 4, 0); 
		Position posn3 = new Position(3, 4, 6); 
		Position posn4 = new Position(1, 1, 0); 
		
		assertTrue(posn1.arePosnsAdjacent(posn2)); 
		assertTrue(posn2.arePosnsAdjacent(posn1)); 
		assertFalse(posn1.arePosnsAdjacent(posn3));
		assertFalse(posn1.arePosnsAdjacent(posn4)); 
		
		Position posn6 = new Position(1, 1, 7); 
		Position posn7 = new Position(1, 0, 5); 
		
		assertTrue(posn6.arePosnsAdjacent(posn7)); 
	}

	@Test
	public void testGetAdjacentPosition() {
		Position posn1 = new Position(3, 3, 4); 
		assertEquals(new Position(3, 4, 1), posn1.getAdjacentPosition());
		
		Position posn2 = new Position(4, 5, 0); 
		assertEquals(new Position(4, 4, 5), posn2.getAdjacentPosition());
		
		Position posn3 = new Position(1, 2, 7); 
		assertEquals(new Position(0, 2, 2), posn3.getAdjacentPosition()); 
	}

	@Test
	public void testEqualsObject() {
		Position posn1 = new Position(1, 2, 3);
		Position posn2 =  new Position(1, 2, 3);
		Position posn3 =  new Position(2, 3, 4);
	
		assertTrue(posn1.equals(posn2));
		assertFalse(posn1.equals(posn3));
	}
}
