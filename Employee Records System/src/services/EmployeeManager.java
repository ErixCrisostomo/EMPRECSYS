package services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import models.Employee;
import models.FullTimeEmployee;
import models.PartTimeEmployee;
import models.Person;
import database.EmployeeDatabase;
import utils.Utils;

public class EmployeeManager {
    ArrayList<Employee> employees = EmployeeDatabase.getEmployees(); // Get employees from the centralized database
    EmployeeDisplay system = new EmployeeDisplay();

    private Scanner scanner = new Scanner(System.in);

    private Person createPerson(int id) {
        Person person = new Person();
        person.setId(id); // Auto-generated ID
        person.validateAndSetDetails();
        return person;
    }

    public void addFullTimeEmployee() {
        try {
            int id = EmployeeDatabase.generateUniqueId(); // Generate unique ID
            Person person = createPerson(id);
    
            double monthlySalary;
            while (true) {
                System.out.print("Enter monthly salary: ");
                try {
                    monthlySalary = Double.parseDouble(scanner.nextLine());
                    if (monthlySalary < 0) {
                        System.out.println("Salary cannot be negative. Please try again.");
                        continue;
                    }
                    break; // Exit loop if input is valid
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid numeric value.");
                }
            }
    
            FullTimeEmployee fullTimeEmployee = new FullTimeEmployee(
                person.getName(), person.getId(), person.getPosition(),
                person.getEmail(), person.getContactNumber(),
                person.getHireDate(), monthlySalary
            );
    
            EmployeeDatabase.addEmployee(fullTimeEmployee);
            System.out.printf("Full-Time Employee added successfully with ID: %04d%n", id);
            Utils.pauseInterface();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while adding the full-time employee.");
            Utils.pauseInterface();
        }
    }

