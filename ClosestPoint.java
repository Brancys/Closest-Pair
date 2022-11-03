
package closestpair;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author brancysb
 */
public class ClosestPoint {

    // Driver code
    public static void main(String[] args) {
        
        Scanner s = new Scanner(System.in); int x,y; boolean be;
        System.out.println("Ingrese 10 puntos para hallar el par mas cercano");
        Point[] points = new Point[10];
        
        for (int i = 0; i < 10; i++) {
            System.out.println("Ingrese coordenada x del punto "+(i+1));
            x = s.nextInt();
            System.out.println("Ingrese coordenada y del punto "+(i+1));
            y = s.nextInt();
            be=search(x,y,points,i);
            if (!be) {
               points[i]=new Point(x,y); 
            }else{
                System.out.println("ESTE PUNTO YA PERTENECE AL ARREGLO,INGRESA UNO DISTINTO \n");
                i--;
            }            
        }
               
        DecimalFormat df = new DecimalFormat("#.######");
        System.out.println("The smallest distance is " + df.format(Point.closest(points, points.length)));
    }
    
    public static boolean search(int xa, int ya, Point[] array, int index){
        for (int i = 0; i < index; i++) {
            if (array[i].x==xa & array[i].y==ya) {               
               return true;                
            }
        }
        return false;
    }
}
/*
        
        Point[] P = new Point[]{
        new Point(2, 3),
        new Point(12, 30),
        new Point(40, 50),
        new Point(5, 1),
        new Point(12, 10),
        new Point(3, 3)

        }; */