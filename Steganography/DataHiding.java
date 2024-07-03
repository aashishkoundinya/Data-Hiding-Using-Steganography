package Steganography;

import java.util.Scanner;

public class DataHiding {

    public static void main(String[] args) {
        
        System.out.println(":: Hi. Press 1 to encode an image with your own secret data. Press 2 to decode an image message from your computer, that you have received. Press 3 to exit the program.::");
        System.out.println("1. Encode\n2. Decode\n3. Exit");
        System.out.print("Enter your choice : ");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            ImageEncoder.encode(scanner);
        } else if (choice == 2) {
            String message = ImageDecoder.decode(scanner);
            System.out.println("The decoded secret message in the input is: " + message);
        } else if (choice == 3) {
            System.out.println("Exiting Program");
            System.exit(0);
        } else {
            System.out.println("Invalid Choice");
        }

        scanner.close();
    }
}