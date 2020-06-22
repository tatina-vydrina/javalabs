import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FractalExplorer {

    private int wh; 
    private JImageDisplay display; 
    private FractalGenerator fractalGenerator;
    private JComboBox comboBox; 
    private Rectangle2D.Double range; 

    private FractalExplorer (int wh) {
        this.wh = wh;
        this.fractalGenerator = new Mandelbrot();
        this.range = new Rectangle2D.Double(0,0,0,0);
    }


    public void createAndShowGUI() {
        JFrame frame = new JFrame("Fractal Explorer");
        display = new JImageDisplay(wh, wh);
        JButton resetBtn = new JButton("Reset");
        JButton saveBtn = new JButton("Save");
        resetBtn.setActionCommand("reset");
        saveBtn.setActionCommand("save");
        JLabel label = new JLabel("Selected fractal: ");
        comboBox = new JComboBox();
        /* Добавление фракталов в меню */
        comboBox.addItem(new Mandelbrot());
        comboBox.addItem(new Tricorn());
        comboBox.addItem(new BurningShip());
        JPanel panel = new JPanel();
        JPanel panelBtn = new JPanel();
        panel.add(label);
        panel.add(comboBox);
        panelBtn.add(resetBtn);
        panelBtn.add(saveBtn);
        ActionHandler aHandler = new ActionHandler();
        MouseHandler mHandler = new MouseHandler();
        resetBtn.addActionListener(aHandler);
        saveBtn.addActionListener(aHandler);
        display.addMouseListener(mHandler);

        frame.setLayout(new java.awt.BorderLayout());
        frame.add(panel, BorderLayout.NORTH);
        frame.add(display, java.awt.BorderLayout.CENTER);
        frame.add(panelBtn, java.awt.BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);

    }

    private void drawFractal() {
        for (int x = 0; x < wh; x++) {
            for (int y = 0; y < wh; y++) {
                int counter = fractalGenerator.numIterations(FractalGenerator.getCoord(range.x, range.x + range.width, wh, x),fractalGenerator.getCoord(range.y, range.y + range.width, wh, y));
                if (counter == -1) {
                    display.drawPixel(x, y, 0);
                }
                else {
                    float hue = 0.7f + (float) counter / 200f;
                    display.drawPixel(x, y, Color.HSBtoRGB(hue, 1f, 1f));
                }
            }
        }
        display.repaint();
    }

    public class ActionHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "reset") {
                fractalGenerator = (FractalGenerator) comboBox.getSelectedItem();
                fractalGenerator.getInitialRange(range);
                drawFractal();
            }
            else if (e.getActionCommand() == "save") {
                JFileChooser chooser = new javax.swing.JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("PNG Images", "png");
                chooser.setFileFilter(filter);
                chooser.setAcceptAllFileFilterUsed(false);
                int res = chooser.showSaveDialog(display);

                if (res == JFileChooser.APPROVE_OPTION) {
                    try {
                        javax.imageio.ImageIO.write(display.getImage(),"png", chooser.getSelectedFile());
                    } catch (Exception e1) {
                        javax.swing.JOptionPane.showMessageDialog(display, e1.getMessage(), "Save error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    return;
                }
            }
        }
    }
    public class MouseHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, wh, e.getX());
            double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, wh, e.getY());
            fractalGenerator.recenterAndZoomRange(range, xCoord, yCoord, .5);
            drawFractal();
        }
    }
    public static void main(String[] args) {
        FractalExplorer fractalExplorer = new FractalExplorer(500);
        fractalExplorer.createAndShowGUI();
        fractalExplorer.drawFractal();
    }
}