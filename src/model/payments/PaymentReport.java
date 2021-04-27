package model.payments;

import java.time.*;
import java.util.*;

public class PaymentReport{
    private List<Paycheck> paycheck;
    private LocalDate date;



    public PaymentReport(List<Paycheck> paycheck, LocalDate date) {
        this.paycheck = paycheck;
        this.date = date;
    }


    public List<Paycheck> getPaycheck() {
        return this.paycheck;
    }

    public void setPaycheck(List<Paycheck> paycheck) {
        this.paycheck = paycheck;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}