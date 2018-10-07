
class FUV {
	
	boolean[] fuv;
	
	FUV(Data d, PUM pumObj) {
		fuv = calculate_FUV(d, pumObj);
	}

	FUV(){
	}
	/*
	 * Returns true if entire row (input variable) of PUM is true.
	 * Otherwise false.
	 */
	boolean PUM_row_bool(PUM pumObj, int row){

		for(int i = 0; i < pumObj.pum[row].length; i++){
			if(!pumObj.pum[row][i] && i != row){
				return false;
			}
		}
		return true;
	}


	boolean[] calculate_FUV(Data d, PUM pumObj) {
		boolean[] fuv = new boolean[15];
		for(int i = 0; i < fuv.length; i++){

			// PUV[i] is false or all elements row i in PUM are true.
			fuv[i] = (!d.getPUV(i) || PUM_row_bool(pumObj, i));
		}
		return fuv;
	}
}
