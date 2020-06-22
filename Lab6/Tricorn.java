import java.awt.geom.Rectangle2D;

public class Tricorn extends FractalGenerator {

    public static final int MAX_ITERATIONS = 2000;
   

    public void getInitialRange(Rectangle2D.Double range) {
    
        range.x = -2;
        range.y = -2;
        range.height = 4;
        range.width = 4;
    }

    public int numIterations(double a, double b) {
    
        double re = a;
        double im = b;
        double re2;
        double im2;
        int i = 0;

        while (i < MAX_ITERATIONS) {
            i += 1;
            re2 = a + re * re - im * im;
            im2 = b - 2 * re * im;
            re = re2;
            im = im2;
            if (re * re + im * im > 4) {
                return i;
            }
        }
        return -1;
    }
    
    public String toString() {
        return("Tricorn");
    }
}
