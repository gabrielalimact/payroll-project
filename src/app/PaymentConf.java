package app;

import java.util.*;
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
}
