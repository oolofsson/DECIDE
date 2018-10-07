import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CMV14Test {
    @Test
    public void numberOfPointsTest() {
        //Contract: The number of points must be five or greater.
        ArrayList<Point> testpoints = new ArrayList<>();
        testpoints.add(new Point(10, 10));
        testpoints.add(new Point(10, 30));

        VolatileData d = new VolatileData();
        d.setArea1(1);
        d.setArea2(1);
        d.setEPTS(1);
        d.setFPTS(1);
        d.setPoints(testpoints);
        CMV cmv = new CMV(d);

        //less than five points
        assertFalse(cmv.cmv_14(d));
    }


    @Test
    public void numberOfPointsTest2() {
        //Contract: The number of points must be greater than the consecutive intervening points combined.
        ArrayList<Point> testpoints = new ArrayList<>();
        testpoints.add(new Point(1, 2));
        testpoints.add(new Point(2, 3));
        testpoints.add(new Point(4, 5));
        testpoints.add(new Point(6, 7));
        testpoints.add(new Point(8, 9));

        VolatileData d = new VolatileData();
        d.setArea2(1);
        d.setArea1(1);
        d.setEPTS(10);
        d.setFPTS(10);
        d.setPoints(testpoints);
        CMV cmv = new CMV(d);

        assertFalse(cmv.cmv_14(d));
    }


    @Test
    public void area2Test() {
        //Contract: Area 2 must be zero or greater.
        ArrayList<Point> testpoints = new ArrayList<>();
        testpoints.add(new Point(1, 2));
        testpoints.add(new Point(2, 3));
        testpoints.add(new Point(4, 5));
        testpoints.add(new Point(6, 7));
        testpoints.add(new Point(8, 9));


        VolatileData d = new VolatileData();
        d.setArea1(10.3);
        d.setArea2(-3);
        d.setEPTS(1);
        d.setFPTS(1);
        d.setPoints(testpoints);
        CMV cmv = new CMV(d);
        assertFalse(cmv.cmv_14(d));
    }


    @Test
    public void requirementTest() {
        //Contract: The same set of three points with E_PTS and F_PTS consecutive intervening points, respectively.
        // Which are vertices of a triangle with area greater than AREA1 and AREA2 results in true.
        ArrayList<Point> testpoints = new ArrayList<>();
        testpoints.add(new Point(1, 2));
        testpoints.add(new Point(5, 4));
        testpoints.add(new Point(10, 8));
        testpoints.add(new Point(15, 10));
        testpoints.add(new Point(20, 12));


        VolatileData d = new VolatileData();
        d.setArea1(5);
        d.setArea2(10);
        d.setEPTS(1);
        d.setFPTS(1);
        d.setPoints(testpoints);
        CMV cmv = new CMV(d);
        assertTrue(cmv.computerTriangleArea(testpoints.get(0), testpoints.get(2), testpoints.get(4)) > 5);
        assertTrue(cmv.computerTriangleArea(testpoints.get(0), testpoints.get(2), testpoints.get(4)) > 10);
        assertTrue(cmv.cmv_14(d));
    }


    @Test
    public void requirementTest2() {
        //Contract: If there only exists one set of three points with E_PTS and F_PTS consecutive intervening points, respectively.
        // Which are vertices of a triangle with area less than AREA1 or AREA2 will result in false

        ArrayList<Point> testpoints = new ArrayList<>();
        testpoints.add(new Point(1, 2));
        testpoints.add(new Point(5, 4));
        testpoints.add(new Point(10, 8));
        testpoints.add(new Point(15, 10));
        testpoints.add(new Point(20, 12));


        VolatileData d = new VolatileData();
        d.setArea1(100);
        d.setArea2(5);
        d.setEPTS(1);
        d.setFPTS(1);
        d.setPoints(testpoints);
        CMV cmv = new CMV(d);


        //Area is less than AREA1
        assertFalse(cmv.computerTriangleArea(testpoints.get(0), testpoints.get(2), testpoints.get(4)) > 100);
        assertTrue(cmv.computerTriangleArea(testpoints.get(0), testpoints.get(2), testpoints.get(4)) > 5);
        assertFalse(cmv.cmv_14(d));

        //Area of triangle is less than AREA2
        d.setArea1(100);
        d.setArea2(5);
        assertFalse(cmv.computerTriangleArea(testpoints.get(0), testpoints.get(2), testpoints.get(4)) > 100);
        assertTrue(cmv.computerTriangleArea(testpoints.get(0), testpoints.get(2), testpoints.get(4)) > 5);
        assertFalse(cmv.cmv_14(d));


        //Is less than both
        d.setRadius1(100);
        d.setRadius2(100);
        assertFalse(cmv.computerTriangleArea(testpoints.get(0), testpoints.get(2), testpoints.get(4)) > 100);
        assertFalse(cmv.computerTriangleArea(testpoints.get(0), testpoints.get(2), testpoints.get(4)) > 100);
        assertFalse(cmv.cmv_14(d));
    }

    @Test
    public void requirementTest3() {
        //Contract: The same set of three points with E_PTS and F_PTS consecutive intervening points, respectively.
        // Isn't required to be have area greater than AREA1 and AREA2, as long as there exists other sets of three points
        // of which vertices forms a triangle with area greater than AREA1 and AREA2.

        ArrayList<Point> testpoints = new ArrayList<>();
        testpoints.add(new Point(1, 2));
        testpoints.add(new Point(5, 4));
        testpoints.add(new Point(10, 8));
        testpoints.add(new Point(15, 10));
        testpoints.add(new Point(20, 12));
        testpoints.add(new Point(100, 50));


        VolatileData d = new VolatileData();
        d.setArea1(10);
        d.setArea2(30);
        d.setEPTS(1);
        d.setFPTS(1);
        d.setPoints(testpoints);
        CMV cmv = new CMV(d);

        // First set of points form triangle with area greater than AREA1 only
        assertTrue(cmv.computerTriangleArea(testpoints.get(0), testpoints.get(2), testpoints.get(4)) > 10);
        assertFalse(cmv.computerTriangleArea(testpoints.get(0), testpoints.get(2), testpoints.get(4)) > 30);

        //Second set of points form triangle with area greater than AREA1 and AREA2
        assertTrue(cmv.computerTriangleArea(testpoints.get(1), testpoints.get(3), testpoints.get(5)) > 10);
        assertTrue(cmv.computerTriangleArea(testpoints.get(1), testpoints.get(3), testpoints.get(5)) > 30);

        //Requirements should be met
        assertTrue(cmv.cmv_14(d));
    }
}
