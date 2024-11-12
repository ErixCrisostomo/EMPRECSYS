import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;

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

    public void setName(String name) {
        this.name = name;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
    
    public void setHourlyWage(double hourlyWage) {
        if (hourlyWage > 0) {
            this.hourlyWage = hourlyWage;
        } else {
            System.out.println("Hourly wage must be positive.");
        }
    }
}

class EMPRECSYS1 {
    private ArrayList<Employee> employees = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void addEmployee() {
        try {
            int id = employees.size();
    
            System.out.print("Enter name: ");
            scanner.nextLine();
            String name = scanner.nextLine();
    
            System.out.print("Enter position: ");
            String position = scanner.nextLine();
    
            System.out.print("Enter hourly wage: ");
            double hourlyWage = scanner.nextDouble();
            
            if (hourlyWage < 0) {
                System.out.println("Hourly wage cannot be negative.");
                return;
            }
    
            Employee employee = new Employee(name, id, position, hourlyWage);
            employees.add(employee);
            System.out.printf("Employee added successfully with ID: %04d%n", id);
    
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine();
        }
    }

    public void recordHours() {
        try {
            System.out.print("Enter employee ID: ");
            int id = scanner.nextInt();
    
            // employee exist?
            Employee employee = null;
            for (Employee emp : employees) {
                if (emp.getId() == id) {
                    employee = emp;
                    break;
                }
            }
    
            if (employee == null) {
                System.out.println("Employee not found.");
                return;
            }
    
            System.out.print("Enter hours worked: ");
            double hours = scanner.nextDouble();
            
            if (hours <= 0) {
                System.out.println("Hours worked must be greater than zero.");
                return;
            }
    
            employee.addHours(hours);
            System.out.println("Hours recorded successfully.");
    
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter numerical values.");
            scanner.nextLine();
        }
    }

    public void displayEmployeeDetails() {
        try {
            if (employees.isEmpty()) {
                System.out.println("No employees found.");
                return;
            }
    
            // sort by id
            employees.sort(Comparator.comparingInt(Employee::getId));
    
            // employee table header
            System.out.printf("%-6s | %-25s | %-15s | %-12s | %-12s | %-12s%n", 
                              centerText("ID", 6), centerText("Name", 25), centerText("Position", 15), 
                              centerText("Hourly Wage", 12), centerText("Hours Worked", 12), 
                              centerText("Total Salary", 12));
            System.out.println("---------------------------------------------------------------------------------------------------");
    
            // display employees
            for (Employee employee : employees) {
                String[] nameLines = splitText(employee.getName(), 25);
                String[] positionLines = splitText(employee.getPosition(), 15);
    
                int maxLines = Math.max(nameLines.length, positionLines.length);
    
                // line by line print
                for (int i = 0; i < maxLines; i++) {
                    String id = i == 0 ? String.format("%04d", employee.getId()) : ""; // id 
                    String name = i < nameLines.length ? nameLines[i] : ""; // name 
                    String position = i < positionLines.length ? positionLines[i] : ""; // position 
                    String wage = i == 0 ? String.format("$%.2f", employee.getHourlyWage()) : "";
                    String hours = i == 0 ? String.format("%.2f", employee.getHoursWorked()) : "";
                    String salary = i == 0 ? String.format("$%.2f", employee.calculateSalary()) : "";
    
                    System.out.printf("%-6s | %-25s | %-15s | %-12s | %-12s | %-12s%n",
                                      centerText(id, 6), centerText(name, 25), centerText(position, 15),
                                      centerText(wage, 12), centerText(hours, 12), centerText(salary, 12));
                }
                System.out.println("---------------------------------------------------------------------------------------------------");
            }
    
        } catch (Exception e) {
            System.out.println("An error occurred while displaying employee details.");
        }
    }
    
    // center text
    private String centerText(String text, int width) {
        if (text.length() >= width) return text;
        int padding = (width - text.length()) / 2;
        String pad = " ".repeat(padding);
        return pad + text + pad + (width % 2 == 0 ? "" : "");
    }
    
    // split text for multi-lining
    private String[] splitText(String text, int width) {
        if (text.length() <= width) {
            return new String[]{text};
        }
    
        // break text into chunks for the specidifed width
        int lines = (int) Math.ceil((double) text.length() / width);
        String[] result = new String[lines];
        for (int i = 0; i < lines; i++) {
            int start = i * width;
            int end = Math.min(start + width, text.length());
            result[i] = text.substring(start, end);
        }
        return result;
    }

    public void deleteEmployee() {
        System.out.print("Enter the ID of the employee to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
    
        // id search
        Employee employeeToDelete = null;
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employeeToDelete = employee;
                break;
            }
        }
    
        if (employeeToDelete != null) {
            // confirm delete
            System.out.print("Are you sure you want to delete employee " + employeeToDelete.getName() + " (ID: " + employeeToDelete.getId() + ")? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
    
            if (confirmation.equals("yes") || confirmation.equals("y")) {
                employees.remove(employeeToDelete);
                System.out.println("Employee " + employeeToDelete.getName() + " has been successfully deleted.");
            } else if (confirmation.equals("no") || confirmation.equals("n")) {
                System.out.println("Deletion canceled. No changes made.");
            } else {
                System.out.println("Invalid input. Please type 'yes' or 'no' to confirm deletion.");
            }
        } else {
            System.out.println("Employee with the specified ID not found.");
        }
    }

    public void modifyEmployee() {
        System.out.print("Enter the ID of the employee to modify: ");
        int id = scanner.nextInt();
        scanner.nextLine();
    
        // id search
        Employee employeeToModify = null;
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employeeToModify = employee;
                break;
            }
        }
    
        if (employeeToModify != null) {
            System.out.print("Enter new name (leave blank to keep current: " + employeeToModify.getName() + "): ");
            String newName = scanner.nextLine();
            if (!newName.trim().isEmpty()) {
                employeeToModify.setName(newName);
            }
    
            System.out.print("Enter new position (leave blank to keep current: " + employeeToModify.getPosition() + "): ");
            String newPosition = scanner.nextLine();
            if (!newPosition.trim().isEmpty()) {
                employeeToModify.setPosition(newPosition);
            }
    
            System.out.print("Enter new hourly wage (current: $" + employeeToModify.getHourlyWage() + "): ");
            String wageInput = scanner.nextLine();
            if (!wageInput.trim().isEmpty()) {
                try {
                    double newWage = Double.parseDouble(wageInput);
                    employeeToModify.setHourlyWage(newWage);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid wage input. Hourly wage remains unchanged.");
                }
            }
    
            System.out.println("Employee details updated successfully.");
        } else {
            System.out.println("Employee with the specified ID not found.");
        }
    }

    public void run() {
        while (true) {
            System.out.println("\nEmployee Records System:");
            System.out.println("1. Add Employee");
            System.out.println("2. Record Hours Worked");
            System.out.println("3. Display Employee Details");
            System.out.println("4. Delete Employee"); 
            System.out.println("5. Modify Employee");
            System.out.println("6. Exit");
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
                    deleteEmployee();
                    break;
                case 5:
                    modifyEmployee();
                    break;
                case 6:
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
