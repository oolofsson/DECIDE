import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class CMV7Test {

	@Test
	public void testCaseToFewPoints() {
		//Contract: If there is less than 3 points, return false
		VolatileData d = new VolatileData();
		d.setLength1(3.0);
		d.setKPTS(3);
		ArrayList<Point> testpoints = new ArrayList<Point>();
		testpoints.add(new Point(0.0, 0.0));
		testpoints.add(new Point(0.1, 0.1));
		d.setPoints(testpoints);
		CMV cmv = new CMV(d);
		assertFalse(cmv.cmv_7(d));
	}
	@Test
	public void testCaseDistIsShorter() {
		//Contract: If the distance between the two points separated
		//by kpts points is shorter than length1, return false
		VolatileData d = new VolatileData();
		d.setLength1(3.0);
		d.setKPTS(3);
		ArrayList<Point> testpoints = new ArrayList<Point>();
		testpoints.add(new Point(0.0, 0.0));
		testpoints.add(new Point(0.1, 0.1));
		testpoints.add(new Point(0.1, 0.1));
		testpoints.add(new Point(0.1, 0.1));
		testpoints.add(new Point(0.1, 0.1));
		d.setPoints(testpoints);
		CMV cmv = new CMV(d);
		assertFalse(cmv.cmv_7(d));
	}
	@Test
	public void testCaseDistIsLonger() {
		//Contract: If the distance between the two points separated
		//by kpts points is longer than length1, return true
		VolatileData d = new VolatileData();
		d.setLength1(3.0);
		d.setKPTS(3);
		ArrayList<Point> testpoints = new ArrayList<Point>();
		testpoints.add(new Point(0.0, 0.0));
		testpoints.add(new Point(0.1, 0.1));
		testpoints.add(new Point(0.1, 0.1));
		testpoints.add(new Point(0.1, 0.1));
		testpoints.add(new Point(10.1, 10.1));
		d.setPoints(testpoints);
		CMV cmv = new CMV(d);
		assertTrue(cmv.cmv_7(d));
	}
}
