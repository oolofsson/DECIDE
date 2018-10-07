/**
 * Created by heysunk on 2018-01-22.
 */
class Quadrant {
    enum quadrant {
        FIRST, SECOND, THIRD, FORTH
    }
    static quadrant determineQuadrant(Point p)
    {
        // Either Quad 1 or Quad 4
        if (p.getX() >= 0){
            if (p.getY() >= 0) {
                return quadrant.FIRST;
            }
            else{
                return quadrant.FORTH;
            }
        }
        // either Quad 2 or 3
        else
        {
            if (p.getY() >= 0){
                return quadrant.SECOND;
            }
            else {
                return quadrant.THIRD;
            }
        }
    }
}
