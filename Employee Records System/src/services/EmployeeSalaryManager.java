package services;

import java.util.ArrayList;
import java.util.Scanner;

import models.Employee;
import database.EmployeeDatabase;
import models.FullTimeEmployee;
import models.PartTimeEmployee;
import utils.Utils;

public class EmployeeSalaryManager {
    Titles system1 = new Titles();
    EmployeeSalaryDisplay system = new EmployeeSalaryDisplay(); // For displaying salary details
    private Scanner scanner = new Scanner(System.in);

    public void salaryManagementMenu() {
        int choice;
        do {
            system1.salaryMangement();
            System.out.println("\n\t\t\t\t\t    ---------- S A L A R Y  M A N A G E M E N T  M E N U ----------");
            System.out.println("\n\t\t\t\t\t\t\t  1. Display Employees' Salary Details");
            System.out.println("\n\t\t\t\t\t\t\t  2. Modify Employee Salary Details");
            System.out.println("\n\t\t\t\t\t\t\t  0. Back to Main Menu");
            System.out.print("\n\t\t\t\t\t\t\t  Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    Utils.clearScreen();
                    system.displayEmployeeSalaryDetails();
                    break;
                case 2:
                    Utils.clearScreen();
                    modifyEmployeeSalaryDetails();
                    break;
                case 0:
                    Utils.clearScreen();
                    break;
                default:
                    System.out.println("\n\t\t\t\t\t\t\t  Invalid choice. Please try again.");
                    Utils.pauseInterface();
            }
        } while (choice != 0);
    }

    public void modifyEmployeeSalaryDetails() {
        system1.salaryMangement();
        ArrayList<Employee> employees = EmployeeDatabase.getEmployees(); // Fetch the latest employee list
        if (employees.isEmpty()) {
            System.out.println("\n\t\t\t\t\t\t\t\tNo employees to modify.");
            Utils.pauseInterface();
            return;
        }

        System.out.print("\n\t\t\t\t\t\tEnter Employee ID to modify salary details (e.g., 0001): ");
        String inputId = scanner.nextLine().trim();
        Utils.clearScreen();

        if (inputId.isEmpty()) {
            return;
        }

        try {
            int id = Integer.parseInt(inputId);
            Employee employeeToModify = null;

            for (Employee employee : employees) {
                if (employee.getId() == id) {
                    employeeToModify = employee;
                    break;
                }
            }

            if (employeeToModify != null) {
                system1.salaryMangement();
                System.out.printf("\n\t\t\t\t\t\t    Current Salary Information for Employee ID: %04d:%n", id);
                System.out.println("\n\t\t\t\t\t\t----------------------------------------------------------");
                System.out.println("\n\t\t\t\t\t\t\t\tName: " + employeeToModify.getName());
                System.out.println("\n\t\t\t\t\t\t\t\tPhilHealth Deduction: " + String.format("%.2f", employeeToModify.getDeducPhilHealth()));
                System.out.println("\n\t\t\t\t\t\t\t\tPagibig Deduction: " + String.format("%.2f", employeeToModify.getDeducPagIbig()));
                System.out.println("\n\t\t\t\t\t\t\t\tSSS Deduction: " + String.format("%.2f", employeeToModify.getDeducSSS()));
                System.out.println("\n\t\t\t\t\t\t\t\tTotal Deductions: " + String.format("%.2f", employeeToModify.totalDeduc()));
                System.out.println("\n\t\t\t\t\t\t\t\tFinal Salary: " + String.format("%.2f", employeeToModify.finalSalary()));

                if (employeeToModify instanceof FullTimeEmployee) {
                    System.out.print("\n\t\t\t\t\t\t\t\tEnter new monthly salary: ");
                    double newSalary = scanner.nextDouble();
                    ((FullTimeEmployee) employeeToModify).setMonthlySalary(newSalary);
                } else if (employeeToModify instanceof PartTimeEmployee) {
                    System.out.print("\n\t\t\t\t\t\t\t\tEnter new hourly wage: ");
                    double newWage = scanner.nextDouble();
                    ((PartTimeEmployee) employeeToModify).setHourlyWage(newWage);

                    System.out.print("\t\t\t\t\t\t\t\tEnter new hours worked: ");
                    double newHours = scanner.nextDouble();
                    ((PartTimeEmployee) employeeToModify).setHoursWorked(newHours);
                }

                System.out.println("\n\t\t\t\t\t\t\t      Salary details updated successfully.");
            } 
            else {
                system1.salaryMangement();
                System.out.printf("\n\t\t\t\t\t\t\t   Error: No employee found with ID %04d.%n", id);
            }
        } 
        catch (NumberFormatException e) {
            System.out.println("\n\t\t\t\t\t\t\t\tInvalid ID format. Please enter a numeric ID.");
        }
        Utils.pauseInterface();
    }
}