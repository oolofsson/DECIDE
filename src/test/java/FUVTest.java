import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FUVTest {

    @Test
    public void testFUV_row_bool(){
        //Contract: Should return true if all elements in row index i are true, otherwise false.
        VolatileData d = new VolatileData();
        List<Point> testpoints = new ArrayList<>();
        d.setPoints(testpoints);
        CMV cmv = new CMV(d);
        cmv.CMV = new boolean[0];
        PUM pumObj = new PUM(d, cmv); // For test purpose CMV is not important

        pumObj.pum = new boolean[15][15];
        Arrays.fill(pumObj.pum[0], true);

        boolean[] testPUV = new boolean[15];
        d.setPUV(testPUV);

        FUV fuv = new FUV(d, pumObj);

        // All elements in row 0 are true
        assertTrue(fuv.PUM_row_bool(pumObj, 0));
        //All elements in row 1 are false
        assertFalse(fuv.PUM_row_bool(pumObj, 1));
    }

    @Test
    public void test(){
        //Contract: Index i in FUV is true if the same index i is false in PUV or if all elements of PUM in row i are true.
        VolatileData d = new VolatileData();
        List<Point> testpoints = new ArrayList<>();
        d.setPoints(testpoints);
        CMV cmv = new CMV(d);
        cmv.CMV = new boolean[0];
        PUM pumObj = new PUM(d, cmv); // For test purpose CMV is not important

        pumObj.pum = new boolean[15][15];
        Arrays.fill(pumObj.pum[0], true);
        Arrays.fill(pumObj.pum[2], true);

        boolean[] testPUV = new boolean[15];
        Arrays.fill(testPUV, true);
        testPUV[0] = true;
        testPUV[1] = false;
        testPUV[2] = false;
        d.setPUV(testPUV);

        FUV fuv = new FUV(d, pumObj);

        // all elements in row 0 of PUM are true
        assertTrue(fuv.fuv[0]);

        //index 1 in PUV is false
        assertTrue(fuv.fuv[1]);

        //index 2 in PUV is false and all elements in row 2 of PUM are true
        assertTrue(fuv.fuv[2]);

        //index 3 in PUV is true and all elements in row 3 of PUM are false
        assertFalse(fuv.fuv[3]);


    }


    }
