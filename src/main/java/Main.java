
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		print(Decide());
	}
	// goes through the process of reading data, calculating pum decision matrix based on cmv function return values and using fuv to get final answer.
	/*
		Takes input according to doc/input-specification.txt
		Returns either true or false whether to launch the (hypothetical) ballistic missile or not

	 */
	public static boolean Decide() {
		Data d = new Data(System.in);
		CMV cmv = new CMV(d);
		PUM pum = new PUM(d, cmv);
		FUV fuv = new FUV(d, pum);
		return Launch.get_answer(fuv);
	}

	private static void print(boolean ans) {
		if (ans) {
			System.out.print("YES");
		} else {
			System.out.print("NO");
		}
	}

}
