import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;


public class CMV0Test {

	@Test
	public void test() {
		//Contract: If There exists at least one set of two consecutive data points that are a distance greater than
		// the length, LENGTH1, apart then return True. 
		VolatileData d = new VolatileData();
		d.setLength1(1.0);
		ArrayList<Point> testpoints = new ArrayList<Point>();
		testpoints.add(new Point(0.0 ,0.0));
		testpoints.add(new Point(0.1 ,0.1));
		d.setPoints(testpoints);
		CMV cmv = new CMV(d);
		assertFalse(cmv.cmv_0(d));
		
		// Add more points
		testpoints.add(new Point(2.0 ,-2.0));
		testpoints.add(new Point(2.0 ,-2.0));
		testpoints.add(new Point(2.0 ,-2.0));
		testpoints.add(new Point(2.0 ,-2.0));
		d.setPoints(testpoints);
		CMV cmv2 = new CMV(d);
		assertTrue(cmv2.cmv_0(d));
		
		// New data set.
		VolatileData d2 = new VolatileData();
		d.setLength1(0.0);
		ArrayList<Point> testpoints2 = new ArrayList<Point>();
		testpoints2.add(new Point(0.0 ,0.0));
		testpoints2.add(new Point(0.0 ,0.0));
		d2.setPoints(testpoints2);
		CMV cmv3 = new CMV(d2);
		assertTrue(cmv3.cmv_0(d));
		
		// Add more points to new data set
		testpoints2.add(new Point(-4.0 ,-110.0));
		testpoints2.add(new Point(-4.0 ,-110.0));
		testpoints2.add(new Point(-4.0 ,-110.0));
		testpoints2.add(new Point(-4.0 ,-110.0));
		testpoints2.add(new Point(-4.0 ,-110.0));
		d2.setPoints(testpoints2);
		CMV cmv4 = new CMV(d2);
		assertTrue(cmv4.cmv_0(d));
	}

}
