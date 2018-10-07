class Point {
	private double x, y;
	
	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	double getX() {
		return x;
	}
	
	double getY() {
		return y;
	}
	
	double distanceTo(Point other) {
		return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
	}
}