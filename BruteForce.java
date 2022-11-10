package closestpair;

import static closestpair.Point.dist;

/**
 *
 * @author Prestamos G
 */
public class BruteForce {
        
    public  static Double bruteForce(Point[] P, int n) {
            Double min = Double.MAX_VALUE;
            Double currMin = 0.0;

            for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                    currMin = dist(P[i], P[j]);
                    if (currMin < min) {
                    min = currMin;
                    }
            }
            }
            return min;
    }
    
}
