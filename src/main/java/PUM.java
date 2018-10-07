
class PUM {

	boolean[][] pum;

	PUM(Data d, CMV cmv) {
		pum = calculate_PUM(d, cmv);
	}
	// calculate decision matrix based on cmv function return values and CMVvalues rules for calculation
	boolean[][] calculate_PUM(Data d, CMV cmv) {
		boolean[][] pum = new boolean[15][15];
		for(int i = 0; i < cmv.CMV.length; i++) {
			for(int j = i + 1; j < cmv.CMV.length; j++) { //j = i + 1 to remove diagonal
				switch(d.getLCM(i, j)) {
					case NOTUSED:
						pum[i][j] = true;
						break;
					case ANDD:
						pum[i][j] = cmv.CMV[i] && cmv.CMV[j];
						break;
					case ORR:
						pum[i][j] = cmv.CMV[i] || cmv.CMV[j];
				}
				pum[j][i] = pum[i][j]; //should be symmetric with respect to the main diagonal
			}
		}
		return pum;
	}

}
