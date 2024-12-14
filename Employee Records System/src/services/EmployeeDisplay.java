package services;

import java.util.ArrayList;
import java.util.Comparator;

import database.EmployeeDatabase;
import models.Employee;
import models.FullTimeEmployee;
import models.PartTimeEmployee;
import utils.Utils;

public class EmployeeDisplay {
    Titles system1 = new Titles();
    
    public void displayEmployees() {
        system1.employeePortal();
        ArrayList<Employee> employees = EmployeeDatabase.getEmployees();
        if (employees.isEmpty()) {
            System.out.println("\n\t\t\t\t\t\t\t\tNo employees to display.");
            Utils.pauseInterface();
            return;
        }

        ArrayList<FullTimeEmployee> fullTimeEmployees = new ArrayList<>();
        ArrayList<PartTimeEmployee> partTimeEmployees = new ArrayList<>();

        for (Employee employee : employees) {
            if (employee instanceof FullTimeEmployee) {
                fullTimeEmployees.add((FullTimeEmployee) employee);
            } else if (employee instanceof PartTimeEmployee) {
                partTimeEmployees.add((PartTimeEmployee) employee);
            }
        }

        fullTimeEmployees.sort(Comparator.comparingInt(Employee::getId));
        partTimeEmployees.sort(Comparator.comparingInt(Employee::getId));

        System.out.println("\n\t\t\t\t\t\t\t   F U L L - T I M E  E M P L O Y E E S");
        printEmployeeTable(fullTimeEmployees);

        System.out.println("\n\t\t\t\t\t\t\t   P A R T - T I M E  E M P L O Y E E S");
        printEmployeeTable(partTimeEmployees);
        Utils.pauseInterface();
    }

    private void printEmployeeTable(ArrayList<? extends Employee> employees) {
        int maxIdLength = 6;
        int maxNameLength = 15;
        int maxPositionLength = 15;
        int maxEmailLength = 25;
        int maxContactLength = 15;
        int maxHireDateLength = 12;

        for (Employee employee : employees) {
            maxNameLength = Math.max(maxNameLength, safeString(employee.getName()).length());
            maxPositionLength = Math.max(maxPositionLength, safeString(employee.getPosition()).length());
            maxEmailLength = Math.max(maxEmailLength, safeString(employee.getEmail()).length());
            maxContactLength = Math.max(maxContactLength, safeString(employee.getContactNumber()).length());
            maxHireDateLength = Math.max(maxHireDateLength, safeString(employee.getHireDate()).length());
        }

        String horizontalLine = "+-" + "-".repeat(maxIdLength) + "-+-" +
                "-".repeat(maxNameLength) + "-+-" +
                "-".repeat(maxPositionLength) + "-+-" +
                "-".repeat(maxEmailLength) + "-+-" +
                "-".repeat(maxContactLength) + "-+-" +
                "-".repeat(maxHireDateLength) + "-+";

        System.out.println("\t\t\t  " + horizontalLine);
        System.out.printf("\t\t\t  | %-6s | %-"+maxNameLength+"s | %-"+maxPositionLength+"s | %-"+maxEmailLength+"s | %-"+maxContactLength+"s | %-"+maxHireDateLength+"s |\n",
                "ID", "Name", "Position", "Email", "Contact", "Hire Date");
        System.out.println("\t\t\t  " + horizontalLine);

        if (employees.isEmpty()) {
            System.out.printf("\t\t\t  | %-6s | %-"+maxNameLength+"s | %-"+maxPositionLength+"s | %-"+maxEmailLength+"s | %-"+maxContactLength+"s | %-"+maxHireDateLength+"s |\n",
                    "N/A", "N/A", "N/A", "N/A", "N/A", "N/A");
        } else {
            for (Employee employee : employees) {
                String id = String.format("%04d", employee.getId());
                String name = safeString(employee.getName());
                String position = safeString(employee.getPosition());
                String email = safeString(employee.getEmail());
                String contact = safeString(employee.getContactNumber());
                String hireDate = safeString(employee.getHireDate());

                System.out.printf("\t\t\t  | %-6s | %-"+maxNameLength+"s | %-"+maxPositionLength+"s | %-"+maxEmailLength+"s | %-"+maxContactLength+"s | %-"+maxHireDateLength+"s |\n",
                        id, name, position, email, contact, hireDate);
            }
        }

        System.out.println("\t\t\t  " + horizontalLine);
    }

    private String safeString(String value) {
        return (value == null || value.isBlank()) ? "N/A" : value;
    }
}