package Steganography.ImageBased;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ImageDecoder {

    public static String decode(Scanner scanner) {
        System.out.print("Enter the image name you want to decode, with extension format: ");
        String imgName = scanner.nextLine();
        File imgFile = new File(imgName);

        BufferedImage image = null;
        try {
            image = ImageIO.read(imgFile);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }

        return decodeImage(image);
    }

    private static String decodeImage(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        StringBuilder binaryMessage = new StringBuilder();
        StringBuilder decodedMessage = new StringBuilder();

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int pixel = image.getRGB(x, y);
                int blue = pixel & 0xff;

                if (blue % 2 == 0) {
                    binaryMessage.append('0');
                } else {
                    binaryMessage.append('1');
                }

                if (binaryMessage.length() % 8 == 0) {
                    char decodedChar = (char) Integer.parseInt(binaryMessage.substring(binaryMessage.length() - 8), 2);
                    decodedMessage.append(decodedChar);
                    if (decodedMessage.length() > 1 && decodedMessage.charAt(decodedMessage.length() - 1) == ' ' && decodedMessage.charAt(decodedMessage.length() - 2) == ' ') {
                        return decodedMessage.substring(0, decodedMessage.length() - 2);
                    }
                }
            }
        }

        return decodedMessage.toString();
    }
}