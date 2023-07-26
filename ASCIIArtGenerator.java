//ASCII GENERADOR DE ARTE

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ASCIIArtGenerator {
    public static void main(String[] args) {
        String imagePath = "C:\\Users\\ST\\Pictures\\Mona.jpg"; // Cambiar por la ruta de la imagen que deseas convertir
        BufferedImage image = loadImage(imagePath);

        if (image != null) {
            int targetWidth = 100; // Ancho objetivo para el arte ASCII
            double aspectRatio = (double) image.getHeight() / image.getWidth();
            int targetHeight = (int) (targetWidth * aspectRatio);

            BufferedImage resizedImage = resizeImage(image, targetWidth, targetHeight);
            String asciiArt = convertToASCII(resizedImage);
            System.out.println(asciiArt);
        } else {
            System.out.println("Error al cargar la imagen.");
        }
    }

    private static BufferedImage loadImage(String imagePath) {
        try {
            File file = new File(imagePath);
            return ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static BufferedImage resizeImage(BufferedImage image, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return resizedImage;
    }

    private static String convertToASCII(BufferedImage image) {
        StringBuilder asciiArt = new StringBuilder();

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color pixelColor = new Color(image.getRGB(x, y));
                int grayValue = (pixelColor.getRed() + pixelColor.getGreen() + pixelColor.getBlue()) / 3;
                char asciiChar = getAsciiChar(grayValue);
                asciiArt.append(asciiChar);
            }
            asciiArt.append("\n");
        }

        return asciiArt.toString();
    }

private static char getAsciiChar(int grayValue) {
    char[] asciiChars = {' ', '.', ':', '-', '=', '+', '*', '#', '%', '@'};
    int range = 256 / asciiChars.length;
    int index = grayValue / range;
    if (index >= asciiChars.length) {
        index = asciiChars.length - 1;
    }
    return asciiChars[index];
}
}

