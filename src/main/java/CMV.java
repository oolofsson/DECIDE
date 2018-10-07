import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class CMV {

	boolean[] CMV;

	CMV(Data in) {
		CMV = new boolean[15];
		CMV[0] = cmv_0(in);
		CMV[1] = cmv_1(in);
		CMV[2] = cmv_2(in);
		CMV[3] = cmv_3(in);
		CMV[4] = cmv_4(in);
		CMV[5] = cmv_5(in);
		CMV[6] = cmv_6(in);
		CMV[7] = cmv_7(in);
		CMV[8] = cmv_8(in);
		CMV[9] = cmv_9(in);
		CMV[10] = cmv_10(in);
		CMV[11] = cmv_11(in);
		CMV[12] = cmv_12(in);
		CMV[13] = cmv_13(in);
		CMV[14] = cmv_14(in);
	}

	CMV() {

    }

  //Calculate the distance between two points
	double distance(Point p1, Point p2) {
		return Math.sqrt( Math.pow((p1.getX()-p2.getX()), 2) + Math.pow((p1.getY()-p2.getY()), 2) );
	}
	//check if three points fits within a circle with passed radius
	boolean circle(Point p1, Point p2, Point p3, double radius) {
		//Get center point
		Point center = new Point((p1.getX() + p2.getX() + p3.getX()) / 3,
				(p1.getY() + p2.getY() + p3.getY()) / 3);
		//Check that all points are within the circle around center point
		return distance(center, p1) <= radius
				&& distance(center, p2) <= radius
				&& distance(center, p3) <= radius;
	}
	//Gives the centre point of two points.
	Point centre(Point p1, Point p2) {
		return new Point((p1.getX()+p2.getX())/2,(p1.getY()+p2.getY())/2);
	}

	// Gives a new Point, which represents a vector between the two points.
	Point subtract(Point from, Point to) {
		return new Point(to.getX()-from.getX(), to.getY()-from.getY());
	}
	// calculate angle of sides connected by point p2 by using the law of cosines
	private boolean angle(Point p1, Point p2, Point p3, double epsilon) {
		double side1 = distance(p1, p2);
		double side2 = distance(p2, p3);
		double side3 = distance(p3, p1);
		if(side1 != 0 && side2 != 0) {
			double a = (Math.pow(side1, 2) + Math.pow(side2, 2) - Math.pow(side3, 2)) / (2*side1*side2);
			double angle = Math.acos(a);
			if(angle > Math.PI + epsilon || angle < Math.PI - epsilon) return true;
		}
		return false;
	}
	// Get the angle of the three points.
	private double get_angle(Point p1, Point p2, Point p3) {
		double alpha = Math.atan2(p3.getX()-p2.getX(), p3.getY()-p2.getY()) - Math.atan2(p1.getX()-p2.getX(), p1.getY()-p2.getY());
		if (alpha < 0) {
			alpha += 2*Math.PI;
		}
		return alpha;
}
	//not_contained: checks wether 3 points fits within circle of param radius
	boolean not_contained(Point p1, Point p2, Point p3, double radius) {
		double l12 = distance(p1,p2);
		double l13 = distance(p1,p3);
		double l23 = distance(p2,p3);
		double max = Math.max(l23, Math.max(l12, l13));
		// If distance between any 2 points is greater than diameter, then the points aren't contained.
		if (max>radius*2) {
			return true;
		}
		// Otherwise, if any 2 points are the same then the points are contained.
		if (l12==0 || l13==0 || l23==0) {
			return false;
		}
		// If the 3 points are colinear then they're contained within the circle.
		if ((p2.getY()-p1.getY())*(p3.getX()-p2.getX()) == (p3.getY()-p2.getY())*(p2.getX()-p1.getX())) {
			return false;
		}

		// Otherwise, the points form a trinagle.
		// Calculate the angles to see if we have an obstute triangle, then the points are contained.
		/*Double alphaA = get_angle(subtract(p1,p2), subtract(p1,p3));
		Double alphaB = get_angle(subtract(p2,p1), subtract(p2,p3));
		Double alphaC = get_angle(subtract(p3,p1), subtract(p3,p2));*/

		Double alphaA = get_angle(p1,p2,p3);
		Double alphaB = get_angle(p2,p1,p3);
		Double alphaC = get_angle(p1,p3,p2);


		if (alphaA > Math.PI) {alphaA = 2*Math.PI - alphaA;}
		if (alphaB > Math.PI) {alphaB = 2*Math.PI - alphaB;}
		if (alphaC > Math.PI) {alphaC = 2*Math.PI - alphaC;}
		if (alphaA >= Math.PI/2.0 || alphaB >= Math.PI/2.0 || alphaC >= Math.PI/2.0) {
			return false;
		}

		//If we have an Acute triangle, then our points are inside its circumcircle, so we compute the circumradius to
		// see if it's larger or not, in which case one point is outside the circumcircle
		if ((l12*l13*l23)/Math.sqrt(((l12+l13+l23)*(l13+l23-l12)*(l23+l12-l13)*(l12+l13-l23))) > radius) {
			return true;
		}

		return false; // Otherwise they're contained.
	}

    // Computes the area of the triangle between the three points, p1, p2, p3.
    // Calculated via the shoelace formula
	double computerTriangleArea(Point p1, Point p2, Point p3){
	    double c1,c2,c3;
	    c1 = p1.getX() * (p2.getY() - p3.getY());
	    c2 = p2.getX() * (p3.getY() - p1.getY());
	    c3 = p3.getX() * (p1.getY() - p2.getY());
	    return Math.abs((c1+c2+c3)/2);
    }
		//computeMinimumDistance: Computes mininimum distance from line between two points
    double computeMinimumDistance(Point lineStart, Point lineEnd, Point p3){
	    double l2 = Math.pow(distance(lineStart, lineEnd),2);
	    if (l2 == 0) {
	        return distance(lineStart, p3);
        }
        double t = ((p3.getX() - lineStart.getX()) * (lineEnd.getX() - lineStart.getX()) + (p3.getY() - lineStart.getY()) * (lineEnd.getY() - lineStart.getY())  ) / l2;
                t = Math.max(0, Math.min(1, t));
                return distance(p3, new Point(lineStart.getX() + t * (lineEnd.getX() - lineStart.getX()),
                        lineStart.getY() + t * (lineEnd.getY() - lineStart.getY())));
    }


	//CMV0 Checks whether at least two consecutive points has a distance greater than LENGHT1.
	boolean cmv_0(Data d) {
		double length = d.LENGTH1;
		List<Point> points = d.getPoints();
		Point p1, p2;
		for (int i=0; i < points.size()-1; i++) {
			p1 = points.get(i);
			p2 = points.get(i+1);
			if (distance(p1,p2) > length) {
				return true;
			}
		}
		return false;
	}
	//CMV1 Checks whether any 3 consecutive points is within a circle with RADIUS1.
	boolean cmv_1(Data d) {
		double radius = d.RADIUS1;
		List<Point> points = d.getPoints();
		if (points.size() < 3) {
			return false; // Must have at least 3 points.
		}
		Point p1, p2, p3;
		for (int i=0; i < points.size()-2; i++) {
			p1 = points.get(i);
			p2 = points.get(i+1);
			p3 = points.get(i+2);
			if (not_contained(p1,p2,p3,radius)) {
				return true;
			}

		}
		return false;
	}
	//CMV2 Checks whether any 3 consecutive points creates an angle that such that angle < PI - EPSILON OR angle > PI + EPSILON
	boolean cmv_2(Data d) {
		double epsilon = d.EPSILON;
		List<Point> points = d.getPoints();
		if (points.size() < 3) {
			return false; // Must have at least 3 points.
		}
		Point p1, p2, p3;
		for (int i=0; i < points.size()-2; i++) {
			p1 = points.get(i);
			p2 = points.get(i+1);
			p3 = points.get(i+2);
			// If first or last points coincide with the vertex, no angle can be formed.
			if ((p1.getX()==p2.getX() && p1.getY()==p2.getY()) || (p3.getX()==p2.getX() && p3.getY()==p2.getY())) {
				// Do nothing.
			} else {
				// Form the two vectors for the angle, and calculate the angle.
				//Double alpha = get_angle(subtract(p2,p1),subtract(p2,p3));
				Double alpha = get_angle( p1,  p2,  p3);
				if (alpha < (Math.PI-epsilon) || alpha > (Math.PI+epsilon)) {
					return true;
				}
			}
		}
		return false;
	}

	/* There exists at least one set of three consecutive data points that are the vertices of a triangle
    with area greater than AREA1.
    */
	boolean cmv_3(Data d) {
	    int numPoints = d.getNumPoints();
	    if (numPoints >= 3)
        {
            Point p1, p2, p3;
            List<Point> points = d.POINTS;
            double area1 = d.AREA1;
            p2 = points.get(0);
            p3 = points.get(1);
            double area;
            for (int i = 2; i < numPoints; i++)
            {
                p1 = p2;
                p2 = p3;
                p3 = points.get(i);
                area = computerTriangleArea(p1, p2, p3);

                if (area > area1) {
                    return true;
                }
            }
        }
		return false;
	}
  // Determine that the #q_pts points lie in atleast "quads" different quadrants.
	boolean cmv_4(Data d) {
	    int q_pts = d.getQPTS();
	    int quads = d.getQuads();
        Set<Quadrant.quadrant> seenQuads;
	    int numPoints = d.getNumPoints();

	    List<Point> points = d.getPoints();
	    for (int i = q_pts - 1; i < numPoints; i++){
	        seenQuads = new HashSet<>();
	        for (int j = 0; j < q_pts
                    ; j++){
                Point p = points.get(i-j);
                seenQuads.add(Quadrant.determineQuadrant(p));
            }
            if (seenQuads.size() > quads) {
	            return true;
            }
        }
		return false;
	}
	//CMV5 Checks whether there exists at least one set of two consecutive points (X[i], Y[i]), (X[i], Y[i]) such that X[j] -
	//X[i] < 0
	boolean cmv_5(Data d) {
	    int numPoints = d.getNumPoints();
	    List<Point> points = d.getPoints();
	    Point p1, p2;
	    for (int i = 1; i < numPoints; i++){
	        p1 = points.get(i - 1);
	        p2 = points.get(i);
	        if (p2.getX() < p1.getX()){
	            return true;
            }
        }
		return false;
	}
	//CMV6 checks that at least one set of N_PTS consecutive data points such that at least on of the points lies a
	//distance greater than DIST from the line joining the first and last of these N_PTS points.
	boolean cmv_6(Data d) {
        int n_pts = d.getNPTS();
        int numPoints = d.getNumPoints();
        double dist = d.getDist();
        if ((numPoints >= 3) && (n_pts >= 3)) {
            List<Point> points = d.getPoints();
            for (int i = n_pts - 1; i < numPoints; i++) {
                Point first = points.get(i - (n_pts -1));
                Point last = points.get(i);
                for (int j = 1; j < n_pts - 1; j++) {
                    Point p = points.get(i - j);
                    Double distance = computeMinimumDistance(first, last, p);
                    if (distance > dist) {
                        return true;
                    }
                }
            }
        }
        return false;
	}
	//CMV7 Checks whether any two points separated KPTS points points has a distance greater than LENGTH1.
	boolean cmv_7(Data d) {
		if(d.getNumPoints() >= 3) {
			List<Point> points = d.getPoints();
			for(int i = 0; i < points.size() - d.getKPTS() - 1; i++) {
				if(distance(points.get(i), points.get(i + d.getKPTS() + 1)) > d.getLength1()) return true;
			}
		}
		return false;
	}
	//CMV8 checks whether any 3 points separated by APTS and BPTS points fits within a circle with radius RADIUS1
	boolean cmv_8(Data d) {
		if(d.getNumPoints() >= 5) {
			List<Point> points = d.getPoints();
			for(int i = 0; i < points.size() - d.getAPTS() - d.getBPTS() - 2; i++) {
				if(!not_contained(points.get(i), points.get(i + d.getAPTS() + 1), points.get(i + d.getAPTS() + d.getBPTS() + 2), d.getRadius1())) {
					return true;
				}
			}
		}
		return false;
	}
	//return true if there is any case where 3 points separated by CPTS and DPTS points form a triangle such that the sides around
	//the second point has angle bigger than PI + epsilon or smaller than PI - epsilon.
	boolean cmv_9(Data d) {
		if(d.getNumPoints() >= 5) {
			List<Point> points = d.getPoints();
			for(int i = 0; i < points.size() - d.getCPTS() - d.getDPTS() - 2; i++) {
				if(angle(points.get(i), points.get(i + d.getCPTS() + 1), points.get(i + d.getCPTS() + d.getDPTS() + 2), d.getEpsilon())) {
					return true;
				}
			}
		}
		return false;
	}
	//CMV10 There exists at least one set of threee data points separated by E_PTS and F_PTS consecutive points that
	//forms a triangle with area bigger than AREA1.
	boolean cmv_10(Data d) {

		if (d.getNumPoints() < 5) {
			return false;
		}

		List<Point> points = d.getPoints();

		for (int i = 0; i < points.size() - d.getEPTS() - d.getFPTS() - 2; i++) {
			Point first = points.get(i);
			Point second = points.get(i + d.getEPTS() + 1);
			Point third = points.get(i + d.getEPTS() + d.getFPTS() + 2);

			if (computerTriangleArea(first, second, third) > d.getArea1()) {
				return true;
			}
		}

		return false;
	}
	//CMV11 Checks whether there exists at least one set of two points (X[i], Y[i]), (X[i], Y[i]), separated by exactly
	//G_PTS points such that X[j] - X[i] < 0
	boolean cmv_11(Data d) {

		if (d.getNumPoints() < 3) {
			return false;
		}

		List<Point> points = d.getPoints();

		for (int i = 0; i < d.getNumPoints() - d.getGPTS() - 1; i++) {
			if (points.get(i).getX() - points.get(i + d.getGPTS() + 1).getX() < 0) {
				return true;
			}
		}

		return false;
	}
	/*
	* Pre-condition: LENGTH2 >= 0 && NUMPOINTS >= 3
	* TRUE:
	* At least one set of 2 points (K_PTS separation (points) between) && distance > LENGTH1
	* AND
	* At least one set of 2 points (same or a new set) (K_PTS separation (points) between) && distance > LENGTH2
	 */
	boolean cmv_12(Data d) {
		//pre-condition
		if((d.getNumPoints() >= 3) && (d.getLength2() >= 0)) {
			List<Point> points = d.getPoints();
			boolean req1 = false;
			boolean req2 = false;

			Point p1, p2;

			for (int i = 0; i < d.getNumPoints() - d.getKPTS() - 1; i++) {
				p1 = points.get(i);
				p2 = points.get(i + d.getKPTS() + 1);
				double distance = distance(p1, p2);

				if (distance > d.getLength1() && !req1) req1 = true;
				if ((distance > d.getLength2()) && !req2) req2 = true;

				if (req1 && req2) return true;
			}
		}
		return false;
	}
	/*
	* Pre-condition: RADIUS2 >= 0 && NUMPOINTS >= 5
	* TRUE:
	* At least one set of 3 points (A_PTS and B_PTS separation (points) between respectively) && not contained in circle <= RADIUS1
	* AND
	* At least one set of 3 points (same or a new set) (A_PTS and B_PTS separation (points) between respectively)
	* && not contained in circle <= RADIUS2
	 */
	boolean cmv_13(Data d) {

		//pre-condition
		if((d.getNumPoints() >= 5) && (d.getRadius2() >= 0)) {
			List<Point> points = d.getPoints();
			boolean req1 = false;
			boolean req2 = false;
			Point p1, p2, p3;

			for (int i = 0; i < (d.getNumPoints() - d.getAPTS() - d.getDPTS() - 2); i++) {
				p1 = points.get(i);
				p2 = points.get(i + d.getAPTS() + 1);
				p3 = points.get(i + d.getAPTS() + d.getDPTS() + 2);

				//only needs to be true 1 time.
				if(!req1) req1 = not_contained(p1, p2, p3, d.getRadius1());
				//only needs to be true 1 time.
				if(!req2) req2 = not_contained(p1, p2, p3, d.getRadius2());
				//We have passed both requirement for radius1 and radius2
				if(req1 && req2) return true;
			}
		}
		return false;
	}

	/*
	* Pre-condition: AREA2 >= 0 && NUMPOINTS >= 5
	* TRUE:
	* At least one set of 3 points (E_PTS and F_PTS separation (points) between respectively), that are vertices of a triangle > AREA1
	* AND
	* At least one set of 3 points (same or a new set) (E_PTS and F_PTS separation (points) between respectively), that are
	* vertices of a triangle > AREA2
	 */
	boolean cmv_14(Data d) {
		//pre-condition
		if((d.getNumPoints() >= 5) && (d.getArea2() >= 0)) {
			List<Point> points = d.getPoints();
			boolean req1 = false;
			boolean req2 = false;
			Point p1, p2, p3;

			for (int i = 0; i < (d.getNumPoints() - d.getEPTS() - d.getFPTS() - 2); i++) {
				p1 = points.get(i);
				p2 = points.get(i + d.getEPTS() + 1);
				p3 = points.get(i + d.getEPTS() + d.getFPTS() + 2);

				//only needs to be true 1 time.
				if(!req1 && (computerTriangleArea(p1, p2, p3) > d.getArea1()))
					req1 = true;
				//only needs to be true 1 time.
				if(!req2 && (computerTriangleArea(p1, p2, p3) > d.getArea2()))
					req2 = true;

				//We have passed both requirements for Area1 and Area2
				if(req1 && req2) return true;
			}
		}
		return false;
	}
}
