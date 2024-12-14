package main;
import java.util.Scanner;

import services.EmployeeManager;
import services.EmployeeSalaryManager;
import services.Titles;
import utils.Utils;

public class Main {
    public static void main(String[] args) {
        EmployeeManager system = new EmployeeManager();
        EmployeeSalaryManager system0 = new EmployeeSalaryManager();
        Titles system1 = new Titles();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            system1.menuTitle();
            System.out.println("\t\t\t\t\t_____________________________________________________________________________\n");
            System.out.println("\n\t\t\t\t\t\t   ---------------  M A I N   M E N U ----------------\n");
            System.out.println("\t\t\t\t\t\t\t\t 1. Employee Management\n");
            System.out.println("\t\t\t\t\t\t\t\t 2. Salary Management\n");
            System.out.println("\t\t\t\t\t\t\t\t 0. Exit\n");
            System.out.print("\n\t\t\t\t\t\t\t\t Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    Utils.clearScreen();
                    system.employeeManagementMenu();
                    break;
                case 2:
                    Utils.clearScreen();
                    system0.salaryManagementMenu();
                    break;
                case 0:
                    System.out.println("\t\t\t\t\t\t\t\t Exiting system...");
                    break;
                default:
                    System.out.println("\n\t\t\t\t\t\t\t    Invalid choice. Please try again.");
                    Utils.pauseInterface();
            }
        } while (choice != 0);

        scanner.close();
    }
}