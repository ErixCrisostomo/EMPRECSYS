import java.util.ArrayList;
import java.util.Scanner;

class Employee {
    private String name;
    private int id;
    private String position;
    private double hourlyWage;
    private double hoursWorked;

    public Employee(String name, int id, String position, double hourlyWage) {
        this.name = name;
        this.id = id;
        this.position = position;
        this.hourlyWage = hourlyWage;
        this.hoursWorked = 0;
    }

    public String getName() { return name; }
    public int getId() { return id; }
    public String getPosition() { return position; }
    public double getHourlyWage() { return hourlyWage; }
    public double getHoursWorked() { return hoursWorked; }

    public void addHours(double hours) {
        if (hours > 0) this.hoursWorked += hours;
    }

    public double calculateSalary() {
        return hourlyWage * hoursWorked;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Position: " + position +
               ", Hourly Wage: $" + hourlyWage + ", Hours Worked: " + hoursWorked +
               ", Total Salary: $" + calculateSalary();
    }
}

class EMPRECSYS1 {
    private ArrayList<Employee> employees = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void addEmployee() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        scanner.nextLine();

        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Clear the newline

        System.out.print("Enter position: ");
        String position = scanner.nextLine();

        System.out.print("Enter hourly wage: ");
        double hourlyWage = scanner.nextDouble();
        

        Employee employee = new Employee(name, id, position, hourlyWage);
        employees.add(employee);
        System.out.println("Employee added successfully.");
    }

    public void recordHours() {
        System.out.print("Enter employee ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter hours worked: ");
        double hours = scanner.nextDouble();

        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employee.addHours(hours);
                System.out.println("Hours recorded successfully.");
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    public void displayEmployeeDetails() {
        System.out.print("Enter employee ID: ");
        int id = scanner.nextInt();

        for (Employee employee : employees) {
            if (employee.getId() == id) {
                System.out.println(employee);
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    public void displayAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }

    public void run() {
        while (true) {
            System.out.println("\nEmployee Records System:");
            System.out.println("1. Add Employee");
            System.out.println("2. Record Hours Worked");
            System.out.println("3. Display Employee Details");
            System.out.println("4. Display All Employees");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    recordHours();
                    break;
                case 3:
                    displayEmployeeDetails();
                    break;
                case 4:
                    displayAllEmployees();
                    break;
                case 5:
                    System.out.println("Exiting system.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        EMPRECSYS1 system = new EMPRECSYS1();
        system.run();
    }
}