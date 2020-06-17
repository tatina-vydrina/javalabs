import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator{

    public void getInitialRange(Rectangle2D.Double range) 
    {
        range.x = -2;
        range.y = -1.5;
        range.height = 3;
        range.width = 3;
    }

    public static final int MAX_ITERATIONS = 2000; 

    public int numIterations(double a, double b) 
    {
        double re = a;
        double im = b;
        int i = 0;

        while (i < MAX_ITERATIONS) {
            i += 1;
            double re2 = a + re * re - im * im;
            double im2 = b + 2 * re * im;
            re = re2;
            im = im2;
            if (re * re + im * im > 4) {
                return i;
            }
        }
        return -1;
    }
}