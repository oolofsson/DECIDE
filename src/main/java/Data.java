import java.io.InputStream;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class Data {

	enum LCMValue {
		NOTUSED, ANDD, ORR;
	};

	protected List<Point> POINTS;
	protected double LENGTH1, RADIUS1, EPSILON, AREA1;
	protected int Q_PTS, QUADS;
	protected double DIST;
	protected int N_PTS, K_PTS, A_PTS, B_PTS, C_PTS, D_PTS, E_PTS, F_PTS, G_PTS;
	protected double LENGTH2, RADIUS2, AREA2;
	protected LCMValue[][] LCM = new LCMValue[15][15];
	protected boolean[] PUV = new boolean[15];

	Data(InputStream pipe) {
		Kattio io = new Kattio(pipe, System.out);

		// retrieve the points
		int NUMPOINTS = io.getInt();

		POINTS = new ArrayList<>((int) Math.ceil(NUMPOINTS / 0.75));

		for (int i = 0; i < NUMPOINTS; i++) {
			POINTS.add(new Point(io.getDouble(), io.getDouble()));
		}

		LENGTH1 = io.getDouble();
		RADIUS1 = io.getDouble();
		EPSILON = io.getDouble();
		AREA1 = io.getDouble();
		Q_PTS = io.getInt();
		QUADS = io.getInt();
		DIST = io.getDouble();
		N_PTS = io.getInt();
		K_PTS = io.getInt();
		A_PTS = io.getInt();
		B_PTS = io.getInt();
		C_PTS = io.getInt();
		D_PTS = io.getInt();
		E_PTS = io.getInt();
		F_PTS = io.getInt();
		G_PTS = io.getInt();
		LENGTH2 = io.getDouble();
		RADIUS2 = io.getDouble();
		AREA2 = io.getDouble();

		// retrieve values for the LCM matrix
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				String word = io.getWord();

				switch (word) {
					case "ANDD":
						LCM[i][j] = LCMValue.ANDD;
						break;

					case "ORR":
						LCM[i][j] = LCMValue.ORR;
						break;

					default:
						LCM[i][j] = LCMValue.NOTUSED;
				}
			}
		}

		// retrieve values for the PUV vector
		for (int i = 0; i < 15; i++) {
			PUV[i] = io.getWord().equals("true");
		}
	}

	Data() {

	}

	int getNumPoints() {
		return POINTS.size();
	}

	// return a copy of the list instead of the original
	List<Point> getPoints() {
		return new ArrayList<>(POINTS);
	}

	double getLength1() {
		return LENGTH1;
	}

	double getRadius1() {
		return RADIUS1;
	}

	double getEpsilon() {
		return EPSILON;
	}

	double getArea1() {
		return AREA1;
	}

	int getQPTS() {
		return Q_PTS;
	}

	int getQuads() {
		return QUADS;
	}

	double getDist() {
		return DIST;
	}

	int getNPTS() {
		return N_PTS;
	}

	int getKPTS() {
		return K_PTS;
	}

	int getAPTS() {
		return A_PTS;
	}

	int getBPTS() {
		return B_PTS;
	}

	int getCPTS() {
		return C_PTS;
	}

	int getDPTS() {
		return D_PTS;
	}

	int getEPTS() {
		return E_PTS;
	}

	int getFPTS() {
		return F_PTS;
	}

	int getGPTS() {
		return G_PTS;
	}

	double getLength2() {
		return LENGTH2;
	}

	double getRadius2() {
		return RADIUS2;
	}

	double getArea2() {
		return AREA2;
	}

	LCMValue getLCM(int x, int y) {
		return LCM[x][y];
	}

	boolean getPUV(int x) {
		return PUV[x];
	}
}
