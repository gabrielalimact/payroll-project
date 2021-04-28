package model.employees;

import java.time.LocalDate;
import java.time.LocalTime;

public class TimeCard {
    private LocalDate date;
    private LocalTime timeIN;
    private LocalTime timeOUT;



    public TimeCard(LocalDate date, LocalTime timeIN, LocalTime timeOUT) {
        this.date = date;
        this.timeIN = timeIN;
        this.timeOUT = timeOUT;
    }


    public LocalDate getDate() {
        return this.date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTimeIN() {
        return this.timeIN;
    }
    public void setTimeIN(LocalTime timeIN) {
        this.timeIN = timeIN;
    }

    public LocalTime getTimeOUT() {
        return this.timeOUT;
    }
    public void setTimeOUT(LocalTime timeOUT) {
        this.timeOUT = timeOUT;
    }

    @Override
    public String toString() {
        return "{" +
            " Date: '" + getDate() + "'" +
            ", Time IN: '" + getTimeIN() + "'" +
            ", Time OUT: '" + getTimeOUT() + "'" +
            "}";
    }


}