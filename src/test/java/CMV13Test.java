import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class CMV13Test {
    @Test
    public void numberOfPointsTest() {
        //Contract: The number of points must be five or greater.
        ArrayList<Point> testpoints = new ArrayList<>();
        testpoints.add(new Point(1, 2));
        testpoints.add(new Point(2, 3));

        VolatileData d = new VolatileData();
        d.setRadius1(10.3);
        d.setRadius2(3.5);
        d.setAPTS(1);
        d.setBPTS(1);
        d.setPoints(testpoints);
        CMV cmv = new CMV(d);

        //less than five points
        assertFalse(cmv.cmv_13(d));
    }


    @Test
    public void numberOfPointsTest2() {
        //Contract: The number of points must be larger than the consecutive intervening points combined.
        ArrayList<Point> testpoints = new ArrayList<>();
        testpoints.add(new Point(1, 2));
        testpoints.add(new Point(2, 3));
        testpoints.add(new Point(4, 5));
        testpoints.add(new Point(6, 7));
        testpoints.add(new Point(8, 9));

        VolatileData d = new VolatileData();
        d.setRadius1(10.3);
        d.setRadius2(3.5);
        d.setAPTS(10);
        d.setBPTS(10);
        d.setPoints(testpoints);
        CMV cmv = new CMV(d);

        //less than five points
        assertFalse(cmv.cmv_13(d));
    }


    @Test
    public void radius2Test() {
        //Contract: Radius 2 must be zero or greater.
        ArrayList<Point> testpoints = new ArrayList<>();
        testpoints.add(new Point(1, 2));
        testpoints.add(new Point(2, 3));
        testpoints.add(new Point(4, 5));
        testpoints.add(new Point(6, 7));
        testpoints.add(new Point(8, 9));


        VolatileData d = new VolatileData();
        d.setRadius1(10.3);
        d.setRadius2(-3);
        d.setAPTS(1);
        d.setBPTS(1);
        d.setPoints(testpoints);
        CMV cmv = new CMV(d);
        assertFalse(cmv.cmv_13(d));
    }


    @Test
    public void requirementTest() {
        //Contract: The same set of three points with A_PTS and B_PTS consecutive intervening points, respectively.
        // Which also isn't contained within or on a circle of radius1 and radius2 will result in true.
        ArrayList<Point> testpoints = new ArrayList<>();
        testpoints.add(new Point(1, 2));
        testpoints.add(new Point(5, 4));
        testpoints.add(new Point(10, 8));
        testpoints.add(new Point(15, 10));
        testpoints.add(new Point(20, 12));


        VolatileData d = new VolatileData();
        d.setRadius1(0.1);
        d.setRadius2(0.5);
        d.setAPTS(1);
        d.setBPTS(1);
        d.setPoints(testpoints);
        CMV cmv = new CMV(d);
        assertTrue(cmv.not_contained(testpoints.get(0), testpoints.get(2), testpoints.get(4), 0.1));
        assertTrue(cmv.not_contained(testpoints.get(0), testpoints.get(2), testpoints.get(4), 0.5));
        assertTrue(cmv.cmv_13(d));
    }


    @Test
    public void requirementTest2() {
        //Contract: If there only exists one set of three points with A_PTS and B_PTS consecutive intervening points, respectively.
        // Which is contained within or on a circle of radius1 or/both radius2 will result in false

        ArrayList<Point> testpoints = new ArrayList<>();
        testpoints.add(new Point(1, 2));
        testpoints.add(new Point(5, 4));
        testpoints.add(new Point(10, 8));
        testpoints.add(new Point(15, 10));
        testpoints.add(new Point(20, 12));


        VolatileData d = new VolatileData();
        d.setRadius1(100);
        d.setRadius2(0.1);
        d.setAPTS(1);
        d.setBPTS(1);
        d.setPoints(testpoints);
        CMV cmv = new CMV(d);


        //Is contained in circle with radius1
        assertFalse(cmv.not_contained(testpoints.get(0), testpoints.get(2), testpoints.get(4), 100));
        assertTrue(cmv.not_contained(testpoints.get(0), testpoints.get(2), testpoints.get(4), 0.1));
        assertFalse(cmv.cmv_13(d));

        //Is contained in circle with radius2
        d.setRadius1(0.1);
        d.setRadius2(100);
        assertTrue(cmv.not_contained(testpoints.get(0), testpoints.get(2), testpoints.get(4), 0.1));
        assertFalse(cmv.not_contained(testpoints.get(0), testpoints.get(2), testpoints.get(4), 100));
        assertFalse(cmv.cmv_13(d));


        //Is contained in both
        d.setRadius1(100);
        d.setRadius2(100);
        assertFalse(cmv.not_contained(testpoints.get(0), testpoints.get(2), testpoints.get(4), 100));
        assertFalse(cmv.not_contained(testpoints.get(0), testpoints.get(2), testpoints.get(4), 100));
        assertFalse(cmv.cmv_13(d));
    }

    @Test
    public void requirementTest3() {
        //Contract: The same set of three points with A_PTS and B_PTS consecutive intervening points, respectively.
        // Isn't required to be outside of both circles with radius1 and radius2, as long as there exists other sets of three points
        // which isn't within or on a circle of radius1 or radius2 the requirements are met.

        ArrayList<Point> testpoints = new ArrayList<>();
        testpoints.add(new Point(1, 2));
        testpoints.add(new Point(5, 4));
        testpoints.add(new Point(10, 8));
        testpoints.add(new Point(15, 10));
        testpoints.add(new Point(20, 12));
        testpoints.add(new Point(100, 50));


        VolatileData d = new VolatileData();
        d.setRadius1(0.1);
        d.setRadius2(11);
        d.setAPTS(1);
        d.setBPTS(1);
        d.setPoints(testpoints);
        CMV cmv = new CMV(d);

        // First set of points is contained in circle of radius2 only.
        assertTrue(cmv.not_contained(testpoints.get(0), testpoints.get(2), testpoints.get(4), 0.1));
        assertFalse(cmv.not_contained(testpoints.get(0), testpoints.get(2), testpoints.get(4), 11));

        //Second set of points is contained in both.
        assertTrue(cmv.not_contained(testpoints.get(1), testpoints.get(3), testpoints.get(5), 0.1));
        assertTrue(cmv.not_contained(testpoints.get(1), testpoints.get(3), testpoints.get(5), 11));

        //Requirements should be met
        assertTrue(cmv.cmv_13(d));
    }

}
