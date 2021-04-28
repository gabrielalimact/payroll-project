package app;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.time.temporal.*;

import model.employees.*;
import model.payments.*;

public class PaymentConf {
    public static Payments getPayInfo(Scanner input, String schedule){
        int i=1;
        System.out.println("What's the payment method?");
        for(PayMethods method : PayMethods.values()){
            System.out.println(i + " - " + method.getMethod());
            i++;
        }
        int op = input.nextInt();
        PayMethods pmethod = PayMethods.values()[op-1];

        System.out.println("Bank Number: ");
        int bank = input.nextInt();
        System.out.println("Agency Number: ");
        int agency = input.nextInt();
        System.out.println("Account Number: ");
        int account = input.nextInt();


        return new Payments(bank, agency, account, schedule, pmethod);
    }

    public static void TodayPayroll(Scanner input, List<Employee> employeeList){
        System.out.println("Enter the start date, please: ");
        LocalDate start = SystemInputs.readDate(input);
        System.out.println("Enter the end date, please: ");
        LocalDate end = SystemInputs.readDate(input);
        int i=0;
        long daysBetween = ChronoUnit.DAYS.between(start, end);
        System.out.println(daysBetween);
        int isFriday = 0;
        Paycheck paycheck;
        LocalDate date = start;
        
        for(i = 0; i<daysBetween; i++){
            date = start.plusDays(i);
            
            if(date.isEqual(SystemInputs.lastDayOfMonthh(date.with(TemporalAdjusters.lastDayOfMonth())))){
                System.out.println("The last day of month (working day): '" + date.toString() + "', for pay salaried employees");
                Predicate<Employee> isSalaried = sEmployee -> sEmployee instanceof Salaried;
                List<Employee> sEmployees = employeeList.stream().filter(isSalaried).collect(Collectors.toList());
                
                
                if(sEmployees.isEmpty()){
                    System.out.println("There are not salaried employee.");
                }else{
                    for(Employee employee : sEmployees){
                        paycheck = employee.paymentsForToday(date);
                        System.out.println(paycheck.toString());
                    }
                }
            }
            if(isFriday == 0 || isFriday == 2 || isFriday == 4){
                System.out.println("Today, " + date.toString() +", is friday " + isFriday + ", day to pay commissioned employees.");
                Predicate<Employee> isCommissioned = cEmployee -> cEmployee instanceof Commissioned;
                List<Employee> cEmployees = employeeList.stream().filter(isCommissioned).collect(Collectors.toList());

                if(cEmployees.isEmpty()){
                    System.out.println("There are not commissioned employee.");
                }else{
                    for(Employee employee : cEmployees){
                        paycheck = employee.paymentsForToday(date);
                        System.out.println(paycheck.toString());
                    }
                }                

            }
            isFriday ++;
            if(date.getDayOfWeek() == DayOfWeek.FRIDAY){
                System.out.println("Today, " + date.toString() +", is friday, day to pay hourly employees.");
                Predicate<Employee> isHourly = hEmployee -> hEmployee instanceof Hourly;
                List<Employee> hEmployees = employeeList.stream().filter(isHourly).collect(Collectors.toList());

                if(hEmployees.isEmpty()){
                    System.out.println("There are not hourly employee.");
                }else{
                    for(Employee employee : hEmployees){
                        paycheck = employee.paymentsForToday(date);
                        System.out.println(paycheck.toString());
                    }
                }
            }
        }
    }
}
