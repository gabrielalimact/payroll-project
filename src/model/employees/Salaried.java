package model.employees;

import java.util.*;
import model.payments.*;
import java.time.*;

public class Salaried extends Employee {
    
    private double salary;
    
    public Salaried(String name, String address, UUID id, Syndicate syndicate, Payments paymentInfo, double salary) {
        super(name, address, id, syndicate, paymentInfo);
        this.salary = salary;
    }
    
    @Override
    public double calculatePayment(LocalDate date) {
        return this.salary;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
