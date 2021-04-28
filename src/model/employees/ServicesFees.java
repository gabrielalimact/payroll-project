package model.employees;


import java.time.*;

public class ServicesFees {
    private double value;
    private LocalDate date;


    public ServicesFees(double value, LocalDate date) {
        this.value = value;
        this.date = date;
    }


    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "{" +
            " Value = '$" + getValue() + "'" +
            ", Date = '" + getDate() + "'" +
            "}";
    }


}