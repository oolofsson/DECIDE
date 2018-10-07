import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CMV10Test {

	@Test
	public void test1() {
		// Contract: with NUMPOINTS < 5, the function should return false

		VolatileData d = new VolatileData();
		d.setPoints(new ArrayList<Point>());

		CMV cmv = new CMV();
		assertFalse(cmv.cmv_10(d));
	}

	@Test
	public void test2() {
		// Contract: form a triangle with an area larger than AREA1, should return TRUE

		VolatileData d = new VolatileData();

		List<Point> points = new ArrayList<>();

		points.add(new Point(0, 0));
		points.add(new Point(-1, 1));
		points.add(new Point(4, 5));
		points.add(new Point(10, 0));
		points.add(new Point(7, 4));
		points.add(new Point(0, 10));

		d.setPoints(points);

		d.setArea1(49); // the area of the triangle is 10 * 10 / 2 = 50
		d.setEPTS(2);
		d.setFPTS(1);

		CMV cmv = new CMV();
		assertTrue(cmv.cmv_10(d));
	}
	@Test
	public void test3() {
		// Contract: form a triangle with an area smaller than AREA1, should return FALSE

		VolatileData d = new VolatileData();

		List<Point> points = new ArrayList<>();

		points.add(new Point(0, 0));
		points.add(new Point(-1, 1));
		points.add(new Point(4, 5));
		points.add(new Point(10, 0));
		points.add(new Point(7, 4));
		points.add(new Point(0, 10));

		d.setPoints(points);

		d.setArea1(51); // the area of the triangle is 10 * 10 / 2 = 50
		d.setEPTS(2);
		d.setFPTS(1);

		CMV cmv = new CMV();
		assertFalse(cmv.cmv_10(d));
	}

}
