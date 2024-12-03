package models;

public class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name, int id, String position, String email, String contactNumber, String hireDate, double monthlySalary) {
        super(name, id, position, email, contactNumber, hireDate);
        setMonthlySalary(monthlySalary); // Use setter to ensure deductions are calculated
    }

    @Override
    public double calculateSalary() {
        return getMonthlySalary(); // Return the monthly salary
    }

    public double getMonthlySalary() {
        return super.finalSalary() + totalDeduc(); // Ensure deductions are reflected
    }

    public void setMonthlySalary(double monthlySalary) {
        setTotalSalary(monthlySalary); // Update total salary and recalculate deductions
    }
}