package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.employees.*;
import model.payments.*;


public class PayrollSystem {
    public static void main(String[] args){
        int op = 13;
        Scanner input = new Scanner(System.in);
        List<Employee> employeeList = new ArrayList<Employee>();
        PaymentSchedule paySchedule = new PaymentSchedule();

        while(op!=0){
            
            System.out.println("\n\n====== Payroll System Menu ======\n");
            System.out.println("Please, choose a option: ");
            System.out.println("1 - Register a new employee");
            System.out.println("2 - Show a list of employees");
            System.out.println("3 - Remove a employee from the system");
            System.out.println("4 - Add Time Card");
            System.out.println("5 - Add a Sale Report");
            System.out.println("6 - Add Service Fees");
            System.out.println("7 - Edit a employee");
            System.out.println("8 - Today Payroll");
            System.out.println("0 - Close menu.\n");
            System.out.print("  -> ");


            op = SystemInputs.readInt(input, "");


            switch(op){
                case 1:
                    employeeList.add(EmployeeConf.newEmployee(input, paySchedule));
                    break;

                case 2:
                    if(employeeList.isEmpty()){
                        System.out.println("\nEmployee list is empty\n");
                    }
                    int i=1;
                    for(Employee employee: employeeList){
                        System.out.println("{ " + i + " }"+" Employee: ");
                        System.out.println(employee.employeeInfos());
                        System.out.println("\n=========================\n");
                        i++;
                    }
                    break;
                case 3:
                    if(employeeList.isEmpty()){
                        System.out.println("\nEmployee list is empty\n");
                    }else{
                        EmployeeConf.removeEmployee(input, employeeList);
                    }
                    break;

                case 4:
                    if(employeeList.isEmpty()){
                        System.out.println("\nEmployee list is empty\n");
                    }else{
                        EmployeeConf.addTC(input, employeeList);
                        
                    }
                    break;
                case 5:
                    if(employeeList.isEmpty()){
                        System.out.println("\nEmployee list is empty\n");
                    }else{
                        EmployeeConf.addSR(input, employeeList);
                    }
                    break;
                case 6:
                    if(employeeList.isEmpty()){
                        System.out.println("\nEmployee list is empty\n");
                    }else{
                        EmployeeConf.addSF(input, employeeList);
                    }
                    break;
                case 7:
                    if(employeeList.isEmpty()){
                        System.out.println("\nEmployee list is empty\n");
                    }else{
                        EmployeeConf.editEmployee(input, employeeList);
                    }
                    break;
                case 8:
                    if(employeeList.isEmpty()){
                        System.out.println("\nEmployee list is empty\n");
                    }else{
                        PaymentConf.TodayPayroll(input, employeeList);
                    }
                    break;
            }
        }

    }
}
