import java.util.ArrayList;
import java.util.List;

class VolatileData extends Data {

	VolatileData() {

	}

	void setPoints(List<Point> POINTS) {
		this.POINTS = new ArrayList<>(POINTS);
	}

	void setLength1(double LENGTH1) {
		this.LENGTH1 = LENGTH1;
	}

	void setRadius1(double RADIUS1) {
		this.RADIUS1 = RADIUS1;
	}

	void setEpsilon(double EPSILON) {
		this.EPSILON = EPSILON;
	}

	void setArea1(double AREA1) {
		this.AREA1 = AREA1;
	}

	void setQPTS(int Q_PTS) {
		this.Q_PTS = Q_PTS;
	}

	void setQuads(int QUADS) {
		this.QUADS = QUADS;
	}

	void setDist(double DIST) {
		this.DIST = DIST;
	}

	void setNPTS(int N_PTS) {
		this.N_PTS = N_PTS;
	}

	void setKPTS(int K_PTS) {
		this.K_PTS = K_PTS;
	}

	void setAPTS(int A_PTS) {
		this.A_PTS = A_PTS;
	}

	void setBPTS(int B_PTS) {
		this.B_PTS = B_PTS;
	}

	void setCPTS(int C_PTS) {
		this.C_PTS = C_PTS;
	}

	void setDPTS(int D_PTS) {
		this.D_PTS = D_PTS;
	}

	void setEPTS(int E_PTS) {
		this.E_PTS = E_PTS;
	}

	void setFPTS(int F_PTS) {
		this.F_PTS = F_PTS;
	}

	void setGPTS(int G_PTS) {
		this.G_PTS = G_PTS;
	}

	void setLength2(double LENGTH2) {
		this.LENGTH2 = LENGTH2;
	}

	void setRadius2(double RADIUS2) {
		this.RADIUS2 = RADIUS2;
	}

	void setArea2(double AREA2) {
		this.AREA2 = AREA2;
	}

	boolean setLCM(LCMValue[][] LCM) {
		if (LCM.length != 15 || LCM[0].length != 15) {
			return false;
		}

		this.LCM = new LCMValue[15][15];

		for (int i = 0; i < 15; i++) {
			this.LCM[i] = LCM[i].clone();
		}

		return true;
	}

	boolean setPUV(boolean[] PUV) {
		if (PUV.length != 15) {
			return false;
		}

		this.PUV = PUV;
		return true;
	}
}
