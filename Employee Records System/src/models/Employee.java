package models;

public abstract class Employee extends Person {
    private double totalSalary;
    private double deducPhilHealth;
    private double deducPagIbig;
    private double deducSSS;

    public Employee(String name, int id, String position, String email, String contactNumber, String hireDate) {
        super(name, id, position, contactNumber, email, hireDate);

        this.name = (name == null || name.isBlank()) ? "Unknown" : name;
        this.position = (position == null || position.isBlank()) ? "Unspecified" : position;
        this.email = (email == null || email.isBlank()) ? "No Email" : email;
        this.contactNumber = (contactNumber == null || contactNumber.isBlank()) ? "No Contact" : contactNumber;
        this.hireDate = (hireDate == null || hireDate.isBlank()) ? "Unknown Date" : hireDate;

        this.totalSalary = 0;
        this.deducPhilHealth = 0;
        this.deducPagIbig = 0;
        this.deducSSS = 0;
    }

    public abstract double calculateSalary();

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
        calculateDeductions();
    }

    private void calculateDeductions() {
        deducPhilHealth = totalSalary * 0.05;
        deducPagIbig = totalSalary < 1500 ? totalSalary * 0.01 : totalSalary * 0.02;

        double sssVal;
        if (totalSalary < 4000) {
            sssVal = 0;
        }
        else if (totalSalary <= 4250) {
            sssVal = 4000.00;
        } 
        else if (totalSalary >= 19750) {
            sssVal = 20000.00;
        } 

        
        else {
            sssVal = Math.floor((totalSalary - 4250) / 500) * 500 + 4500;
        }
        deducSSS = sssVal * 0.045;
    }

    public double getDeducPhilHealth() {
        return deducPhilHealth;
    }

    public double getDeducPagIbig() {
        return deducPagIbig;
    }

    public double getDeducSSS() {
        return deducSSS;
    }

    public double totalDeduc() {
        return deducPhilHealth + deducPagIbig + deducSSS;
    }

    public double finalSalary() {
        return totalSalary - totalDeduc();
    }
}