import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by heysunk on 2018-01-21.
 */
public class CMV5Test {
    @Test
    // Contract: if there are less than 2 points in the list cmv5 should return false
    public void test1() throws Exception {
        CMV cmv = new CMV();
        VolatileData d = new VolatileData();
        List<Point> points = new ArrayList<>();
        d.setPoints(points);
        assertFalse(cmv.cmv_5(d));
        points.add(new Point(0,5));
        d.setPoints(points);
        assertFalse(cmv.cmv_5(d));
    }

    @Test
    // Contract: returns true if there exists at least one set of two consecutive data points
    // such that X[j] - X[i] < 0 (where i = j-1)
    public void test2() throws Exception {
        CMV cmv = new CMV();
        VolatileData d = new VolatileData();
        List<Point> points = new ArrayList<>();
        points.add(new Point(1,0));
        points.add(new Point(0,0));
        // X[1] - X[0] = -1
        // -1 < 0
        d.setPoints(points);
        assertTrue(cmv.cmv_5(d));
    }

    @Test
    // Contract: returns false if there is no set of two consecutive data points
    // such that X[j] - X[i] < 0 (where i = j-1)
    public void test3() throws Exception {
        CMV cmv = new CMV();
        VolatileData d = new VolatileData();
        List<Point> points = new ArrayList<>();
        points.add(new Point(0,0));
        points.add(new Point(1,0));
        d.setPoints(points);
        assertFalse(cmv.cmv_5(d));
    }


}