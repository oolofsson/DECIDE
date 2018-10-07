import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by heysunk on 2018-01-22.
 */
public class CMV4Test {
    @Test
    // contact: returns the quadrant the given point resides in. If there exists ambiguity
    // to which quadrant a point belongs to priority of decision will be by quadrant number
    public void test1() throws Exception {
        Point p = new Point(0,0);
        Point p2 = new Point(-1,1);
        Quadrant.quadrant ORACLE = Quadrant.quadrant.FIRST;
        assertEquals(ORACLE, Quadrant.determineQuadrant(p));
        Quadrant.quadrant ORACLE1 = Quadrant.quadrant.SECOND;
        assertEquals(ORACLE1, Quadrant.determineQuadrant(p2));
    }

    @Test
    // contact: Returns true if there exists at least one set of Q_PTS
    // consecutive data points such that they lie in more than QUADS quadrants.
    public void test2() throws Exception {
        VolatileData d = new VolatileData();
        d.setQPTS(3);
        d.setQuads(2);
        List<Point> points = new ArrayList<>();
        points.add(new Point(1,1));
        points.add(new Point(-1,1));
        points.add(new Point(1, -1));
        d.setPoints(points);
        CMV cmv = new CMV();

        assertTrue(cmv.cmv_4(d));
    }

    @Test
    // contact: Returns false if there does not exist at least one set of Q_PTS
    // consecutive data points such that they lie in more than QUADS quadrants.
    public void test3() throws Exception {
        VolatileData d = new VolatileData();
        d.setQPTS(3);
        d.setQuads(2);
        List<Point> points = new ArrayList<>();
        points.add(new Point(1,1));
        points.add(new Point(-1,1));
        points.add(new Point(0, 0));
        d.setPoints(points);
        CMV cmv = new CMV();

        assertFalse(cmv.cmv_4(d));
    }


}