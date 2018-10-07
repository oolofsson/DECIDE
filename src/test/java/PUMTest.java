import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class PUMTest {

	@Test
	public void test() {
    //Contract: Calculates pum matrix based on LCMValues and CMV functions, compares to expected matrix calculated
    //by testing each cmv individually and calculating matrix based on predefined LCMValues. Assert Equal should be true.
		VolatileData d = new VolatileData();

		ArrayList<Point> testpoints = new ArrayList<Point>();
		testpoints.add(new Point(0.4, 0.0));
		testpoints.add(new Point(4.1, 0.1));
		testpoints.add(new Point(10.5, 2.0));
		testpoints.add(new Point(-4.1, 2.1));
		testpoints.add(new Point(-1.2, -5.1));
		testpoints.add(new Point(7.1, 4.8));
		testpoints.add(new Point(1.9, 2.4));
		d.setPoints(testpoints);

		d.setAPTS(3);
		d.setBPTS(2);
		d.setCPTS(3);
		d.setDPTS(2);
		d.setEPTS(1);
		d.setFPTS(3);
		d.setGPTS(4);
		d.setKPTS(2);
		d.setNPTS(3);
		d.setQPTS(2);
		d.setArea1(1.5);
		d.setArea2(3.2);
		d.setEpsilon(4.2);
		d.setRadius1(2.5);
		d.setRadius2(4.1);
		d.setDist(6.3);
		d.setLength1(7.2);
		d.setLength2(8.2);
		d.setQuads(4);

		Data.LCMValue[][] LCM = new Data.LCMValue[15][15];
		Data.LCMValue current = Data.LCMValue.NOTUSED;
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				if(i % 3 == 0) {
					LCM[i][j] = Data.LCMValue.NOTUSED;
				}else if(i % 2 == 0) {
					LCM[i][j] = Data.LCMValue.ANDD;
				}else {
					LCM[i][j] = Data.LCMValue.ORR;
				}
			}
		}
		d.setLCM(LCM);
		CMV cmv = new CMV(d);
		PUM pum = new PUM(d, cmv);
		//expected matrix based on calculated cmv values and the LCMValues above.
		boolean[][] expected = {
				{false, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
				{true, false, true, true, true, true, true, true, true, true, true, true, true, true, true},
				{true, true, false, false, false, false, false, false, false, false, false, false, false, false, false},
				{true, true, false, false, true, true, true, true, true, true, true, true, true, true, true},
				{true, true, false, true, false, false, false, false, false, false, false, false, false, false, false},
				{true, true, false, true, false, false, true, true, true, true, true, true, true, true, true},
				{true, true, false, true, false, true, false, true, true, true, true, true, true, true, true},
				{true, true, false, true, false, true, true, false, true, true, true, true, true, true, true},
				{true, true, false, true, false, true, true, true, false, false, false, false, false, false, false},
				{true, true, false, true, false, true, true, true, false, false, true, true, true, true, true},
				{true, true, false, true, false, true, true, true, false, true, false, true, false, false, true},
				{true, true, false, true, false, true, true, true, false, true, true, false, true, true, true},
				{true, true, false, true, false, true, true, true, false, true, false, true, false, true, true},
				{true, true, false, true, false, true, true, true, false, true, false, true, true, false, true},
				{true, true, false, true, false, true, true, true, false, true, true, true, true, true, false}
		};

		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				assertEquals(pum.pum[i][j], expected[i][j]);
			}
		}
	}

}
