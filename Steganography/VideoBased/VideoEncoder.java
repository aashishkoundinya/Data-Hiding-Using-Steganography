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
