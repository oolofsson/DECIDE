

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class CMV8Test {

	@Test
	public void testCaseToFewPoints() {
		//Contract: If there is less than 5 points, it should return false
		VolatileData d = new VolatileData();
		d.setRadius1(5.0);
		d.setAPTS(3);
		d.setBPTS(2);
		ArrayList<Point> testpoints = new ArrayList<Point>();
		testpoints.add(new Point(0.0, 0.0));
		testpoints.add(new Point(0.1, 0.1));
		testpoints.add(new Point(0.1, 0.1));
		testpoints.add(new Point(0.1, 0.1));
		d.setPoints(testpoints);
		CMV cmv = new CMV(d);
		assertFalse(cmv.cmv_8(d));
	}

	@Test
	public void testCaseNoPointsInside() {
		//Contract: If no case where 3 points separated by APTS and
		//BPTS points fits in a circle with Radius1, it should return false
		VolatileData d = new VolatileData();
		d.setRadius1(5.0);
		d.setAPTS(1);
		d.setBPTS(2);
		ArrayList<Point> testpoints = new ArrayList<Point>();
		testpoints.add(new Point(0.0, 0.0));
		testpoints.add(new Point(0.1, 0.1));
		testpoints.add(new Point(50.1, 35.1));
		testpoints.add(new Point(0.1, 0.1));
		testpoints.add(new Point(0.1, 0.1));
		testpoints.add(new Point(-15.1, -20.3));
		d.setPoints(testpoints);
		CMV cmv = new CMV(d);
		assertFalse(cmv.cmv_8(d));
	}
	@Test
	public void testCasePointsInside() {
		//Contract: If there is any case where 3 points separated by
		//APTS and BPTS points does fit in a circle
		//with Radius1, it should return true
		VolatileData d = new VolatileData();
		d.setRadius1(5.0);
		d.setAPTS(1);
		d.setBPTS(2);
		ArrayList<Point> testpoints = new ArrayList<Point>();
		testpoints.add(new Point(0.0, 0.0));
		testpoints.add(new Point(0.1, 0.1));
		testpoints.add(new Point(3.1, 2.1));
		testpoints.add(new Point(0.1, 0.1));
		testpoints.add(new Point(2.1, 1.1));
		testpoints.add(new Point(0.1, 0.1));
		d.setPoints(testpoints);
		CMV cmv = new CMV(d);
		assertTrue(cmv.cmv_8(d));
	}
}
