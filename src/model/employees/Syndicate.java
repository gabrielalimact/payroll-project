package model.employees;

import java.util.*;


public class Syndicate {
    private UUID idEmployee;
    private double fees;
    private boolean active;
    private List<ServicesFees> serviceFees;


    public Syndicate(UUID idEmployee, double fees, boolean active) {
        this.idEmployee = idEmployee;
        this.fees = fees;
        this.active = active;
    }

    public UUID getIdEmployee() {
        return this.idEmployee;
    }

    public void setIdEmployee(UUID idEmployee) {
        this.idEmployee = idEmployee;
    }

    public double getFees() {
        return this.fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }


    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<ServicesFees> getServiceFees() {
        return this.serviceFees;
    }

    public void setServiceFees(List<ServicesFees> serviceFees) {
        this.serviceFees = serviceFees;
    }


    @Override
    public String toString() {
        return "{" +
            " Employee ID ='" + getIdEmployee() + "'" +
            "\nFees Price = '" + getFees() + "'" +
            "\n Active = '" + getActive() + "'" +
            "\n Service Fees = '" + getServiceFees() + "'" +
            "}";
    }


}