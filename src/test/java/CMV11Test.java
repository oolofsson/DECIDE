import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;



public class CMV11Test {

    @Test
    public void test1() {
        // Contract: return FALSE if the number of points is less than 3

        VolatileData d = new VolatileData();

        d.setPoints(new ArrayList<Point>());
        d.setGPTS(2);

        CMV cmv = new CMV(d);
        assertFalse(cmv.cmv_11(d));
    }

    @Test
    public void test2(){
        // Contract: return TRUE when there are two points with X_j - X_i >= 0, where j - i = G_PTS

        VolatileData d = new VolatileData();

        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 5));
        points.add(new Point(1, 4));
        points.add(new Point(0, 3));
        points.add(new Point(2, 2));
        points.add(new Point(0, 1));

        d.setPoints(points);
        d.setGPTS(2);

        CMV cmv = new CMV(d);
        assertTrue(cmv.cmv_11(d));
    }

    @Test
    public void test3(){
        // Contract: return FALSE when there are no two points with X_j - X_i >= 0, where j - i = G_PTS

        VolatileData d = new VolatileData();

        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 1));
        points.add(new Point(2, 2));
        points.add(new Point(-1, 3));
        points.add(new Point(1, 4));
        points.add(new Point(-2, 5));

        d.setPoints(points);
        d.setGPTS(2);

        CMV cmv = new CMV(d);
        assertFalse(cmv.cmv_11(d));
    }
}
