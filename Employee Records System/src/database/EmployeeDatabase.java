package database;

import java.util.ArrayList;
import models.Employee;

public class EmployeeDatabase {
    public static final ArrayList<Employee> employees = new ArrayList<>();
    public static int nextId = 1; // Start ID generation from 1

    public static ArrayList<Employee> getEmployees() {
        return employees;
    }

    public static void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public static void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public static int generateUniqueId() {
        return nextId++;
    }
}