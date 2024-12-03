package utils;
import java.util.Scanner;

public class Utils {
    public static void pauseInterface() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
}