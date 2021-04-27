package model.payments;

import model.employees.*;

import java.time.*;

public class Paycheck {
    private Employee employee;
    private LocalDate date;
    private double totalSalary;
    private double discounts;
    private boolean syndFees;


    public Paycheck(Employee employee, LocalDate date, double totalSalary, double discounts, boolean syndFees) {
        this.employee = employee;
        this.date = date;
        this.totalSalary = totalSalary;
        this.discounts = discounts;
        this.syndFees = syndFees;
    }

    public double getNetSalary() {
        return this.getTotalSalary() - this.getDiscounts();
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTotalSalary() {
        return this.totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public boolean getSyndFees() {
        return this.syndFees;
    }

    public void setSyndFees(boolean syndFees) {
        this.syndFees = syndFees;
    }


    public double getDiscounts() {
        return this.discounts;
    }

    public void setDiscounts(double discounts) {
        this.discounts = discounts;
    }

    
    @Override
    public String toString() {
        return "\nEmployee:\n" + getEmployee().basic() +
            "\nTotal Salary = $" + getTotalSalary() +
            "\newDiscounts = $" + getDiscounts() + 
            "\nSalary = $"+ getNetSalary();

    }
}
