import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class CMV2Test {

	@Test
	public void test() {
		//Contract: If There exists at least one set of three consecutive data points that cannot all be contained
		// within or on a circle of radius RADIUS1, then return True. 
		// Only 2 points, or 2 same points give false.
		
		VolatileData d = new VolatileData();
		d.setEpsilon(0.0);
		ArrayList<Point> testpoints = new ArrayList<Point>();
		testpoints.add(new Point(0.0 ,0.0));
		testpoints.add(new Point(0.0 ,0.0));
		d.setPoints(testpoints);
		CMV cmv = new CMV(d);
		assertFalse(cmv.cmv_2(d)); // Only 2 points gives false.
		
		testpoints.add(new Point(1.0 ,0.0));
		d.setPoints(testpoints);
		assertFalse(cmv.cmv_2(d)); // 2 same points give false
		
		testpoints.add(new Point(0.0 ,1.0));
		d.setPoints(testpoints);
		assertTrue(cmv.cmv_2(d)); // angle of 45 degrees is smaller than 180 degrees.
		
		testpoints.add(new Point(0.0 ,1.0));
		testpoints.add(new Point(0.0 ,1.0));
		testpoints.add(new Point(0.0 ,1.0));
		testpoints.add(new Point(0.0 ,1.0));
		d.setPoints(testpoints);
		assertTrue(cmv.cmv_2(d)); // already got true, everything else should give true.
	}
	
	@Test
	public void test2() {
		//Contract: If There exists at least one set of three consecutive data points that cannot all be contained
		// within or on a circle of radius RADIUS1, then return True. 
		
		VolatileData d = new VolatileData();
		d.setEpsilon(0.0);
		ArrayList<Point> testpoints = new ArrayList<Point>();
		testpoints.add(new Point(1.0 ,0.0));
		testpoints.add(new Point(0.0 ,0.0));
		testpoints.add(new Point(-1.0 ,0.0));
		d.setPoints(testpoints);
		CMV cmv = new CMV(d);
		assertFalse(cmv.cmv_2(d)); // angle is PI, and epsilon is 0 so should give false.
		
		d.setEpsilon(Math.PI/2);
		assertFalse(cmv.cmv_2(d)); // Changing the epsilon shouldn't change the previous result.
		
		testpoints.add(new Point(-0.1,0.2));
		d.setPoints(testpoints);
		assertTrue(cmv.cmv_2(d)); // 0.22 is smaller than PI - PI/2
		
		VolatileData d2 = new VolatileData();
		d2.setEpsilon(Math.PI/2);
		ArrayList<Point> testpoints2 = new ArrayList<Point>();
		testpoints2.add(new Point(0.0 ,0.0));
		testpoints2.add(new Point(-1.0 ,0.0));
		testpoints2.add(new Point(-0.1,-0.2));
		d2.setPoints(testpoints2);
		CMV cmv2 = new CMV(d2);
		assertTrue(cmv2.cmv_2(d2)); // Similar to the last test, but now we test that (2*PI)-0.22 is larger than PI + PI/2
	}
	

}
