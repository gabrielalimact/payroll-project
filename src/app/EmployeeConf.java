package app;

import model.payments.*;
import model.employees.*;
import model.employees.Salaried;


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
        // String name = SystemInputs.readString(input, "Employee's Name: ");
        // String address = SystemInputs.readString(input, "Employee's Address: ");

        System.out.println("Select the type of employee: ");
        System.out.println("1 - Salaried\n2 - Commissioned\n3 - Hourly");

        op = input.nextInt();
        System.out.println();
        optionSchedule = schedule.getSchedule().get(op-1);
        switch(op){
            case 1:
            double salary = SystemInputs.readDouble(input, "Salary Value: ");
            employee = new Salaried("Gabriela", "Maceio", UUID.randomUUID(), syndicateMember, payment, salary);
            //employee = new Salaried(name, address, UUID.randomUUID(), syndicateMember, payment, salary);
            break;
            
            case 2:
            double salaryC = SystemInputs.readDouble(input, "Salary Value: ");
            double commissionPay = SystemInputs.readDouble(input, "Percentage of Commission: ");
            employee = new Commissioned("Leticia", "Arapiraca", UUID.randomUUID(), syndicateMember, payment, salaryC, commissionPay);
            //employee = new Commissioned(name, address, UUID.randomUUID(), syndicateMember, payment, salaryC, commissionPay);
            break;
            
            case 3:
            double hourPay = SystemInputs.readDouble(input, "Value for Work Hour: ");
            employee = new Hourly("Joao", "Maceio", UUID.randomUUID(), syndicateMember, payment, hourPay);
            //employee = new Hourly(name, address, UUID.randomUUID(), syndicateMember, payment, hourPay);
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
        System.out.println(employee.employeeInfos() + "\n\n\n");
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
            System.out.println("Enter the date:");
            LocalDate date = SystemInputs.readDate(input);
            Hourly employee = (Hourly) hEmployees.get(getIndiceDaLista(input, hEmployees));
            // LocalTime tIN, tOUT;

            System.out.println("Please, enter the hours and minutes of IN");
            int hIN = SystemInputs.readInt(input, "Hour of IN: ");
            int mIN = SystemInputs.readInt(input, "Minutes of IN: ");
            LocalTime tIN = LocalTime.of(hIN, mIN);

            System.out.println("Please, enter the hours and minutes of OUT");
            int hOUT = SystemInputs.readInt(input, "Hour of OUT: ");
            int mOUT = SystemInputs.readInt(input, "Minutes of OUT: ");
            LocalTime tOUT = LocalTime.of(hOUT, mOUT);

            TimeCard tc = new TimeCard(date, tIN, tOUT);
            employee.getTimeCard().add(tc);

            System.out.println("Time Card added with success.");
        }
    }

    public static void addSR(Scanner input, List<Employee> employeeList){
        Predicate<Employee> isCommissioned = cEmployee -> cEmployee instanceof Commissioned; // verifica se o employee é comissionado
        
        List<Employee> cEmployees = employeeList.stream().filter(isCommissioned).collect(Collectors.toList()); // cria lista com os employees comissionados

        if(cEmployees.isEmpty()){
            System.out.println("There are no commissioned employees on the list.");
        }else{
            System.out.println("Enter the date:");
            LocalDate date = SystemInputs.readDate(input);
            Commissioned employee = (Commissioned) cEmployees.get(getIndiceDaLista(input, cEmployees));

            double saleValue = SystemInputs.readDouble(input, "Please, enter the value of sale: ");

            SalesReport sales = new SalesReport(saleValue, date);
            employee.getSalesReport().add(sales);
            System.out.println("The sale report was added successfully");
        }
        
    }

    public static void addSF(Scanner input, List<Employee> employeeList){
        int i=0;
        for(Employee employee : employeeList){
            if(employee.getSyndicate()!=null)
            i++;
        }
        if(i!=0){
            Predicate<Employee> isSynd = syndM -> syndM.getSyndicate() != null && syndM.getSyndicate().getActive() ; // verifica se o employee é sindicalista
            List<Employee> syndEmployee = employeeList.stream().filter(isSynd).collect(Collectors.toList()); // adiciona todos os sindicalistas numa lista

            if(syndEmployee.isEmpty()){
                System.out.println("There are no syndicalist employees on the list.");
            }else{
                System.out.println("Enter the date:");
                LocalDate date = SystemInputs.readDate(input);
                Employee employee = syndEmployee.get(getIndiceDaLista(input, syndEmployee));
                double feesValue = SystemInputs.readDouble(input, "Please, enter the value of fees: ");

                ServicesFees fees = new ServicesFees(feesValue, date);
                employee.getSyndicate().getServiceFees().add(fees);
                System.out.println("Service Fees added.");

            }
        }else{
            System.out.println("There are no syndicalist employees on the list.");
        }
        
    }

    public static void editEmployee(Scanner input, List<Employee> employeeList){
        // Os seguintes atributos de um empregado podem ser alterados: nome, endereço, tipo (hourly, salaried, commissioned), método de pagamento, se pertence ao sindicato ou não, identificação no sindicato, taxa sindical.
        int op = 12;
        Employee e = employeeList.get(getIndiceDaLista(input, employeeList));

        while(op!=0){
            System.out.println("\nPlease, choose a option to change: ");
            System.out.println("1 - Name");
            System.out.println("2 - Address");
            System.out.println("3 - Type");
            System.out.println("4 - Payment Method");
            System.out.println("5 - Syndicate Status");
            System.out.println("6 - Syndicate ID");
            System.out.println("7 - Syndicate Fee");
            System.out.println("0 - Close menu.\n");
            System.out.print("  -> ");


            op = SystemInputs.readInt(input, "");


            switch(op){
            case 1:
                String name = SystemInputs.readString(input, "Employee's Name: '");
                e.setName(name);
                break;
            case 2:
                String address = SystemInputs.readString(input, "Employee's Address: '");
                e.setAddress(address);
                break;
            case 3:
                System.out.println("Select the type of employee: ");
                System.out.println("1 - Salaried\n2 - Commissioned\n3 - Hourly\nPress any other key to back to menu");
                int i = SystemInputs.readInt(input, "");
                switch(i){
                    case 1:
                        if(!(e instanceof Salaried)){ // verifica se o employee não é salariado
                            double salary = SystemInputs.readDouble(input, "Salary Value: ");
                            e = new Salaried(e.getName(), e.getAddress(), e.getId(), e.getSyndicate(), e.getPayInfo(), salary);
                        }else{
                            System.out.println("The employee's already salaried.");
                        }
                        break;
                    
                    case 2:
                        if(!(e instanceof Commissioned)){ // verifica se o employee não é comissionado
                            double salaryC = SystemInputs.readDouble(input, "Salary Value: ");
                            double commissionPay = SystemInputs.readDouble(input, "Percentage of Commission: ");
                            e = new Commissioned(e.getName(), e.getAddress(), e.getId(), e.getSyndicate(), e.getPayInfo(), salaryC, commissionPay);
                        }else{
                            System.out.println("The employee's already commissioned.");
                        }
                        break;

                    case 3:
                        if(!(e instanceof Hourly)){// verifica se o employee não é horista
                            double hourPay = SystemInputs.readDouble(input, "Value for Work Hour: ");
                            e = new Hourly(e.getName(), e.getAddress(), e.getId(), e.getSyndicate(), e.getPayInfo(), hourPay);
                        }else{
                            System.out.println("The employee's already hourly.");
                        }
                        break;

                    default:
                        break;
                }
                break;
            case 4:
                int a = 1;
                System.out.println("What's the payment method?");
                for(PayMethods method : PayMethods.values()){
                    System.out.println(a + " - " + method.getMethod());
                    a++;
                }
                int x = input.nextInt();
                switch(x){
                    case 1:
                        e.getPayInfo().setPayMethod(PayMethods.values()[x-1]);
                        System.out.println("Method was changed.\n" + e.employeeInfos());
                        break;
                    case 2:
                        e.getPayInfo().setPayMethod(PayMethods.values()[x-1]);
                        System.out.println("Method was changed.\n" + e.employeeInfos());
                        break;
                    case 3:
                        e.getPayInfo().setPayMethod(PayMethods.values()[x-1]);
                        System.out.println("Method was changed.\n" + e.employeeInfos());
                        break;
                    default:
                        System.out.println("Invalid option.\n");
                        break;
                }
                break;
            case 5: 
                if(e.getSyndicate()==null){
                    System.out.println("Employee isn't a syndicalist. Want to register?\n1 - Yes\n2 - No\n");
                    int o = SystemInputs.readInt(input, "");
                    if(o == 1){                        
                        double syndFees = SystemInputs.readDouble(input, "Price for syndicate membership: ");
                        Syndicate syndicateMember = new Syndicate(e.getId(), syndFees, true);
                        e.setSyndicate(syndicateMember);
                    }else{
                        break;
                    }
                }else{
                    if(e.getSyndicate().getActive()){
                        System.out.println("Employee is a syndicalist. Do you want to disable?\n1 - Yes\n2 - No");
                        int o = SystemInputs.readInt(input, "");
                        if(o == 1){                        
                            e.getSyndicate().setActive(false);
                        }else{
                            break;
                        }
                    }else if(!e.getSyndicate().getActive()){
                        System.out.println("Employee is a inactive syndicalist. Do you want to reactivate?");
                        int o = SystemInputs.readInt(input, "1 - Yes\n2 - No");
                        if(o == 1){
                            e.getSyndicate().setActive(true);                            
                        }else{
                            break;
                        }
                    }
                }

                break;
            case 6:
                if(e.getSyndicate()!=null){
                    e.getSyndicate().setIdEmployee(UUID.randomUUID());
                    System.out.println(e.employeeInfos());                    
                }else{
                    System.out.println("The employee isn't a syndicalist.");
                }
                break;
            case 7:
                if(e.getSyndicate()!=null){
                    double syndFees = SystemInputs.readDouble(input, "Enter the new value: ");
                    e.getSyndicate().setFees(syndFees);
                    System.out.println(e.employeeInfos());                  
                }else{
                    System.out.println("The employee isn't a syndicalist.");
                }
                
        }
        


        }


    }


}
