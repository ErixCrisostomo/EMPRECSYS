# EMPRECSYS: Employee Records System

<b> 📄 Project Overview
<br> EMPRECSYS is a Java-based application designed to manage employee information and salary details efficiently. The program offers features such as adding, modifying, and deleting employee records while providing detailed salary breakdowns. It leverages Object-Oriented Programming (OOP) principles to ensure modularity, maintainability, and scalability. The system supports both full-time and part-time employees, demonstrating flexibility in workforce management.


🛠️ Application of OOP Principles

This project showcases the use of core OOP principles:

1. Encapsulation

All sensitive employee details (e.g., salary and contact information) are encapsulated within appropriate classes (Employee, FullTimeEmployee, PartTimeEmployee).

Access to these details is controlled through getters and setters, ensuring data integrity and security.

2. Abstraction

The abstract class Employee defines a common structure for all employees, with concrete details implemented in its subclasses (FullTimeEmployee, PartTimeEmployee).

This abstraction simplifies the system and reduces code duplication.

3. Inheritance

Classes like FullTimeEmployee and PartTimeEmployee inherit attributes and methods from the Employee class, demonstrating code reuse and extending functionality.

4. Polymorphism

The system allows dynamic method overriding, as seen in the calculateSalary method, which is implemented differently for full-time and part-time employees.

This enables flexibility in handling various employee types seamlessly.

🌍 Sustainable Development Goal (SDG) Integration

This project aligns with SDG 8: Decent Work and Economic Growth, which emphasizes promoting sustained, inclusive, and sustainable economic growth, full and productive employment, and decent work for all.

How it's integrated:

By managing employee data and salary details efficiently, the system ensures transparency and fairness in employment.

The application supports both full-time and part-time employees, catering to diverse employment needs and promoting inclusive work practices.

🚀 Instructions for Running the Program

Follow these steps to set up and run the Employee Records Management System:

1. Prerequisites

Java Development Kit (JDK) installed (version 8 or higher).

A Java-compatible IDE (e.g., IntelliJ IDEA, Eclipse, or Visual Studio Code) or terminal for compiling and running the program.

2. Cloning the Repository

git clone https://github.com/<your-username>/employee-records-management.git
cd employee-records-management

3. Compiling the Code

Navigate to the src directory and compile the code:

javac main/Main.java

4. Running the Program

Execute the compiled Main class:

java main.Main

5. Using the System

Navigate through the menu to:

Manage employee records (Add, Delete, Modify, View).

Access salary details for full-time and part-time employees.

Exit the program when finished.

6. Database Management

The employee records are stored in memory using an EmployeeDatabase class.

Future versions may integrate a persistent database for enhanced functionality.

📸 Screenshots (Optional)

Include screenshots of the program interface and sample outputs for a better understanding of the system's features.

💡 Feel free to contribute!
If you'd like to improve this project, create a pull request or report issues in the repository's issue tracker. Together, we can build a more robust and feature-rich employee management solution!

