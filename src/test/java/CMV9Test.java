import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class CMV9Test {

	@Test
	public void testCaseToFewPoints() {
		//Contract: If there is less than 5 points, it should return false
		VolatileData d = new VolatileData();
		d.setEpsilon(2.0);
		d.setCPTS(1);
		d.setDPTS(1);
		ArrayList<Point> testpoints = new ArrayList<Point>();
		testpoints.add(new Point(0.0, 0.0));
		testpoints.add(new Point(0.1, 0.1));
		testpoints.add(new Point(0.1, 0.1));
		testpoints.add(new Point(0.1, 0.1));
		d.setPoints(testpoints);
		CMV cmv = new CMV();
		assertFalse(cmv.cmv_9(d));
	}

	@Test
	public void testCaseTrue() {
		//Contract: We have 3 points separated by CPTS and
		//DPTS points that forms a triangle such that the angle between the sides
		//connected by the second point and should return true if the angle is smaller
		//than PI - epsilon or bigger than PI + epsilon,
		//in this case epsilon is 2 so 3.14 - 2 > 1.0141 which the angle around point (5, 9), and should return true
		VolatileData d = new VolatileData();
		d.setEpsilon(2.0);
		d.setCPTS(1);
		d.setDPTS(1);
		ArrayList<Point> testpoints = new ArrayList<Point>();
		testpoints.add(new Point(0.0, 0.0));
		testpoints.add(new Point(0.1, 0.1));
		testpoints.add(new Point(5.0, 9.0));
		testpoints.add(new Point(0.1, 0.1));
		testpoints.add(new Point(10.0, 0.0));
		d.setPoints(testpoints);
		CMV cmv = new CMV();
		assertTrue(cmv.cmv_9(d));
	}
	@Test
	public void testCaseFalse() {
		//Contract: We have 3 points separated by CPTS and
		//DPTS points that forms a triangle such that the angle between the sides
		//connected by the second point and should return true if the angle is smaller
		//than PI - epsilon or bigger than PI + epsilon,
		//in this case epsilon is 2 so 3.14 - 2 < 2.06 which is the angle around point (5, 3), and should return false
		VolatileData d = new VolatileData();
		d.setEpsilon(2.0);
		d.setCPTS(1);
		d.setDPTS(1);
		ArrayList<Point> testpoints = new ArrayList<Point>();
		testpoints.add(new Point(0.0, 0.0));
		testpoints.add(new Point(0.1, 0.1));
		testpoints.add(new Point(5.0, 3.0));
		testpoints.add(new Point(0.1, 0.1));
		testpoints.add(new Point(10.0, 0.0));
		d.setPoints(testpoints);
		CMV cmv = new CMV();
		assertFalse(cmv.cmv_9(d));
	}

}
