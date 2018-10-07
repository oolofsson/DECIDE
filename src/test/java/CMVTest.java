import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by heysunk on 2018-01-21.
 */
public class CMVTest {

    @Test(expected = NullPointerException.class)
    // contract: instantiating a CMV object with an empty Data object should result in a NullPointerException
    public void test1() throws Exception {
        Data d = new Data();
        CMV cmv = new CMV(d);
    }

    @Test
    // contract: instantiating a CMV with no arguments should not throw any exceptions
    public void test2() throws Exception {
        CMV cmv = new CMV();
    }

}