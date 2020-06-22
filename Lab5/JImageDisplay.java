import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JImageDisplay extends JComponent {
    private BufferedImage bufferedImage;

    public JImageDisplay(int w, int h) 
    {
        this.bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Dimension d = new Dimension(w,h);
        setPreferredSize(d);
    }

    public void paintComponent(Graphics g)
    
    {
        super.paintComponent(g);
        g.drawImage(bufferedImage, 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null);
    }

    public void clearImage()

    {
        for(int y = 0; y < bufferedImage.getHeight(); y++){
            for(int x = 0; x < bufferedImage.getWidth(); x++){
                bufferedImage.setRGB(x,y,0);
            }
        }
    }

    public void drawPixel(int x, int y, int rgbColor) 
    {
        bufferedImage.setRGB(x, y, rgbColor);
    }

    public BufferedImage getImage() {
        return bufferedImage;
    }
}

