import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by heysunk on 2018-01-21.
 */
public class CMV3Test {
    @Test
    // Contract: Returns the area of the triangle with the three points as corners.
    public void testTriangleArea() throws Exception {
        Point p1 = new Point(0,0);
        Point p2 = new Point( 5, 0);
        Point p3 = new Point(5, 5);
        // Triangle between points (0,0), (0,5) & (5,5)
        // Area is 5 * 5 / 2 = 12.5
        double ORACLE = 12.5;

        VolatileData d = new VolatileData();
        ArrayList<Point> testpoints = new ArrayList<Point>();
        d.setPoints(testpoints);
        CMV cmv = new CMV(d);
        double result = cmv.computerTriangleArea(p1, p2, p3);
        assertEquals(ORACLE, result, 0.000001);
    }

    @Test
    // Contract, returns true if there are 3 consecutive points which form a
    // triangle with area larger than area1
    public void testCMV3Success() throws Exception {
        Point p1 = new Point(0,0);
        Point p2 = new Point( 5, 0);
        Point p3 = new Point(5, 5);
        // the 3 points form a triangle with area 12.5
        double AREA1 = 12.4;
        VolatileData d = new VolatileData();
        ArrayList<Point> testpoints = new ArrayList<Point>();
        testpoints.add(p1);
        testpoints.add(p2);
        testpoints.add(p3);
        d.setPoints(testpoints);
        d.setArea1(AREA1);
        CMV cmv = new CMV(d);
        boolean result = cmv.cmv_3(d);
        assertTrue(result);
    }

    @Test
    // Contract: returns false if there is no triangle formed by three
    // consecutive points with an area larger than area1
    public void testCMV3Failure() throws Exception {
        Point p1 = new Point(0,0);
        Point p2 = new Point( 5, 0);
        Point p3 = new Point(5, 5);
        // the 3 points form a triangle with area 12.5
        double AREA1 = 12.6;
        VolatileData d = new VolatileData();
        ArrayList<Point> testpoints = new ArrayList<Point>();
        testpoints.add(p1);
        testpoints.add(p2);
        testpoints.add(p3);
        d.setPoints(testpoints);
        d.setArea1(AREA1);
        CMV cmv = new CMV(d);
        boolean result = cmv.cmv_3(d);
        assertFalse(result);
    }

    @Test
    // Contract: returns false if there are fewer than 3 points
    public void testCMV3TooFewPoints() throws Exception {
        Point p1 = new Point(0,0);
        Point p2 = new Point( 5, 0);
        // the 3 points form a triangle with area 12.5
        double AREA1 = 12.4;
        VolatileData d = new VolatileData();
        ArrayList<Point> testpoints = new ArrayList<Point>();
        testpoints.add(p1);
        testpoints.add(p2);
        d.setPoints(testpoints);
        d.setArea1(AREA1);
        CMV cmv = new CMV(d);
        boolean result = cmv.cmv_3(d);
        assertFalse(result);
    }

}