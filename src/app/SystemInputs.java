package app;

import java.time.*;
import java.util.Scanner;

public class SystemInputs {

    public static String readString(Scanner input, String output){
        if(!output.isEmpty()){
            System.out.println(output);
        }
        String string = input.nextLine();
        System.out.println();
        return string;
    }

    public static Double readDouble(Scanner input, String output){
        if(!output.isEmpty()){
            System.out.println(output);
        }
        double d = input.nextDouble();
        input.nextLine();
        System.out.println();
        return d;
    }

    public static int readInt(Scanner input, String output){
        if(!output.isEmpty()){
            System.out.println(output);
        }
        int r = input.nextInt();
        input.nextLine();
        System.out.println();
        return r;
    }

    public static void pressEnter(Scanner input){
        System.out.println("Press ENTER to continue...");
        input.nextLine();
    }

    public static LocalDate readDate(Scanner input){
        System.out.println("Day: ");
        int day = input.nextInt();
        System.out.println("Month (number): ");
        int month = input.nextInt();
        System.out.println("Year: ");
        int year = input.nextInt();

        return LocalDate.of(year, month, day);
    }

    public static LocalDate lastDayOfMonthh(LocalDate lastDay){
        LocalDate lastDayOfMonth;

        switch(lastDay.getDayOfWeek()){
            case SATURDAY:
                lastDayOfMonth = lastDay.minusDays(1);
                break;
            case SUNDAY:
                lastDayOfMonth = lastDay.minusDays(2);
                break;
            default:
                lastDayOfMonth = lastDay;
                break;
        }
        return lastDayOfMonth;
    }

}
