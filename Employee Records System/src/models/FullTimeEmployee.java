package models;

public class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name, int id, String position, String email, String contactNumber, String hireDate, double monthlySalary) {
        super(name, id, position, email, contactNumber, hireDate);
        setMonthlySalary(monthlySalary);
    }

    @Override
    public double calculateSalary() {
        return getMonthlySalary();
    }

    public double getMonthlySalary() {
        return super.finalSalary() + totalDeduc();
    }

    public void setMonthlySalary(double monthlySalary) {
        setTotalSalary(monthlySalary);
    }
}