package services;

import java.util.ArrayList;
import java.util.Comparator;

import database.EmployeeDatabase;
import models.Employee;
import models.FullTimeEmployee;
import models.PartTimeEmployee;
import utils.Utils;

public class EmployeeDisplay {
    public void displayEmployees() {
        ArrayList<Employee> employees = EmployeeDatabase.getEmployees(); // Fetch the latest employee list
        if (employees.isEmpty()) {
            System.out.println("No employees to display.");
            Utils.pauseInterface();
            return;
        }

        // Separate employees by type
        ArrayList<FullTimeEmployee> fullTimeEmployees = new ArrayList<>();
        ArrayList<PartTimeEmployee> partTimeEmployees = new ArrayList<>();

        // Categorize employees into full-time and part-time
        for (Employee employee : employees) {
            if (employee instanceof FullTimeEmployee) {
                fullTimeEmployees.add((FullTimeEmployee) employee);
            } else if (employee instanceof PartTimeEmployee) {
                partTimeEmployees.add((PartTimeEmployee) employee);
            }
        }

        // Sort by ID
        fullTimeEmployees.sort(Comparator.comparingInt(Employee::getId));
        partTimeEmployees.sort(Comparator.comparingInt(Employee::getId));

        // Print Full-Time Employees Table
        System.out.println("\nFull-Time Employees:");
        printEmployeeTable(fullTimeEmployees);

        // Print Part-Time Employees Table
        System.out.println("\nPart-Time Employees:");
        printEmployeeTable(partTimeEmployees);
        Utils.pauseInterface();
    }

    private void printEmployeeTable(ArrayList<? extends Employee> employees) {
        int maxIdLength = 6; // Default to accommodate "ID"
        int maxNameLength = 15;
        int maxPositionLength = 15;
        int maxEmailLength = 25;
        int maxContactLength = 15;
        int maxHireDateLength = 12;

        // Adjust lengths dynamically based on the content
        for (Employee employee : employees) {
            maxNameLength = Math.max(maxNameLength, safeString(employee.getName()).length());
            maxPositionLength = Math.max(maxPositionLength, safeString(employee.getPosition()).length());
            maxEmailLength = Math.max(maxEmailLength, safeString(employee.getEmail()).length());
            maxContactLength = Math.max(maxContactLength, safeString(employee.getContactNumber()).length());
            maxHireDateLength = Math.max(maxHireDateLength, safeString(employee.getHireDate()).length());
        }

        // Create horizontal line for the table
        String horizontalLine = "+-" + "-".repeat(maxIdLength) + "-+-" +
                "-".repeat(maxNameLength) + "-+-" +
                "-".repeat(maxPositionLength) + "-+-" +
                "-".repeat(maxEmailLength) + "-+-" +
                "-".repeat(maxContactLength) + "-+-" +
                "-".repeat(maxHireDateLength) + "-+";

        // Print table header
        System.out.println(horizontalLine);
        System.out.printf("| %-6s | %-"+maxNameLength+"s | %-"+maxPositionLength+"s | %-"+maxEmailLength+"s | %-"+maxContactLength+"s | %-"+maxHireDateLength+"s |\n",
                "ID", "Name", "Position", "Email", "Contact", "Hire Date");
        System.out.println(horizontalLine);

        // Print each employee's details or "N/A" if the list is empty
        if (employees.isEmpty()) {
            System.out.printf("| %-6s | %-"+maxNameLength+"s | %-"+maxPositionLength+"s | %-"+maxEmailLength+"s | %-"+maxContactLength+"s | %-"+maxHireDateLength+"s |\n",
                    "N/A", "N/A", "N/A", "N/A", "N/A", "N/A");
        } else {
            for (Employee employee : employees) {
                String id = String.format("%04d", employee.getId());
                String name = safeString(employee.getName());
                String position = safeString(employee.getPosition());
                String email = safeString(employee.getEmail());
                String contact = safeString(employee.getContactNumber());
                String hireDate = safeString(employee.getHireDate());

                System.out.printf("| %-6s | %-"+maxNameLength+"s | %-"+maxPositionLength+"s | %-"+maxEmailLength+"s | %-"+maxContactLength+"s | %-"+maxHireDateLength+"s |\n",
                        id, name, position, email, contact, hireDate);
            }
        }

        // Print bottom border
        System.out.println(horizontalLine);
    }

    // Helper method to handle null strings
    private String safeString(String value) {
        return (value == null || value.isBlank()) ? "N/A" : value;
    }
}