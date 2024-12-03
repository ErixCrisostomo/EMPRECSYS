package services;

import java.util.ArrayList;
import java.util.Scanner;

import models.Employee;
import database.EmployeeDatabase;
import models.FullTimeEmployee;
import models.PartTimeEmployee;
import utils.Utils;

public class EmployeeSalaryManager {
    EmployeeSalaryDisplay system = new EmployeeSalaryDisplay(); // For displaying salary details
    private Scanner scanner = new Scanner(System.in);

    public void salaryManagementMenu() {
        int choice;
        do {
            System.out.println("Salary Management Menu:");
            System.out.println("1. Display Employees' Salary Details");
            System.out.println("2. Modify Employee Salary Details");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    system.displayEmployeeSalaryDetails();
                    break;
                case 2:
                    modifyEmployeeSalaryDetails();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 0);
    }

    public void modifyEmployeeSalaryDetails() {
        ArrayList<Employee> employees = EmployeeDatabase.getEmployees(); // Fetch the latest employee list
        if (employees.isEmpty()) {
            System.out.println("No employees to modify.");
            Utils.pauseInterface();
            return;
        }

        System.out.print("Enter Employee ID to modify salary details (e.g., 0001): ");
        String inputId = scanner.nextLine().trim();

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
                System.out.printf("\nCurrent Salary Information for Employee ID: %04d:%n", id);
                System.out.println("Name: " + employeeToModify.getName());
                System.out.println("PhilHealth Deduction: " + String.format("%.2f", employeeToModify.getDeducPhilHealth()));
                System.out.println("Pagibig Deduction: " + String.format("%.2f", employeeToModify.getDeducPagIbig()));
                System.out.println("SSS Deduction: " + String.format("%.2f", employeeToModify.getDeducSSS()));
                System.out.println("Total Deductions: " + String.format("%.2f", employeeToModify.totalDeduc()));
                System.out.println("Final Salary: " + String.format("%.2f", employeeToModify.finalSalary()));

                if (employeeToModify instanceof FullTimeEmployee) {
                    System.out.print("\nEnter new monthly salary: ");
                    double newSalary = scanner.nextDouble();
                    ((FullTimeEmployee) employeeToModify).setMonthlySalary(newSalary);
                } else if (employeeToModify instanceof PartTimeEmployee) {
                    System.out.print("\nEnter new hourly wage: ");
                    double newWage = scanner.nextDouble();
                    ((PartTimeEmployee) employeeToModify).setHourlyWage(newWage);

                    System.out.print("Enter new hours worked: ");
                    double newHours = scanner.nextDouble();
                    ((PartTimeEmployee) employeeToModify).setHoursWorked(newHours);
                }

                System.out.println("Salary details updated successfully.");
            } 
            else {
                System.out.printf("Error: No employee found with ID %04d.%n", id);
            }
        } 
        catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a numeric ID.");
        }
        Utils.pauseInterface();
    }
}