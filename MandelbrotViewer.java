import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MandelbrotViewer extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final double DEFAULT_XMIN = -2.5;
    private static final double DEFAULT_XMAX = 1.5;
    private static final double DEFAULT_YMIN = -1.5;
    private static final double DEFAULT_YMAX = 1.5;
    private static final int MAX_ITERATIONS = 1000;

    private double xmin, xmax, ymin, ymax;

    public MandelbrotViewer() {
        setTitle("Mandelbrot Viewer");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        xmin = DEFAULT_XMIN;
        xmax = DEFAULT_XMAX;
        ymin = DEFAULT_YMIN;
        ymax = DEFAULT_YMAX;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                double x = map(e.getX(), 0, WIDTH, xmin, xmax);
                double y = map(e.getY(), 0, HEIGHT, ymin, ymax);

                double width = xmax - xmin;
                double height = ymax - ymin;

                double newWidth = width / 4;
                double newHeight = height / 4;

                xmin = x - newWidth / 2;
                xmax = x + newWidth / 2;
                ymin = y - newHeight / 2;
                ymax = y + newHeight / 2;

                repaint();
            }
        });
    }

    private int computeMandelbrotIterations(double x, double y) {
        double real = x;
        double imag = y;
        int iterations = 0;

        while (iterations < MAX_ITERATIONS && real * real + imag * imag < 4) {
            double tempReal = real * real - imag * imag + x;
            imag = 2 * real * imag + y;
            real = tempReal;
            iterations++;
        }

        return iterations;
    }

    @Override
    public void paint(Graphics g) {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                double a = map(x, 0, WIDTH, xmin, xmax);
                double b = map(y, 0, HEIGHT, ymin, ymax);
                int n = computeMandelbrotIterations(a, b);
                int color = (255 * n) / MAX_ITERATIONS;
                g.setColor(new Color(color, color, color));
                g.fillRect(x, y, 1, 1);
            }
        }
    }

    private double map(double value, double start1, double stop1, double start2, double stop2) {
        return start2 + (stop2 - start2) * ((value - start1) / (stop1 - start1));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MandelbrotViewer viewer = new MandelbrotViewer();
            viewer.setVisible(true);
        });
    }
}