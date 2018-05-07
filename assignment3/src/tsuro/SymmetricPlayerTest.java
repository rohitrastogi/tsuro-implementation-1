package tsuro;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Comparator;

import org.junit.Test;

public class SymmetricPlayerTest {
	Tile noSymmetry1, noSymmetry2, oneAxis1, oneAxis2, twoAxes1, twoAxes2;	
	Comparator<Tile> ls, ms;

	@Test
	public void testLSLessThan() {
		setupTest();
		assertEquals(-1, ls.compare(noSymmetry1, oneAxis1));
		assertEquals(-1, ls.compare(oneAxis1, twoAxes1));
		assertEquals(-1, ls.compare(noSymmetry1, twoAxes1));
	}
	
	@Test
	public void testMSGreaterThan(){
		setupTest();
		assertEquals(1, ms.compare(noSymmetry1, oneAxis1));
		assertEquals(1, ms.compare(oneAxis1, twoAxes1));
		assertEquals(1, ms.compare(noSymmetry1, twoAxes1));
	}
	
	@Test
	public void testMSLessThan() {
		setupTest();
		assertEquals(-1, ms.compare(oneAxis1, noSymmetry1));
		assertEquals(-1, ms.compare(twoAxes1, oneAxis1));
		assertEquals(-1, ms.compare(twoAxes1, noSymmetry1));
	}
	
	@Test
	public void testLSGreaterThan(){
		setupTest();
		assertEquals(1, ls.compare(oneAxis1, noSymmetry1));
		assertEquals(1, ls.compare(twoAxes1, oneAxis1));
		assertEquals(1, ls.compare(twoAxes1, noSymmetry1));
	}
	
	@Test 
	public void testLSEquals(){
		setupTest();
		assertEquals(0, ls.compare(noSymmetry1, noSymmetry2));
		assertEquals(0, ls.compare(oneAxis1, oneAxis2));
		assertEquals(0, ls.compare(twoAxes1, twoAxes2));
	}
	
	@Test
	public void testMSEquals(){
		setupTest();
		assertEquals(0, ms.compare(noSymmetry1, noSymmetry2));
		assertEquals(0, ms.compare(oneAxis1, oneAxis2));
		assertEquals(0, ms.compare(twoAxes1, twoAxes2));
	}
	
	public void setupTest(){
		ls = new LeastSymmetricalComparison();
		ms = Collections.reverseOrder(new LeastSymmetricalComparison());
		noSymmetry1 = new Tile(new Path[] {new Path(0, 5), new Path(1, 2), new Path(3, 6), new Path(4, 7)});
		noSymmetry2 = new Tile(new Path[] {new Path(0, 5), new Path(1, 3), new Path(2, 6), new Path(4, 7)});
		oneAxis1 = new Tile(new Path[] {new Path(0, 1), new Path(2, 7), new Path(3, 6), new Path(4, 5)});
		oneAxis2 = new Tile(new Path[] {new Path(0, 5), new Path(1, 4), new Path(2, 6), new Path(3, 7)});
		twoAxes1 = new Tile(new Path[] {new Path(0, 1), new Path(2, 3), new Path(4, 5), new Path(6, 7)});
		twoAxes2 = new Tile(new Path[] {new Path(0, 5), new Path(1, 4), new Path(2, 7), new Path(3, 6)});
	}

}
