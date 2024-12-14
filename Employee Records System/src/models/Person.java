package models;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Person {
    public String name;
    public int id;
    public String position;
    public String contactNumber;
    public String email;
    public String hireDate;

    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]{4,}@gmail\\.com$";
    private static final Scanner scanner = new Scanner(System.in);

    public Person(String name, int id, String position, String contactNumber, String email, String hireDate) {
        this.name = name;
        this.id = id;
        this.position = position;
        this.contactNumber = contactNumber;
        this.email = email;
        this.hireDate = hireDate;
    }

    public Person() {
        // Default constructor to allow creating an empty Person object.
    }

    public void validateAndSetDetails() {
        System.out.println("\n\t\t\t\t\t       --------------- E M P L O Y E E  R E G I S T R Y ---------------");
        this.name = promptInput("\n\t\t\t\t\t\t\t   Enter name: ");
        this.position = promptInput("\n\t\t\t\t\t\t\t   Enter position: ");
        this.contactNumber = promptInput("\n\t\t\t\t\t\t\t   Enter contact number: ");
        this.email = promptValidEmail();
        this.hireDate = promptValidHireDate();

        
    }

    private String promptValidEmail() {
        while (true) {
            System.out.print("\n\t\t\t\t\t\t\t   Enter email (Gmail): ");
            String email = scanner.nextLine();
            if (email.matches(EMAIL_REGEX)) {
                return email;
            } else {
                System.out.println("\n\t\t\t\t\t\t   Invalid email. Username must be at least 4 characters long.");
                System.out.println("\t\t\t\t\t\t\t\t   Email must be Gmail.");
            }
        }
    }

    private String promptValidHireDate() {
        while (true) {
            System.out.print("\n\t\t\t\t\t\t\t   Enter hire date (MM-DD-YYYY): ");
            String hireDate = scanner.nextLine();
            if (hireDate.isBlank()) {
                System.out.println("\n\t\t\t\t\t\t\tHire date cannot be empty. Please try again.");
                continue;
            }
            try {
                LocalDate.parse(hireDate, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
                return hireDate;
            } catch (DateTimeParseException e) {
                System.out.println("\n\t\t\t\t\t\tInvalid date. Please ensure the format is MM-DD-YYYY.");
            }
        }
    }

    private String promptInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // Getters
    public String getName() { return name; }
    public int getId() { return id; }
    public String getPosition() { return position; }
    public String getContactNumber() { return contactNumber; }
    public String getEmail() { return email; }
    public String getHireDate() { return hireDate; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setId(int id) { this.id = id; }
    public void setPosition(String position) { this.position = position; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public void setEmail(String email) { this.email = email; }
    public void setHireDate(String hireDate) { this.hireDate = hireDate; }

    @Override
    public String toString() {
        return "Name: " + name + "\nID: " + id + "\nPosition: " + position + "\nEmail: " + email +
               "\nContact Number: " + contactNumber + "\nHire Date: " + hireDate;
    }
}