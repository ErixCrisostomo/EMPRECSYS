package utils;
import java.util.Scanner;

public class Utils {
    public static void pauseInterface() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\t\t\t\t\t\t\t\tPress Enter to continue...");
        scanner.nextLine();
        clearScreen();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}