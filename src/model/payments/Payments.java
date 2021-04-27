package model.payments;

import java.util.*;

public class Payments {
    private int bank;
    private int agency;
    private int account;
    private String schedule;
    private List<Paycheck> paycheck;
    private PayMethods payMethod;

    public Payments(int bank, int agency, int account, String schedule, PayMethods payMethod) {
        this.bank = bank;
        this.agency = agency;
        this.account = account;
        this.schedule = schedule;
        this.paycheck = new ArrayList<Paycheck>();
        this.payMethod = payMethod;
    }
   


    public int getBank() {
        return this.bank;
    }

    public void setBank(int bank) {
        this.bank = bank;
    }

    public int getAgency() {
        return this.agency;
    }

    public void setAgency(int agency) {
        this.agency = agency;
    }

    public int getAccount() {
        return this.account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public String getSchedule() {
        return this.schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public PayMethods getPayMethod() {
        return this.payMethod;
    }

    public void setPayMethod(PayMethods payMethod) {
        this.payMethod = payMethod;
    }

    public List<Paycheck> getPaycheck() {
        return this.paycheck;
    }

    public void setPaycheck(List<Paycheck> paycheck) {
        this.paycheck = paycheck;
    }
    public Paycheck getLastPaycheck() {
        Paycheck lastPaycheck = null;
        if(!this.paycheck.isEmpty()){
            lastPaycheck = this.paycheck.get(paycheck.size() - 1);
        }
        return lastPaycheck;
    }


    @Override
    public String toString() {
        return "{" +
            " bank='" + getBank() + "'" +
            ", agency='" + getAgency() + "'" +
            ", account='" + getAccount() + "'" +
            ", schedule='" + getSchedule() + "'" +
            ", payment method='" + getPayMethod().getMethod() + "'" +
            "}";
    }


}
