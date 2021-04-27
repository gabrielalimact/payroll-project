package model.employees;


import java.time.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import model.payments.*;

public class Hourly extends Employee{
    private double hourPay;
    private List<TimeCard> timeCard;

    @Override
    public double calculatePayment(LocalDate date) { //calculo do pagamento para empregado horista
        double total = 0, hours = 0, extraH = 0;

        List<TimeCard> timeCard;
        List<Paycheck> paycheck;
        Predicate<TimeCard> verifying;

        LocalDate lastDate;
        paycheck = getPayInfo().getPaycheck();
        int i = paycheck.size()-1;

        // verificar se os cartões de ponto do empregado são válidos, ou seja, se os cartões de ponto estão depois a ultima data de pagamento e antes da próxima
        if(!paycheck.isEmpty()){
            lastDate = paycheck.get(i).getDate();
            verifying = tC -> tC.getDate().isAfter(lastDate) && !tC.getDate().isAfter(date); 
        }else{
            verifying = tC ->!tC.getDate().isAfter(date);
        }

        timeCard = this.getTimeCard().stream().filter(verifying).collect(Collectors.toList()); // atribui a timeCard os cartões válidos(para aquela data);


        for( TimeCard t : timeCard ){ //passando a lista de cartões válidos
            LocalTime tIN = t.getTimeIN();
            LocalTime tOUT = t.getTimeOUT();

            Duration d = Duration.between(tIN, tOUT); // calcula a duração entre o horário de entrada e o horário de saída;
            hours = d.toSeconds()/3600; //transforma a duração em horas

            if(hours > 8){
                extraH = hours-8;
                double extraPay = extraH*getHourPay()*1.5;

                total+=8*getHourPay();
                total+=extraPay;
            }else{
                total += hours*getHourPay();
            }
        }

        return total;
    }

    
    public Hourly(String name, String address, UUID id, Syndicate syndicate, Payments paymentInfo, double hourPay) {
        super(name, address, id, syndicate, paymentInfo);
        this.hourPay = hourPay;
    }


    public double getHourPay() {
        return this.hourPay;
    }

    public void setHourPay(double hourPay) {
        this.hourPay = hourPay;
    }


    public List<TimeCard> getTimeCard() {
        return this.timeCard;
    }

    public void setTimeCard(List<TimeCard> timeCard) {
        this.timeCard = timeCard;
    }


    @Override
    public String toString() {
        return "{" +
            " hourPay='" + getHourPay() + "'" +
            ", timeCard='" + getTimeCard() + "'" +
            "}";
    }

}
