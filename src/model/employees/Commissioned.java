package model.employees;

import model.payments.*;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Commissioned extends Employee{
    private double salary;
    private double percCommission;
    private List<SalesReport> salesReport;

    @Override
    public double calculatePayment(LocalDate date) { //calculo de pagamento para empregado comissionado
        double total = this.getSalary()/2;

        List<SalesReport> salesReport;
        List<Paycheck> paycheck;
        Predicate<SalesReport> verifying;
        LocalDate lastDate;
        paycheck = getPayInfo().getPaycheck();
        int i = paycheck.size()-1;

        //verifica os relatórios de venda que aconteceram depois do ultimo pagamento e antes do próximo, calculando o pagamento total;
        if(!paycheck.isEmpty() && paycheck != null){
            lastDate = paycheck.get(i).getDate();
            verifying = s -> s.getDate().isAfter(lastDate) && !s.getDate().isAfter(date); // verificando se a data registrada é depois da ultima data e se a data atual não é depois da data registrada; 
        } else{
            verifying = s -> !s.getDate().isAfter(date); // verificando se a data registrada não é depois da atual
        }

        salesReport = this.getSalesReport().stream().filter(verifying).collect(Collectors.toList()); // atribui a salesReport todas as vendas que são válidas, após o processo de verificação


        for( SalesReport sales : salesReport ){
            double commission = (this.getPercCommission() / 100.0);
            double totalC = sales.getValue() * commission;
            total += totalC;
        }
        return total;
        
    }
    public Commissioned(String name, String address, UUID id, Syndicate syndicate, Payments paymentInfo, double salary, double percCommission) {
        super(name, address, id, syndicate, paymentInfo);
        this.salary = salary;
        this.percCommission = percCommission;
        this.salesReport = new ArrayList<SalesReport>();
    }

    
    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


    public double getPercCommission() {
        return this.percCommission;
    }

    public void setPercCommission(double percCommission) {
        this.percCommission = percCommission;
    }

    public List<SalesReport> getSalesReport() {
        return this.salesReport;
    }

    public void setSalesReport(List<SalesReport> salesReport) {
        this.salesReport = salesReport;
    }


    @Override
    public String toString() {
        return "{" +
            " Salary = $" + getSalary() +
            ", Percentage of Commission = " + getPercCommission() + "%" +
            ", Sales Report = '" + getSalesReport() + "'" +
            "}";
    }



}
