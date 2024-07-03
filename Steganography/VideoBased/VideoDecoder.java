package Steganography.VideoBased;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VideoDecoder {

    public static String decode(String inputFilePath) {
        try {
            // Read input video file
            Path inputPath = Paths.get(inputFilePath);
            byte[] videoBytes = Files.readAllBytes(inputPath);

            // Extract message from video bytes (simplified example)
            byte[] messageBytes = new byte[256]; // assuming the message is 256 bytes long
            System.arraycopy(videoBytes, videoBytes.length - messageBytes.length, messageBytes, 0, messageBytes.length);

            return new String(messageBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}