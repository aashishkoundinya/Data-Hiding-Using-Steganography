package Steganography.VideoBased;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VideoEncoder {

    public static void encode(String inputFilePath, String outputFilePath, String message) {
        try {
            // Read input video file
            Path inputPath = Paths.get(inputFilePath);
            byte[] videoBytes = Files.readAllBytes(inputPath);

            // Embed message into video bytes (simplified example)
            byte[] messageBytes = message.getBytes();
            System.arraycopy(messageBytes, 0, videoBytes, videoBytes.length - messageBytes.length, messageBytes.length);

            // Write output video file
            Path outputPath = Paths.get(outputFilePath);
            Files.write(outputPath, videoBytes);
            System.out.println("Message hidden successfully in video!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// package Steganography.VideoBased;

// import javax.imageio.ImageIO;
// import java.awt.image.BufferedImage;
// import java.io.File;
// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;

// public class VideoEncoder {

//     public static void encode(String framesDirectory, String outputDirectory, String message) {
//         File dir = new File(framesDirectory);
//         File[] files = dir.listFiles((d, name) -> name.endsWith(".png"));
        
//         if (files == null || files.length == 0) {
//             System.out.println("No frames found in the directory: " + framesDirectory);
//             return;
//         }

//         // Convert message to binary
//         String binaryMessage = convertToBinary(message);
//         int messageIndex = 0;

//         try {
//             for (File file : files) {
//                 BufferedImage image = ImageIO.read(file);
//                 if (image == null) continue;

//                 for (int y = 0; y < image.getHeight(); y++) {
//                     for (int x = 0; x < image.getWidth(); x++) {
//                         if (messageIndex < binaryMessage.length()) {
//                             int pixel = image.getRGB(x, y);
//                             int blue = pixel & 0xff;
//                             if (binaryMessage.charAt(messageIndex) == '0' && (blue % 2 != 0)) {
//                                 blue -= 1;
//                             } else if (binaryMessage.charAt(messageIndex) == '1' && (blue % 2 == 0)) {
//                                 if (blue != 0) {
//                                     blue -= 1;
//                                 } else {
//                                     blue += 1;
//                                 }
//                             }

//                             int newPixel = (pixel & 0xffffff00) | blue;
//                             image.setRGB(x, y, newPixel);
//                             messageIndex++;
//                         }
//                     }
//                 }

//                 File outputFile = new File(outputDirectory + "/" + file.getName());
//                 ImageIO.write(image, "png", outputFile);
//             }
//             System.out.println("Message hidden successfully in frames!");
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     private static String convertToBinary(String data) {
//         StringBuilder binary = new StringBuilder();
//         for (char ch : data.toCharArray()) {
//             binary.append(String.format("%8s", Integer.toBinaryString(ch)).replaceAll(" ", "0"));
//         }
//         return binary.toString();
//     }

//     public static void main(String[] args) {
//         String framesDirectory = "frames";
//         String outputDirectory = "output_frames";
//         String message = "This is a secret message";

//         // Create output directory if it does not exist
//         Path outputPath = Paths.get(outputDirectory);
//         if (!Files.exists(outputPath)) {
//             try {
//                 Files.createDirectories(outputPath);
//             } catch (IOException e) {
//                 e.printStackTrace();
//             }
//         }

//         encode(framesDirectory, outputDirectory, message);
//     }
// }