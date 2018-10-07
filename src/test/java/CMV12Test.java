import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;



public class CMV12Test {

    @Test
    public void numberOfPointsTest() {
        //Contract: The number of points must not be less than three.

        ArrayList<Point> testpoints = new ArrayList<>();
        testpoints.add(new Point(1, 2));
        testpoints.add(new Point(2, 3)); //Distance 1.4

        VolatileData d = new VolatileData();
        d.setLength1(1);
        d.setLength2(1.2);
        d.setKPTS(1);
        d.setPoints(testpoints);
        CMV cmv = new CMV(d);

        //less than three
        assertFalse(cmv.cmv_12(d));

        //equal to three
        testpoints.add(new Point(3, 4));
        d.setPoints(testpoints);
        assertTrue(cmv.cmv_12(d));
    }

    @Test
    public void length2Test(){
        //Contract: Length2 must be greater or equal to zero

        ArrayList<Point> testpoints = new ArrayList<>();
        testpoints.add(new Point(1, 2));
        testpoints.add(new Point(2, 3));
        testpoints.add(new Point(4, 5));

        VolatileData d = new VolatileData();
        d.setLength1(1);
        d.setLength2(-1);
        d.setKPTS(1);
        d.setPoints(testpoints);
        CMV cmv = new CMV(d);

        //Length < 0
        assertFalse(cmv.cmv_12(d));

        // Length == 0
        d.setLength2(0);
        assertTrue(cmv.cmv_12(d));

        // Length > 0
        d.setLength2(1);
        assertTrue(cmv.cmv_12(d));


    }

    @Test
    public void requriementsTest() {
        //Contract: If there exists at least one set of 2 points with exactly
        // K_PTS consecutive intervening points and distance greater than both LENGTH1 and LENGTH2
        // the result is true.


        //Same set of points pass both requirements
        ArrayList<Point> testpoints = new ArrayList<>();
        testpoints.add(new Point(1, 2));
        testpoints.add(new Point(2, 3));
        testpoints.add(new Point(3, 4));

        VolatileData d = new VolatileData();
        d.setLength1(1);
        d.setLength2(1.2);
        d.setKPTS(1);
        d.setPoints(testpoints);
        CMV cmv = new CMV(d);
        double correctPointsDistance = cmv.distance(testpoints.get(0), testpoints.get(2));

        assertTrue((correctPointsDistance > 1) && (correctPointsDistance > 1.2));
        assertTrue(cmv.cmv_12(d));


    }

    @Test
    public void requriementsTest2() {
        //Contract: Returns false if there are not K_PTS consecutive intervening points.


        //Not enough consecutive points
        ArrayList<Point> testpoints = new ArrayList<>();
        testpoints.add(new Point(1, 2));
        testpoints.add(new Point(2, 3));
        testpoints.add(new Point(3, 4));

        VolatileData d = new VolatileData();
        d.setLength1(1);
        d.setLength2(1.2);
        d.setKPTS(2);
        d.setPoints(testpoints);
        CMV cmv = new CMV(d);

        assertFalse(cmv.cmv_12(d));


    }

    @Test
    public void requriementsTest3() {
        //Contract: If there's only one set of 2 points with exactly K_PTS consecutive intervening points and one of LENGTH1 or LENGTH2 is
        //less than distance between the set of points, it should return false


        ArrayList<Point> testpoints = new ArrayList<>();
        testpoints.add(new Point(1, 2));
        testpoints.add(new Point(2, 3));
        testpoints.add(new Point(3, 4));
        testpoints.add(new Point(4, 5));

        VolatileData d = new VolatileData();
        d.setLength1(1);
        d.setLength2(100);
        d.setKPTS(2);
        d.setPoints(testpoints);
        CMV cmv = new CMV(d);
        double distanceBetweenSetOfPoints = cmv.distance(testpoints.get(0), testpoints.get(3)); // ≈ 4.2

        assertTrue(distanceBetweenSetOfPoints > 1);
        assertFalse(distanceBetweenSetOfPoints > 100);

        assertFalse(cmv.cmv_12(d));
    }


    @Test
    public void requriementsTest4() {
        //Contract: There must exist at least one (same or different) set of 2 points with distance greater than LENGTH1 or LENGTH2
        // In addition all above mentioned points must have K_PTS consecutive intervening points.


        ArrayList<Point> testpoints = new ArrayList<Point>();
        testpoints.add(new Point(1, 2));
        testpoints.add(new Point(2, 3));
        testpoints.add(new Point(3, 4));
        testpoints.add(new Point(4, 5));
        testpoints.add(new Point(6, 7));
        testpoints.add(new Point(8, 9));
        testpoints.add(new Point(13, 14));

        VolatileData d = new VolatileData();
        d.setLength1(1);
        d.setLength2(10);
        d.setKPTS(3);
        d.setPoints(testpoints);
        CMV cmv = new CMV(d);

        //Set of points 1 with K_PTS between (1,2) (6, 7)
        double distanceBetweenSetOfPointsSet1 = cmv.distance(testpoints.get(0), testpoints.get(4)); // ≈ 7.1

        //Set of points 2 with K_PTS between (2,3) (8,9)
        double distanceBetweenSetOfPointsSet2 = cmv.distance(testpoints.get(1), testpoints.get(5)); // ≈ 8.5

        //Set of points 3 with K_PTS between (3,4) (13,14)
        double distanceBetweenSetOfPointsSet3 = cmv.distance(testpoints.get(2), testpoints.get(6)); // ≈ 14.1


        //Set of points 1 (1,2) (6,7)
        assertTrue(distanceBetweenSetOfPointsSet1 > 1);
        assertFalse(distanceBetweenSetOfPointsSet1 > 10);

        //Set of points (2,3) (8,9)
        assertTrue(distanceBetweenSetOfPointsSet2 > 1);
        assertFalse(distanceBetweenSetOfPointsSet2 > 10);

        //Set of points (3,4) (13,14)
        assertTrue(distanceBetweenSetOfPointsSet3 > 1);
        assertTrue(distanceBetweenSetOfPointsSet3 > 10);

        assertTrue(cmv.cmv_12(d));

    }
}
