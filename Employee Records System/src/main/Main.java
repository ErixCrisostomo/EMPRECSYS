package main;
import java.util.Scanner;

import services.EmployeeManager;
import services.EmployeeSalaryManager;
import utils.Utils;

public class Main {
    public static void main(String[] args) {
        EmployeeManager system = new EmployeeManager();
        EmployeeSalaryManager system0 = new EmployeeSalaryManager();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nEmployee Records System");
            System.out.println("1. Employee Management");
            System.out.println("2. Salary Management");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    system.employeeManagementMenu();
                    break;
                case 2:
                    system0.salaryManagementMenu();
                    break;
                case 0:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    Utils.pauseInterface();
            }
        } while (choice != 0);

        scanner.close();
    }
}