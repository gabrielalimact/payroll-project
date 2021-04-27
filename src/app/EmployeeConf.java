package app;

import model.payments.*;
import model.employees.*;
import model.employees.Salaried;
import app.*;

import java.time.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EmployeeConf{

    public static Employee newEmployee(Scanner input, PaymentSchedule schedule){
        Employee employee = null;
        Payments payment = null;
        Syndicate syndicateMember = null;

        int op;
        String optionSchedule = "";
        String name = SystemInputs.readString(input, "Employee's Name: ");
        String address = SystemInputs.readString(input, "Employee's Address: ");

        System.out.println("Select the type of employee: ");
        System.out.println("1 - Salaried\n2 - Commissioned\n3 - Hourly");

        op = input.nextInt();
        System.out.println();
        optionSchedule = schedule.getSchedule().get(op-1);

        switch(op){
            case 1:
                double salary = SystemInputs.readDouble(input, "Salary Value: ");
                employee = new Salaried(name, address, UUID.randomUUID(), syndicateMember, payment, salary);
                break;
            
            case 2:
                double salaryC = SystemInputs.readDouble(input, "Salary Value: ");
                double commissionPay = SystemInputs.readDouble(input, "Percentage of Commission: ");
                employee = new Commissioned(name, address, UUID.randomUUID(), syndicateMember, payment, salaryC, commissionPay);
                break;
                
            case 3:
                double hourPay = SystemInputs.readDouble(input, "Value for Work Hour: ");
                employee = new Hourly(name, address, UUID.randomUUID(), syndicateMember, payment, hourPay);
                break;
        }

        payment = PaymentConf.getPayInfo(input, optionSchedule);
        employee.setPayInfo(payment);

        System.out.println("Is a Syndicate Member?\n1 - Yes\n2 - No");
        int synd = input.nextInt();
        if(synd == 1){
            double syndFees = SystemInputs.readDouble(input, "Price for syndicate membership: ");
            syndicateMember = new Syndicate(employee.getId(), syndFees, true);
            employee.setSyndicate(syndicateMember);
        }else if (synd == 2){
            double syndFees = 0;
            syndicateMember = new Syndicate(employee.getId(),syndFees, false);
        }
        System.out.println("\nEMPLOYEE SUCCESSFULLY REGISTERED!");
        System.out.println(employee.toString() + "\n\n\n");
        return employee;
    }
    private static int getIndiceDaLista(Scanner input, List<Employee> employeeList){
        int i=1;
        int indice = 0, size = employeeList.size();

        for (Employee employee : employeeList){
            System.out.println(i + ": " + employee.basic());
            i++;
        }

        while(indice <= 0 || indice > size){
            indice = SystemInputs.readInt(input, "");
        }
        return indice-1;
    }

    public static void removeEmployee(Scanner input, List<Employee> employeeList){
        if(!employeeList.isEmpty()){
            employeeList.remove(getIndiceDaLista(input, employeeList));
            System.out.println("Employee Removed!");
        }else{
            System.out.println("This employee list is empty.");
        }
       
       
    }

    public static void addTC(Scanner input, List<Employee> employeeList){
        Predicate<Employee> isHourly = hEmployee -> hEmployee instanceof Hourly; // verifica se o employee é horista
        
        List<Employee> hEmployees = employeeList.stream().filter(isHourly).collect(Collectors.toList()); // cria lista com os employees horistas
        
        if(hEmployees.isEmpty()){
            System.out.println("There are no hourly employees on the list.");
        }else{
            int indice = getIndiceDaLista(input, hEmployees);
            System.out.println("Enter the date:");
            LocalDate date = SystemInputs.readDate(input);
            Hourly employee = (Hourly) hEmployees.get(indice);
            LocalTime tIN, tOUT;

            System.out.println("Please, enter the hours and minutes of IN");
            int hIN = SystemInputs.readInt(input, "Hour of IN: ");
            int mIN = SystemInputs.readInt(input, "Minutes of IN: ");
            tIN = LocalTime.of(hIN, mIN);

            System.out.println("Please, enter the hours and minutes of OUT");
            int hOUT = SystemInputs.readInt(input, "Hour of IN: ");
            int mOUT = SystemInputs.readInt(input, "Minutes of IN: ");
            tOUT = LocalTime.of(hOUT, mOUT);


            TimeCard tc = new TimeCard(date, tIN, tOUT);
            employee.getTimeCard().add(tc);

            System.out.println("Time Card added with success.");
        }
    }
}
