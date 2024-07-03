package Steganography.AudioBased;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class AudioDecoder {

    public static String decode(Scanner scanner) {
        System.out.print("Enter the WAV file name you want to decode, with extension: ");
        String audioFileName = scanner.nextLine();
        File audioFile = new File(audioFileName);

        byte[] audioBytes;
        try {
            audioBytes = Files.readAllBytes(audioFile.toPath());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }

        return decodeAudio(audioBytes);
    }

    private static String decodeAudio(byte[] audioBytes) {
        StringBuilder binaryMessage = new StringBuilder();
        StringBuilder decodedMessage = new StringBuilder();

        for (int i = 44; i < audioBytes.length; i++) {
            if (i % 2 != 0) continue; // Only read LSB of every alternate byte to avoid header corruption

            int audioByte = audioBytes[i] & 0xFF;
            binaryMessage.append(audioByte & 0x01);

            if (binaryMessage.length() % 8 == 0) {
                char decodedChar = (char) Integer.parseInt(binaryMessage.substring(binaryMessage.length() - 8), 2);
                decodedMessage.append(decodedChar);
                if (decodedMessage.length() > 1 && decodedMessage.charAt(decodedMessage.length() - 1) == ' ' && decodedMessage.charAt(decodedMessage.length() - 2) == ' ') {
                    return decodedMessage.substring(0, decodedMessage.length() - 2);
                }
            }
        }

        return decodedMessage.toString();
    }
}
