package tsuro;

import static org.junit.Assert.*;

import org.junit.Test;

public class PathTest {

	@Test
	public void testContains() {
		Path test = new Path(0, 1);
		assertFalse(test.contains(2));
		assertTrue(test.contains(0));
		assertTrue(test.contains(1));
	}

	@Test
	public void testGetEndpoint() {
		Path test1 = new Path(0, 1);
		assertEquals(0, test1.getEndpoint(1));
		assertEquals(1, test1.getEndpoint(0));
		assertEquals(-1, test1.getEndpoint(2));
	}

	@Test
	public void testEqualsObject() {
		Path test1 = new Path(0,1);
		Path test2 = new Path(0, 1);
		Path test3 = new Path(1, 2);
		assertTrue(test1.equals(test2));
		assertFalse(test1.equals(test3));
	}
}
