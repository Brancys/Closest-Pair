package closestpair;

/**
 *
 * @author brancysb
 */
import java.util.Arrays;
import java.util.Comparator;

// A divide and conquer program in Java
// to find the smallest distance from a
// given set of points.

// A structure to represent a two coordinates Point 
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
            this.x = x;
            this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    

    // A utility function to find the
    // distance between two points
    public static Double dist(Point p1, Point p2) {
            return (Double) squareRoot((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }
    
   //Squared Root Method without Math class
    public static double squareRoot(int num){  
        //temporary variable  
        double t;  
        double sqrtroot=num/2;  
        do{  
        t=sqrtroot;  
        sqrtroot=(t+(num/t))/2;  
        } while((t-sqrtroot)!= 0);  
        
        return sqrtroot;  
    }  
    public static double Minimun(double num1, double num2){  
        if(num1<=num2)
            return num1;
       //return minimun of two double values
        return num2;
    }
    
    public static double AbsoluteValue(double num){    
        if (num<0)
            return (-1*num);
        //return the Absolute Value of a Double number
        return num;       
    }
    
    // A Brute Force method to return the
    // smallest distance between two points
    // in P[] of size n
    public static Double bruteForce(Point[] P, int n) {
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

    // A utility function to find the
    // distance between the closest points of
    // strip of given size. All points in
    // strip[] are sorted according to
    // y coordinate. They all have an upper
    // bound on minimum distance as d.
    // Note that this method seems to be
    // a O(n^2) method, but it's a O(n)
    // method as the inner loop runs at most 6 times
    public static Double stripClosest(Point[] strip, int size, Double d) {
            Double min = d; // Initialize the minimum distance as d

            Arrays.sort(strip, 0, size, new PointYComparator());

            // Pick all points one by one and try the next points till the difference
            // between y coordinates is smaller than d.
            // This is a proven fact that this loop runs at most 6 times
            for (int i = 0; i < size; ++i) {
            for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < min; ++j) {
                    if (dist(strip[i], strip[j]) < min) {
                    min = dist(strip[i], strip[j]);
                    }
            }
            }

            return min;
    }

    // A recursive function to find the
    // smallest distance. The array P contains
    // all points sorted according to x coordinate
    public static Double closestUtil(Point[] P, int startIndex,int endIndex){

            // If there are 2 or 3 points, then use brute force
            if ((endIndex - startIndex) <= 3) {
            return bruteForce(P, endIndex);
            }

            // Find the middle point
            int mid = startIndex + (endIndex - startIndex) / 2;
            Point midPoint = P[mid];

            // Consider the vertical line passing
            // through the middle point calculate
            // the smallest distance dl on left
            // of middle point and dr on right side
            Double dl = closestUtil(P, startIndex, mid);
            Double dr = closestUtil(P, mid, endIndex);

            // Find the smaller of two distances
            Double d = Minimun(dl, dr);

            // Build an array strip[] that contains
            // points close (closer than d)
            // to the line passing through the middle point
            Point[] strip = new Point[endIndex];
            int j = 0;
            for (int i = 0; i < endIndex; i++) {
            if (AbsoluteValue(P[i].x - midPoint.x) < d) {
                    strip[j] = P[i];
                    j++;
            }
            }

            // Find the closest points in strip.
            // Return the minimum of d and closest
            // distance is strip[]
            return Minimun(d, stripClosest(strip, j, d));
    }

    // The main function that finds the smallest distance
    // This method mainly uses closestUtil()
    public static Double closest(Point[] P, int n) {
            Arrays.sort(P, 0, n, new PointXComparator());

            // Use recursive function closestUtil()
            // to find the smallest distance
            return closestUtil(P, 0, n);
    }

}

// A structure to represent a Point in 2D plane
class PointXComparator implements Comparator<Point> {

    // Needed to sort array of points
    // according to X coordinate
    @Override
    public int compare(Point pointA, Point pointB) {
            return Integer.compare(pointA.getX(), pointB.getY());
    }

}

class PointYComparator implements Comparator<Point> {

    // Needed to sort array of points
    // according to Y coordinate
    @Override
    public int compare(Point pointA, Point pointB) {
            return Integer.compare(pointA.getY(), pointB.getY());
    }

}



