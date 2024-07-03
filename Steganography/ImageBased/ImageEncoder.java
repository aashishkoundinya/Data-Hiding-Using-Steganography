package Steganography.ImageBased;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ImageEncoder {

    public static void encode(Scanner scanner) {
        System.out.print("Enter image name with extension format: ");
        String imgName = scanner.nextLine();
        File imgFile = new File(imgName);

        BufferedImage image = null;
        try {
            image = ImageIO.read(imgFile);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        System.out.print("Enter your secret message: ");
        String message = scanner.nextLine();
        if (message.length() == 0) {
            throw new IllegalArgumentException("Data is empty");
        }

        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < image.getHeight(); y++) {      // reads every pixel row wise
            for (int x = 0; x < image.getWidth(); x++) {   // reads every pixel column wise
                newImage.setRGB(x, y, image.getRGB(x, y));   // sets new image RGB values same as the old one
            }
        }

        try {
            encodeImage(newImage, message);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        System.out.print("Enter the image name you want to save it as with extension format: ");
        String newImgName = scanner.nextLine();
        File newImgFile = new File(newImgName);

        try {
            ImageIO.write(newImage, "png", newImgFile);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Message hidden successfully!");
    }

    private static void encodeImage(BufferedImage image, String message) throws Exception {
        int w = image.getWidth();
        int h = image.getHeight();
        int messageIndex = 0;
        int messageLength = message.length();
        String binaryMessage = convertToBinary(messageLength + " " + message);

        for (int y = 0; y < h; y++) {   // starts at the first row,  works until < height
            for (int x = 0; x < w; x++) {  // starts at the first column,  works until < width
                if (messageIndex < binaryMessage.length()) {
                    int pixel = image.getRGB(x, y);     // gets RGB value of pixel (x,y)
                    int red = (pixel >> 16) & 0xff;     // 32-bit value, shifting red value 16 bits using right shift
                    int green = (pixel >> 8) & 0xff;    // shifting green value 8 bits using right shift
                    int blue = pixel & 0xff;    // keeping the blue values as it is

                    if (binaryMessage.charAt(messageIndex) == '0' && (blue % 2 != 0)) {
                        blue -= 1;  // checks if current bit in the binary message is 0 and if blue is odd
                    } else if (binaryMessage.charAt(messageIndex) == '1' && (blue % 2 == 0)) {
                        if (blue != 0) {   // checks if current bit in binary message is 1 and if blue is even
                            blue -= 1;     // if blue is not equal to 0 then decrement -1 to change LSB
                        } else {
                            blue += 1;     //  if blue is equal to 0 then increment 1 to change LSB
                        }
                    }

                    int newPixel = (red << 16) | (green << 8) | blue;  // combines them back to single integer value 
                    image.setRGB(x, y, newPixel); // set pixel at (x,y) to new rgb value
                    messageIndex++;  // move to next index of the binary message
                } else {
                    return;
                }
            }
        }

        if (messageIndex < binaryMessage.length()) {
            throw new Exception("Message is too long to fit in the provided image.");  // image capacity is less for the given message
        }
    }

    private static String convertToBinary(String data) {
        StringBuilder binary = new StringBuilder();   // convert to binary
        for (char ch : data.toCharArray()) {
            binary.append(String.format("%8s", Integer.toBinaryString(ch)).replaceAll(" ", "0"));
        }
        return binary.toString();
    }
}