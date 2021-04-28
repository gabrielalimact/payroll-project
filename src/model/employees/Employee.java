package model.employees;


import model.payments.*;

import java.time.LocalDate;
import java.util.*;


public abstract class Employee {
    
    private String name;
    private String address;  
    private UUID id;  
    private Syndicate syndicate;
    private Payments payInfo;

    public Employee(String name, String address, UUID id, Syndicate syndicate, Payments payInfo) {
        this.name = name;
        this.address = address;
        this.id = id;
        this.syndicate = syndicate;
        this.payInfo = payInfo;
    }

    public Paycheck paymentsForToday(LocalDate date){
        LocalDate lastPayDay;
        double totalSalary = this.calculatePayment(date);
        double discounts = this.calculateServicesFees();
        double syndFees = this.getSyndFees();

        if(syndFees > 0){
            Paycheck last = this.getPayInfo().getLastPaycheck();

            if(last == null){
                discounts += syndFees;
            }else{
                lastPayDay = last.getDate();

                if((lastPayDay.getMonthValue() != date.getMonthValue() && lastPayDay.getYear() != date.getYear())){
                    discounts += syndFees;
                }
            }
        }

        Paycheck paycheck = new Paycheck (this, date, totalSalary, discounts, syndFees);
        this.getPayInfo().getPaycheck().add(paycheck);

        return paycheck;

    }

    public double getSyndFees(){
        double payFees = 0;
        Syndicate synd = getSyndicate();
        if(synd != null && synd.getActive()){
            payFees += getSyndicate().getFees();
        }
        return payFees;
    }

    public double calculateServicesFees(){
        Syndicate synd = getSyndicate();
        List<ServicesFees> taxes;

        double totalTaxes = 0;
        if (synd.getActive()){
            taxes = synd.getServiceFees();
            
            for(ServicesFees s : taxes){
                totalTaxes += s.getValue();
            }
        }
        return totalTaxes;
    }
    
    
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public UUID getId() {
        return this.id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public Syndicate getSyndicate() {
        return this.syndicate;
    }
    public void setSyndicate(Syndicate syndicate) {
        this.syndicate = syndicate;
    }

    public Payments getPayInfo() {
        return this.payInfo;
    }
    public void setPayInfo(Payments payInfo) {
        this.payInfo = payInfo;
    }
    public abstract double calculatePayment(LocalDate date);


    public String basic(){
        return "{\n" +
            "Name='" + getName() + "'\n" +
            "ID =  '" + getId() + "'\n" +
            "}\n";
    }

    
    public String employeeInfos() {
        return
            "-> Name = '" + getName() + "'" +
            "\n-> Address = '" + getAddress() + "'" +
            "\n-> ID = '" + getId() + "'" +
            "\n-> Syndicate = '" + getSyndicate() + "'" +
            "\n-> PaymentInfo = '" + getPayInfo() + "'";
    }

}

