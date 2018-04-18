package assignment3;

import static org.junit.Assert.*;

import org.junit.Test;

public class TupleTest {

	@Test
	public void testContains() {
		Tuple test = new Tuple(0, 1);
		assertFalse(test.contains(2));
		assertTrue(test.contains(0));
		assertTrue(test.contains(1));
	}

	@Test
	public void testGetOther() {
		Tuple test1 = new Tuple(0, 1);
		assertEquals(0, test1.getOther(1));
		assertEquals(1, test1.getOther(0));
		assertEquals(-1, test1.getOther(2));
	}

	@Test
	public void testEqualsObject() {
		Tuple test1 = new Tuple(0,1);
		Tuple test2 = new Tuple(0, 1);
		Tuple test3 = new Tuple(1, 2);
		assertTrue(test1.equals(test2));
		assertFalse(test1.equals(test3));
	}
}
