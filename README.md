# EMPRECSYS: Employee Records System

## Table of Contents

[1. Project Overview](#proj-over) <br>
[2. Application of OOP Principles](#oop-prin) <br>
[3. SDG Integration](#sdg) <br>
[4. How to Run the Program](#proj-run) <br>
[5. Screenshots](#pics)
[6. Contributors](#contribs) <br>

## <a id = "proj-over"> 📄 Project Overview </a> <br>
**_EMPRECSYS_** is a Java-based application designed to manage employee information and salary details efficiently. The program offers features such as adding, modifying, and deleting employee records while providing detailed salary breakdowns. It leverages Object-Oriented Programming (OOP) principles to ensure modularity, maintainability, and scalability. The system supports both full-time and part-time employees, demonstrating flexibility in workforce management.

## <a id = "oop-prin"> 🛠️ Application of OOP Principles </a> <br>
This project showcases the use of core OOP principles:

  **_1. Encapsulation_**
  <br> All sensitive employee details (e.g., salary and contact information) are encapsulated within appropriate classes (`Employee`, `FullTimeEmployee`, `PartTimeEmployee`).
  
  Access to these details is controlled through getters and setters, ensuring data integrity and security.
  
  **_2. Abstraction_**
  <br> The abstract class `Employee` defines a common structure for all employees, with concrete details implemented in its subclasses (`FullTimeEmployee`, `PartTimeEmployee`).
  
  This abstraction simplifies the system and reduces code duplication.
  
  **_3. Inheritance_**
  <br> Classes like `FullTimeEmployee` and `PartTimeEmployee` inherit attributes and methods from the `Employee` class, demonstrating code reuse and extending functionality.
  
  **_4. Polymorphism_**
  <br> The system allows dynamic method overriding, as seen in the `calculateSalary` method, which is implemented differently for full-time and part-time employees.
  
  This enables flexibility in handling various employee types seamlessly.

## <a id = "sdg"> 🌍 Sustainable Development Goal (SDG) Integration </a> <br>
This project aligns with SDG 8: Decent Work and Economic Growth, which emphasizes promoting sustained, inclusive, and sustainable economic growth, full and productive employment, and decent work for all.

How it's integrated:

  By managing employee data and salary details efficiently, the system ensures transparency and fairness in employment.
  The application supports both full-time and part-time employees, catering to diverse employment needs and promoting inclusive work practices.

## <a id = "proj-run"> 🚀 Instructions for Running the Program </a> <br>
Follow these steps to set up and run the Employee Records Management System:

  **_1. Clone the Repository_**
  <br> `git clone https://github.com/ErixCrisostomo/EMPRECSYS/edit/main/README.md`
  
  **_2. Compiling the Code_**
  <br> Navigate to the src directory and compile the code:
  
  `javac main/Main.java`
  
  **_3. Running the Program_**
  <br> Execute the compiled Main class:
  
  `java main.Main`
  
  **_4. Using the System_**
  <br> Navigate through the menu to:
  
  Manage employee records (Add, Delete, Modify, View).
  
  Access salary details for full-time and part-time employees.
  
  Exit the program when finished.
  
  **_5. Database Management_**
  <br> The employee records are stored in memory using an EmployeeDatabase class.
  
  Future versions may integrate a persistent database for enhanced functionality.

## <a id = "pics"> 📸 Screenshots </a> <br>

Interface:
<p align = "center">
  <img src = "interface1.JPG" width = "524" height = "342" alt="interface"> 
</p>
<p align = "center">
  <img src = "interface2.JPG" width = "488" height = "403" alt="interface"> 
</p>
<p align = "center">
  <img src = "interface3.JPG" width = "536" height = "303" alt="interface"> 
</p>

## <a id = "contribs"> 👷‍ Contributors </a> <br>
| Name | Role | Contact |
| --- | --- | --- |
| <a href = "(https://github.com/ErixCrisostomo)">Crisostomo, Erix Steven L.</a>| Developer | 23-01893@g.batstate-u.edu.ph |
| <a href = "(https://github.com/marieemoiselle)">Agdon, Fatima Marie P.</a>| Instructor | fatimamarie.agdon@g.batstate-u.edu.ph |
## 💡 Thank You!