    public void addPartTimeEmployee() {
        try {
            int id = EmployeeDatabase.generateUniqueId(); // Generate unique ID
            Person person = createPerson(id);
    
            double hourlyWage = 0, hoursWorked = 0;
    
            // Input validation for hourly wage
            while (true) {
                System.out.print("Enter hourly wage: ");
                try {
                    hourlyWage = Double.parseDouble(scanner.nextLine());
                    if (hourlyWage < 0) {
                        System.out.println("Hourly wage cannot be negative. Please try again.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid numeric value.");
                }
            }
    
            // Input validation for hours worked
            while (true) {
                System.out.print("Enter total hours worked: ");
                try {
                    hoursWorked = Double.parseDouble(scanner.nextLine());
                    if (hoursWorked < 0) {
                        System.out.println("Hours worked cannot be negative. Please try again.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid numeric value.");
                }
            }
    
            PartTimeEmployee partTimeEmployee = new PartTimeEmployee(
                person.getName(), person.getId(), person.getPosition(),
                person.getEmail(), person.getContactNumber(),
                person.getHireDate(), hourlyWage, hoursWorked
            );
    
            EmployeeDatabase.addEmployee(partTimeEmployee);
            System.out.printf("Part-Time Employee added successfully with ID: %04d%n", id);
            Utils.pauseInterface();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while adding the part-time employee.");
            Utils.pauseInterface();
        }
    }

    public void deleteEmployee() {
        ArrayList<Employee> employees = EmployeeDatabase.getEmployees(); // Fetch the latest employee list
        if (employees.isEmpty()) {
            System.out.println("No employees to delete.");
            Utils.pauseInterface();
            return;
        }
    
        System.out.print("Enter the Employee ID to delete (e.g., 0001): ");
        String inputId = scanner.nextLine();
    
        try {
            int id = Integer.parseInt(inputId);
            Employee employeeToDelete = null;
    
            for (Employee employee : employees) {
                if (employee.getId() == id) {
                    employeeToDelete = employee;
                    break;
                }
            }
    
            if (employeeToDelete != null) {
                EmployeeDatabase.removeEmployee(employeeToDelete);
                System.out.printf("Employee with ID %04d has been deleted successfully.%n", id);
            } else {
                System.out.printf("Error: No employee found with ID %04d.%n", id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a numeric ID.");
        }
    
        Utils.pauseInterface();
    }

    public void modifyEmployee() {
        ArrayList<Employee> employees = EmployeeDatabase.getEmployees(); // Fetch the latest employee list
        if (employees.isEmpty()) {
            System.out.println("No employees to modify.");
            Utils.pauseInterface();
            return;
        }
    
        System.out.print("Enter the Employee ID to modify (e.g., 0001): ");
        String inputId = scanner.nextLine();
    
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
                System.out.printf("Modifying Employee with ID %04d:%n", id);
    
                int choice;
                do {
                    System.out.println("\nSelect which information to modify:");
                    System.out.println("1. Name");
                    System.out.println("2. Contact Number");
                    System.out.println("3. Email");
                    System.out.println("4. Hire Date");
                    if (employeeToModify instanceof FullTimeEmployee) {
                        System.out.println("5. Monthly Salary");
                    } else if (employeeToModify instanceof PartTimeEmployee) {
                        System.out.println("5. Hourly Wage");
                        System.out.println("6. Hours Worked");
                    }
                    System.out.println("0. Finish Modifications");
                    System.out.print("Enter your choice: ");
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
    
                    switch (choice) {
                        case 1: // Modify Name
                            System.out.printf("Current Name: %s%n", employeeToModify.getName());
                            System.out.print("Enter new name: ");
                            String newName = scanner.nextLine();
                            employeeToModify.setName(newName);
                            break;
                        case 2: // Modify Contact Number
                            System.out.printf("Current Contact Number: %s%n", employeeToModify.getContactNumber());
                            System.out.print("Enter new contact number: ");
                            String newContact = scanner.nextLine();
                            employeeToModify.setContactNumber(newContact);
                            break;
                        case 3: // Modify Email
                            System.out.printf("Current Email: %s%n", employeeToModify.getEmail());
                            System.out.print("Enter new email: ");
                            String newEmail = scanner.nextLine();
                            if (newEmail.matches(Person.EMAIL_REGEX)) {
                                employeeToModify.setEmail(newEmail);
                            } else {
                                System.out.println("Invalid email format.");
                            }
                            break;
                        case 4: // Modify Hire Date
                            System.out.printf("Current Hire Date: %s%n", employeeToModify.getHireDate());
                            System.out.print("Enter new hire date (MM-DD-YYYY): ");
                            String newHireDate = scanner.nextLine();
                            try {
                                LocalDate.parse(newHireDate, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
                                employeeToModify.setHireDate(newHireDate);
                            } catch (DateTimeParseException e) {
                                System.out.println("Invalid date format.");
                            }
                            break;
                        case 5: // Modify Salary Details
                            if (employeeToModify instanceof FullTimeEmployee) {
                                FullTimeEmployee fullTimeEmployee = (FullTimeEmployee) employeeToModify;
                                System.out.printf("Current Monthly Salary: %s%n", fullTimeEmployee.getMonthlySalary());
                                System.out.print("Enter new monthly salary: ");
                                double newSalary = scanner.nextDouble();
                                fullTimeEmployee.setMonthlySalary(newSalary);
                            } else if (employeeToModify instanceof PartTimeEmployee) {
                                PartTimeEmployee partTimeEmployee = (PartTimeEmployee) employeeToModify;
                                System.out.printf("Current Hourly Salary: %s%n", partTimeEmployee.getHourlyWage());
                                System.out.print("Enter new hourly wage: ");
                                double newWage = scanner.nextDouble();
                                partTimeEmployee.setHourlyWage(newWage);
                            }
                            break;
                        case 6: // Modify Hours Worked (Part-Time Only)
                            if (employeeToModify instanceof PartTimeEmployee) {
                                PartTimeEmployee partTimeEmployee = (PartTimeEmployee) employeeToModify;
                                System.out.printf("Current Hours Worked: %s%n", partTimeEmployee.getHoursWorked());
                                System.out.print("Enter new hours worked: ");
                                double newHours = scanner.nextDouble();
                                partTimeEmployee.setHoursWorked(newHours);
                            }
                            break;
                        case 0: // Exit
                            System.out.println("Finished modifying employee.");
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } while (choice != 0);
            } else {
                System.out.printf("No employee found with ID %04d.%n", id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a valid numeric ID.");
        }
    
        Utils.pauseInterface();
    }

    public void employeeManagementMenu() {
        int choice;
        do {
            System.out.println("Employee Management Menu:");
            System.out.println("1. Add Full-Time Employee");
            System.out.println("2. Add Part-Time Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. Modify Employee");
            System.out.println("5. Display All Employee Details");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addFullTimeEmployee();
                    break;
                case 2:
                    addPartTimeEmployee();
                    break;
                case 3:
                    deleteEmployee();
                    break;
                case 4:
                    modifyEmployee();
                    break;
                case 5:
                    system.displayEmployees();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 0);
    }
}
