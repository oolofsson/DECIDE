import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class CMV1Test {

	@Test
	public void test() {
		//Contract: If There exists at least one set of three consecutive data points that cannot all be contained
		// within or on a circle of radius RADIUS1, then return True. 
		// Less than 3 points or all same points give false.
		
		VolatileData d = new VolatileData();
		d.setRadius1(0.0);
		ArrayList<Point> testpoints = new ArrayList<Point>();
		testpoints.add(new Point(0.0 ,0.0));
		testpoints.add(new Point(0.0 ,0.0));
		d.setPoints(testpoints);
		CMV cmv = new CMV(d);
		assertFalse(cmv.cmv_1(d)); // Only 2 points gives false.
		
		testpoints.add(new Point(0.0 ,0.0));
		d.setPoints(testpoints);
		assertFalse(cmv.cmv_1(d)); // All same points give false
		
		VolatileData d2 = new VolatileData();
		d2.setRadius1(4.0);
		ArrayList<Point> testpoints2 = new ArrayList<Point>();
		testpoints2.add(new Point(-2.0 ,0.0));
		testpoints2.add(new Point(2.0 ,0.0));
		testpoints2.add(new Point(0.0 ,-0.5));
		d2.setPoints(testpoints2);
		CMV cmv2 = new CMV(d2);
		assertFalse(cmv2.cmv_1(d2)); // The three points are within distance, so False.
		
		testpoints2.add(new Point(6.0 ,6.0));
		d2.setPoints(testpoints2);
		assertTrue(cmv2.cmv_1(d2)); // But not the last point, so True.
	}
	
	@Test
	public void test2() {
		//Contract: If There exists at least one set of three consecutive data points that cannot all be contained
		// within or on a circle of radius RADIUS1, then return True. 
		// Colinear points give false.
		
		VolatileData d2 = new VolatileData();
		d2.setRadius1(4.0);
		ArrayList<Point> testpoints2 = new ArrayList<Point>();
		testpoints2.add(new Point(-2.0 ,0.0));
		testpoints2.add(new Point(2.0 ,0.0));
		testpoints2.add(new Point(0.0 ,-0.0));
		d2.setPoints(testpoints2);
		CMV cmv2 = new CMV(d2);
		assertFalse(cmv2.cmv_1(d2)); // Colinear points give false.
		
		testpoints2.add(new Point(2.5 ,2.5));
		d2.setPoints(testpoints2);
		assertFalse(cmv2.cmv_1(d2)); // We add one point so that the points become an obstute triangle.
		
		testpoints2.add(new Point(2.5 ,0));
		d2.setPoints(testpoints2);
		assertFalse(cmv2.cmv_1(d2)); // We add one point so that the points become a right triangle. special case of the above.
		
		d2.setRadius1(1.75);
		assertTrue(cmv2.cmv_1(d2)); // Now we decrease the radius just enough to make the points too far away from each other.
	}
	

	@Test
	public void test3() {
		//Contract: If There exists at least one set of three consecutive data points that cannot all be contained
		// within or on a circle of radius RADIUS1, then return True. 
		// We can make the radius bigger to give false when we already have true.
		
		VolatileData d2 = new VolatileData();
		d2.setRadius1(1.15);
		ArrayList<Point> testpoints2 = new ArrayList<Point>();
		testpoints2.add(new Point(2.0 ,0.0));
		testpoints2.add(new Point(0.0 ,-0.0));
		testpoints2.add(new Point(1.5 ,1.5));
		d2.setPoints(testpoints2);
		CMV cmv2 = new CMV(d2);

		d2.setRadius1(1.11);
		assertTrue(cmv2.cmv_1(d2)); // Make the circle just small enough to give true.
		
		d2.setRadius1(1.12);
		assertFalse(cmv2.cmv_1(d2)); // Should give false.
		
		d2.setRadius1(1.05);
		testpoints2.add(new Point(1.5 ,1.5));
		testpoints2.add(new Point(1.5 ,1.5));
		testpoints2.add(new Point(1.5 ,1.5));
		testpoints2.add(new Point(1.5 ,1.5));
		testpoints2.add(new Point(1.5 ,1.5));
		testpoints2.add(new Point(1.5 ,1.5));
		assertTrue(cmv2.cmv_1(d2)); // Since we already got true, this must return true no matter what.
		
	}

}
