package services;

import java.util.ArrayList;
import java.util.Comparator;

import database.EmployeeDatabase;
import models.Employee;
import models.FullTimeEmployee;
import models.PartTimeEmployee;
import utils.Utils;

public class EmployeeSalaryDisplay {
    Titles system1 = new Titles();
    
    public void displayEmployeeSalaryDetails() {
        system1.salaryMangement();
        ArrayList<Employee> employees = EmployeeDatabase.getEmployees(); // Fetch the latest employee list
        if (employees.isEmpty()) {
            System.out.println("\n\t\t\t\t\t\t\t\tNo employees to display.");
            Utils.pauseInterface();
            return;
        }

        // Separate employees by type
        ArrayList<FullTimeEmployee> fullTimeEmployees = new ArrayList<>();
        ArrayList<PartTimeEmployee> partTimeEmployees = new ArrayList<>();

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

        // Print Full-Time Employees Salary Table
        System.out.println("\n\t\t\t\t\t   F U L L - T I M E  E M P L O Y E E S'  S A L A R Y  D E T A I L S");
        printSalaryDetailsTable(fullTimeEmployees);

        // Print Part-Time Employees Salary Table
        System.out.println("\n\t\t\t\t\t   P A R T - T I M E  E M P L O Y E E S'  S A L A R Y  D E T A I L S");
        printSalaryDetailsTable(partTimeEmployees);
        Utils.pauseInterface();
    }

    private void printSalaryDetailsTable(ArrayList<? extends Employee> employees) {
        // Dynamically calculate the maximum lengths for columns
        int maxIdLength = "ID".length();
        int maxNameLength = "Name".length();
        int maxSalaryLength = "Salary".length();
        int maxPhilHealthLength = "PhilHealth".length();
        int maxPagibigLength = "Pagibig".length();
        int maxSssLength = "SSS".length();
        int maxTotalDeductionLength = "Total Deduction".length();
        int maxFinalSalaryLength = "Final Salary".length();

        for (Employee employee : employees) {
            maxIdLength = Math.max(maxIdLength, String.format("%04d", employee.getId()).length());
            maxNameLength = Math.max(maxNameLength, safeString(employee.getName()).length());
            maxSalaryLength = Math.max(maxSalaryLength, String.format("%.2f", employee.calculateSalary()).length());
            maxPhilHealthLength = Math.max(maxPhilHealthLength, String.format("%.2f", employee.getDeducPhilHealth()).length());
            maxPagibigLength = Math.max(maxPagibigLength, String.format("%.2f", employee.getDeducPagIbig()).length());
            maxSssLength = Math.max(maxSssLength, String.format("%.2f", employee.getDeducSSS()).length());
            maxTotalDeductionLength = Math.max(maxTotalDeductionLength, String.format("%.2f", employee.totalDeduc()).length());
            maxFinalSalaryLength = Math.max(maxFinalSalaryLength, String.format("%.2f", employee.finalSalary()).length());
        }

        // Build the horizontal line to match column widths
        String horizontalLine = "+-" + "-".repeat(maxIdLength) + "-+-" +
                "-".repeat(maxNameLength) + "-+-" +
                "-".repeat(maxSalaryLength) + "-+-" +
                "-".repeat(maxPhilHealthLength) + "-+-" +
                "-".repeat(maxPagibigLength) + "-+-" +
                "-".repeat(maxSssLength) + "-+-" +
                "-".repeat(maxTotalDeductionLength) + "-+-" +
                "-".repeat(maxFinalSalaryLength) + "-+";

        // Print table header with dynamic column widths
        System.out.println("\t\t\t  " + horizontalLine);
        System.out.printf("\t\t\t  | %-"+maxIdLength+"s | %-"+maxNameLength+"s | %-"+maxSalaryLength+"s | %-"+maxPhilHealthLength+"s | %-"+maxPagibigLength+"s | %-"+maxSssLength+"s | %-"+maxTotalDeductionLength+"s | %-"+maxFinalSalaryLength+"s |\n",
                          "ID", "Name", "Salary", "PhilHealth", "Pagibig", "SSS", "Total Deduction", "Final Salary");
        System.out.println("\t\t\t  " + horizontalLine);

        // Print each employee's details
        if (employees.isEmpty()) {
            System.out.printf("\t\t\t  | %-"+maxIdLength+"s | %-"+maxNameLength+"s | %-"+maxSalaryLength+"s | %-"+maxPhilHealthLength+"s | %-"+maxPagibigLength+"s | %-"+maxSssLength+"s | %-"+maxTotalDeductionLength+"s | %-"+maxFinalSalaryLength+"s |\n",
                    "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A");
        } else {
            for (Employee employee : employees) {
                String id = String.format("%04d", employee.getId());
                String name = safeString(employee.getName());
                String salary = String.format("%.2f", employee.calculateSalary());
                String philHealth = String.format("%.2f", employee.getDeducPhilHealth());
                String pagIbig = String.format("%.2f", employee.getDeducPagIbig());
                String sss = String.format("%.2f", employee.getDeducSSS());
                String totalDeduction = String.format("%.2f", employee.totalDeduc());
                String finalSalary = String.format("%.2f", employee.finalSalary());

                System.out.printf("\t\t\t  | %-"+maxIdLength+"s | %-"+maxNameLength+"s | %-"+maxSalaryLength+"s | %-"+maxPhilHealthLength+"s | %-"+maxPagibigLength+"s | %-"+maxSssLength+"s | %-"+maxTotalDeductionLength+"s | %-"+maxFinalSalaryLength+"s |\n",
                        id, name, salary, philHealth, pagIbig, sss, totalDeduction, finalSalary);
            }
        }

        System.out.println("\t\t\t  " + horizontalLine);
    }
    // Helper method to handle null or empty strings
    private String safeString(String value) {
        return (value == null || value.isBlank()) ? "N/A" : value;
    }
}