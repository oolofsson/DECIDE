import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by heysunk on 2018-01-22.
 */
public class CMV6Test {

    @Test
    // contact: computes the distance between the given line segment and the point.
    public void test1() throws Exception{
        CMV cmv = new CMV();
        Point p1 = new Point(0,0);
        Point p2 = new Point(0, 1);
        Point p3 = new Point(0, 2);
        Point p4 = new Point(0.5, 0.5);
        double ORACLE = 1.0;
        double ORACLE2 = 0.5;
        assertEquals(ORACLE, cmv.computeMinimumDistance(p1,p2,p3), 0.00001);
        assertEquals(ORACLE2, cmv.computeMinimumDistance(p1,p2,p4), 0.00001);
    }

    @Test
    // contact: returns true if there exists at least one set of N_PTS consecutive points
    // at least one of the points lies a distance grater than DIST from the line between the
    // first and last of these N_PTS points.
    public void test2() throws Exception{
        VolatileData d = new VolatileData();
        List<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(0,2));
        points.add(new Point(0,1));
        d.setPoints(points);
        d.setNPTS(3);
        d.setDist(0.99);
        CMV cmv = new CMV();
        assertTrue(cmv.cmv_6(d));
    }

    @Test
    // contact: returns false if there are less than 3 points in the points List
    public void test3() throws Exception{

        VolatileData d = new VolatileData();
        List<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(0,2));
        d.setPoints(points);
        d.setNPTS(3);
        d.setDist(0.99);
        CMV cmv = new CMV();
        assertFalse(cmv.cmv_6(d));
    }

    @Test
    // contract: returns false if there does not exist any point within the set of
    // consecutive points which lies at a distance greater than DIST from the line
    public void test4() throws Exception{
        VolatileData d = new VolatileData();
        List<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(0,1));
        points.add(new Point(0,2));
        d.setPoints(points);
        d.setNPTS(3);
        d.setDist(1.01);
        CMV cmv = new CMV();
        assertFalse(cmv.cmv_6(d));
    }

    @Test
    // contract: returns false if N_PTS is less than 3
    public void test5() throws Exception{
        VolatileData d = new VolatileData();
        List<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(0,1));
        points.add(new Point(0,2));
        d.setPoints(points);
        d.setNPTS(0);
        d.setDist(1.01);
        CMV cmv = new CMV();
        assertFalse(cmv.cmv_6(d));
    }

}