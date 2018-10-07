
class Launch {

	// returns answer based on cmv, pum and puv
	static boolean get_answer(FUV fuv) {
		boolean[] array = fuv.fuv;
		for (int i = 0; i < array.length; i++){
			if(!array[i]){
				return false;
			}
		}
		return true;
	}

}
