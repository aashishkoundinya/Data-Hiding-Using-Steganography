package Steganography;

import Steganography.AudioBased.*;
import Steganography.ImageBased.*;
import Steganography.VideoBased.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(":: Welcome to the Steganography Program ::");
            System.out.println("1. Image\n2. Audio\n3. Video\n4. Exit");
            System.out.print("Select the type of file you want to work with: ");

            int fileTypeChoice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            if (fileTypeChoice == 4) {
                System.out.println("Exiting Program");
                break;
            }

            System.out.println(":: Choose an operation ::");
            System.out.println("1. Encode\n2. Decode\n3. Back");
            System.out.print("Enter your choice: ");

            int operationChoice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            if (operationChoice == 3) {
                continue;
            }

            switch (fileTypeChoice) {
                case 1:
                    if (operationChoice == 1) {
                        ImageEncoder.encode(scanner);
                    } else if (operationChoice == 2) {
                        String message = ImageDecoder.decode(scanner);
                        System.out.println("The decoded secret message is: " + message);
                    }
                    break;
                case 2:
                    if (operationChoice == 1) {
                        AudioEncoder.encode(scanner);
                    } else if (operationChoice == 2) {
                        String message = AudioDecoder.decode(scanner);
                        System.out.println("The decoded secret message is: " + message);
                    }
                    break;
                case 3:
                    if (operationChoice == 1) {
                        System.out.print("Enter input video file path: ");
                        String inputVideoPath = scanner.nextLine();
                        System.out.print("Enter output video file path: ");
                        String outputVideoPath = scanner.nextLine();
                        System.out.print("Enter your secret message: ");
                        String message = scanner.nextLine();
                        VideoEncoder.encode(inputVideoPath, outputVideoPath, message);
                    } else if (operationChoice == 2) {
                        System.out.print("Enter input video file path: ");
                        String inputVideoPath = scanner.nextLine();
                        String message = VideoDecoder.decode(inputVideoPath);
                        System.out.println("The decoded secret message is: " + message);
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}