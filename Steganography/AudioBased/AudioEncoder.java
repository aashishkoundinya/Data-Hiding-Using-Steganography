package Steganography.AudioBased;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class AudioEncoder {

    public static void encode(Scanner scanner) {
        System.out.print("Enter path of .WAV file to encode: ");
        String audioFileName = scanner.nextLine();
        File audioFile = new File(audioFileName);

        byte[] audioBytes;

        try {
            audioBytes = Files.readAllBytes(audioFile.toPath());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        System.out.print("Enter your secret message: ");
        String message = scanner.nextLine();

        if (message.length() == 0) {
            throw new IllegalArgumentException("Data is empty");
        }

        try {
            encodeAudio(audioBytes, message);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        System.out.print("Enter the file name you want to save it as with extension: ");
        String newAudioFileName = scanner.nextLine();
        File newAudioFile = new File(newAudioFileName);

        try {
            Files.write(newAudioFile.toPath(), audioBytes);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Message hidden successfully!");
    }

    private static void encodeAudio(byte[] audioBytes, String message) throws Exception {
        int messageIndex = 0;
        int messageLength = message.length();
        String binaryMessage = convertToBinary(messageLength + " " + message);

        for (int i = 44; i < audioBytes.length && messageIndex < binaryMessage.length(); i++) {
            if (i % 2 != 0) continue; // Only modify LSB of every alternate byte to avoid header corruption

            int audioByte = audioBytes[i] & 0xFF;
            if (binaryMessage.charAt(messageIndex) == '0') {
                audioByte = audioByte & 0xFE; // Set LSB to 0
            } else {
                audioByte = audioByte | 0x01; // Set LSB to 1
            }
            audioBytes[i] = (byte) audioByte;
            messageIndex++;
        }

        if (messageIndex < binaryMessage.length()) {
            throw new Exception("Message is too long to fit in the provided audio file.");
        }
    }

    private static String convertToBinary(String data) {
        StringBuilder binary = new StringBuilder();

        for (char ch : data.toCharArray()) {
            binary.append(String.format("%8s", Integer.toBinaryString(ch)).replace(" ", "0"));
        }        return binary.toString();
    }
}
