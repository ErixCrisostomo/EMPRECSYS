package services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import models.Employee;
import models.FullTimeEmployee;
import models.PartTimeEmployee;
import models.Person;
import database.EmployeeDatabase;
import utils.Utils;

public class EmployeeManager {
    ArrayList<Employee> employees = EmployeeDatabase.getEmployees();
    EmployeeDisplay system = new EmployeeDisplay();
    Titles system1 = new Titles();

    private Scanner scanner = new Scanner(System.in);

    private Person createPerson(int id) {
        Person person = new Person();
        person.setId(id); 
        person.validateAndSetDetails();
        return person;
    }

    public void addFullTimeEmployee() {
        system1.employeePortal();
        try {
            int id = EmployeeDatabase.generateUniqueId();
            Person person = createPerson(id);
    
            double monthlySalary;
            while (true) {
                System.out.print("\n\t\t\t\t\t\t\t   Enter monthly salary: ");
                try {
                    monthlySalary = Double.parseDouble(scanner.nextLine());
                    if (monthlySalary < 0) {
                        System.out.println("\t\t\t\t\t\tSalary cannot be negative. Please try again.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("\t\t\t\t\t\tInvalid input. Please enter a valid numeric value.");
                }
            }
    
            FullTimeEmployee fullTimeEmployee = new FullTimeEmployee(
                person.getName(), person.getId(), person.getPosition(),
                person.getEmail(), person.getContactNumber(),
                person.getHireDate(), monthlySalary
            );
    
            EmployeeDatabase.addEmployee(fullTimeEmployee);
            System.out.printf("\n\t\t\t\t\t\t\tFull-Time Employee added successfully with ID: %04d%n", id);
            Utils.pauseInterface();
        } catch (Exception e) {
            System.out.println("\t\t\t\t\t\tAn unexpected error occurred while adding the full-time employee.");
            Utils.pauseInterface();
        }
    }

    public void addPartTimeEmployee() {
        system1.employeePortal();
        try {
            int id = EmployeeDatabase.generateUniqueId();
            Person person = createPerson(id);
    
            double hourlyWage = 0, hoursWorked = 0;
    
            while (true) {
                System.out.print("\n\t\t\t\t\t\t\t   Enter hourly wage: ");
                try {
                    hourlyWage = Double.parseDouble(scanner.nextLine());
                    if (hourlyWage < 0) {
                        System.out.println("\n\t\t\t\t\t\tHourly wage cannot be negative. Please try again.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("\n\t\t\t\t\t\t\tInvalid input. Please enter a valid numeric value.");
                }
            }
    
            while (true) {
                System.out.print("\n\t\t\t\t\t\t\t   Enter total hours worked: ");
                try {
                    hoursWorked = Double.parseDouble(scanner.nextLine());
                    if (hoursWorked < 0) {
                        System.out.println("\n\t\t\t\t\t\tHours worked cannot be negative. Please try again.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("\n\t\t\t\t\t\tInvalid input. Please enter a valid numeric value.");
                }
            }
    
            PartTimeEmployee partTimeEmployee = new PartTimeEmployee(
                person.getName(), person.getId(), person.getPosition(),
                person.getEmail(), person.getContactNumber(),
                person.getHireDate(), hourlyWage, hoursWorked
            );
    
            EmployeeDatabase.addEmployee(partTimeEmployee);
            System.out.printf("\n\t\t\t\t\t\t\tPart-Time Employee added successfully with ID: %04d%n", id);
            Utils.pauseInterface();
        } catch (Exception e) {
            System.out.println("\n\t\t\t\t\t\tAn unexpected error occurred while adding the part-time employee.");
            Utils.pauseInterface();
        }
    }

    public void deleteEmployee() {
        system1.employeePortal();
        ArrayList<Employee> employees = EmployeeDatabase.getEmployees();
        if (employees.isEmpty()) {
            System.out.println("\n\t\t\t\t\t\t\t\tNo employees to delete.");
            Utils.pauseInterface();
            return;
        }
    
        System.out.print("\n\t\t\t\t\t\t\tEnter the Employee ID to delete (e.g., 0001): ");
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
                System.out.printf("\n\t\t\t\t\t\t\tEmployee with ID %04d has been deleted successfully.%n", id);
            } else {
                System.out.printf("\n\t\t\t\t\t\t\t\tError: No employee found with ID %04d.%n", id);
            }
        } catch (NumberFormatException e) {
            System.out.println("\n\t\t\t\t\t\t\tInvalid ID format. Please enter a numeric ID.");
        }
    
        Utils.pauseInterface();
    }

    public void modifyEmployee() {
        system1.employeePortal();
        ArrayList<Employee> employees = EmployeeDatabase.getEmployees();
        if (employees.isEmpty()) {
            System.out.println("\n\t\t\t\t\t\t\t\tNo employees to modify.");
            Utils.pauseInterface();
            return;
        }
    
        System.out.print("\n\t\t\t\t\t\t\tEnter the Employee ID to modify (e.g., 0001): ");
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
                system1.employeePortal();
                System.out.printf("\n\t\t\t\t\t\t\t\t\tModifying Employee with ID %04d:%n", id);
    
                int choice;
                do {
                    system1.employeePortal();
                    System.out.println("\n\n\t\t\t\t\t\t\t\tSelect which information to modify:");
                    System.out.println("\n\t\t\t\t\t\t\t\t1. Name");
                    System.out.println("\n\t\t\t\t\t\t\t\t2. Contact Number");
                    System.out.println("\n\t\t\t\t\t\t\t\t3. Email");
                    System.out.println("\n\t\t\t\t\t\t\t\t4. Hire Date");
                    if (employeeToModify instanceof FullTimeEmployee) {
                        System.out.println("\n\t\t\t\t\t\t\t\t5. Monthly Salary");
                    } else if (employeeToModify instanceof PartTimeEmployee) {
                        System.out.println("\n\t\t\t\t\t\t\t\t5. Hourly Wage");
                        System.out.println("\n\t\t\t\t\t\t\t\t6. Hours Worked");
                    }
                    System.out.println("\n\t\t\t\t\t\t\t\t0. Finish Modifications");
                    System.out.print("\n\t\t\t\t\t\t\t\tEnter your choice: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
    
                    switch (choice) {
                        case 1:
                            System.out.printf("\n\t\t\t\t\t\t\t\t\tCurrent Name: %s%n", employeeToModify.getName());
                            System.out.print("\n\t\t\t\t\t\t\t\tEnter new name: ");
                            String newName = scanner.nextLine();
                            employeeToModify.setName(newName);
                            break;
                        case 2:
                            System.out.printf("\n\t\t\t\t\t\t\t\tCurrent Contact Number: %s%n", employeeToModify.getContactNumber());
                            System.out.print("\n\t\t\t\t\t\t\t\tEnter new contact number: ");
                            String newContact = scanner.nextLine();
                            employeeToModify.setContactNumber(newContact);
                            break;
                        case 3:
                            System.out.printf("\n\t\t\t\t\t\t\t\tCurrent Email: %s%n", employeeToModify.getEmail());
                            System.out.print("\n\t\t\t\t\t\t\t\tEnter new email: ");
                            String newEmail = scanner.nextLine();
                            if (newEmail.matches(Person.EMAIL_REGEX)) {
                                employeeToModify.setEmail(newEmail);
                            } else {
                                System.out.println("\n\t\t\t\t\t\t\t\tInvalid email or format.");
                            }
                            break;
                        case 4:
                            System.out.printf("\n\t\t\t\t\t\t\t\tCurrent Hire Date: %s%n", employeeToModify.getHireDate());
                            System.out.print("\n\t\t\t\t\t\t\t\tEnter new hire date (MM-DD-YYYY): ");
                            String newHireDate = scanner.nextLine();
                            try {
                                LocalDate.parse(newHireDate, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
                                employeeToModify.setHireDate(newHireDate);
                            } catch (DateTimeParseException e) {
                                System.out.println("\n\t\t\t\t\t\t\t\tInvalid date or format.");
                            }
                            break;
                        case 5: 
                            if (employeeToModify instanceof FullTimeEmployee) {
                                FullTimeEmployee fullTimeEmployee = (FullTimeEmployee) employeeToModify;
                                System.out.printf("\n\t\t\t\t\t\t\t\tCurrent Monthly Salary: %s%n", fullTimeEmployee.getMonthlySalary());
                                System.out.print("\n\t\t\t\t\t\t\t\tEnter new monthly salary: ");
                                double newSalary = scanner.nextDouble();
                                fullTimeEmployee.setMonthlySalary(newSalary);
                            } else if (employeeToModify instanceof PartTimeEmployee) {
                                PartTimeEmployee partTimeEmployee = (PartTimeEmployee) employeeToModify;
                                System.out.printf("\n\t\t\t\t\t\t\t\tCurrent Hourly Salary: %s%n", partTimeEmployee.getHourlyWage());
                                System.out.print("\n\t\t\t\t\t\t\t\tEnter new hourly wage: ");
                                double newWage = scanner.nextDouble();
                                partTimeEmployee.setHourlyWage(newWage);
                            }
                            break;
                        case 6:
                            if (employeeToModify instanceof PartTimeEmployee) {
                                PartTimeEmployee partTimeEmployee = (PartTimeEmployee) employeeToModify;
                                System.out.printf("\n\t\t\t\t\t\t\t\tCurrent Hours Worked: %s%n", partTimeEmployee.getHoursWorked());
                                System.out.print("\n\t\t\t\t\t\t\t\tEnter new hours worked: ");
                                double newHours = scanner.nextDouble();
                                partTimeEmployee.setHoursWorked(newHours);
                            }
                            break;
                        case 0:
                            System.out.println("\n\t\t\t\t\t\t\t\tFinished modifying employee.");
                            break;
                        default:
                            System.out.println("\n\t\t\t\t\t\t\t\tInvalid choice. Please try again.");
                    }
                } while (choice != 0);
            } else {
                System.out.printf("\n\t\t\t\t\t\t\t\tNo employee found with ID %04d.%n", id);
            }
        } catch (NumberFormatException e) {
            System.out.println("\n\t\t\t\t\t\t\tInvalid ID format. Please enter a valid numeric ID.");
        }
    
        Utils.pauseInterface();
    }

    public void employeeManagementMenu() {
        int choice;
        do {
            system1.employeePortal();
            System.out.println("\n\n\t\t\t\t\t    ------------ E M P L O Y E E  M A N A G E M E N T  M E N U -----------");
            System.out.println("\n\t\t\t\t\t\t\t\t 1. Add Full-Time Employee");
            System.out.println("\n\t\t\t\t\t\t\t\t 2. Add Part-Time Employee");
            System.out.println("\n\t\t\t\t\t\t\t\t 3. Delete Employee");
            System.out.println("\n\t\t\t\t\t\t\t\t 4. Modify Employee");
            System.out.println("\n\t\t\t\t\t\t\t\t 5. Display All Employee Details");
            System.out.println("\n\t\t\t\t\t\t\t\t 0. Back to Main Menu");
            System.out.print("\n\t\t\t\t\t\t\t\t Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Utils.clearScreen();
                    addFullTimeEmployee();
                    break;
                case 2:
                    Utils.clearScreen();
                    addPartTimeEmployee();
                    break;
                case 3:
                    Utils.clearScreen();
                    deleteEmployee();
                    break;
                case 4:
                    Utils.clearScreen();
                    modifyEmployee();
                    break;
                case 5:
                    Utils.clearScreen();
                    system.displayEmployees();
                    break;
                case 0:
                    Utils.clearScreen();
                    break;
                default:
                    System.out.println("\n\t\t\t\t\t\t\t     Invalid choice. Please try again.");
                    Utils.pauseInterface();
            }
        } while (choice != 0);
    }
}
