import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by heysunk on 2018-01-23.
 */
public class LaunchTest {

    @Test
    //contract: returns true if all elements in the fuv array are true
    public void test1() throws Exception{
        FUV fuv = new FUV();
        boolean[] array = {true, true, true, true, true,
                true, true, true, true, true,
                true, true, true, true, true};
        fuv.fuv = array;

        assertTrue(Launch.get_answer(fuv));
    }

    @Test
    // contract: returns false if there exists an element in the fuv array which is false
    public void test2() throws Exception{
        FUV fuv = new FUV();
        boolean[] array = {true, true, true, true, true,
                true, true, false, true, true,
                true, true, true, true, true};
        fuv.fuv = array;

        assertFalse(Launch.get_answer(fuv));
    }


}