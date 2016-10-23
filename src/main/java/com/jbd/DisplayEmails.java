package com.jbd;

import java.util.List;
import java.util.Scanner;


public class DisplayEmails {
    public final int WHOLE_EMAIL = 1;
    public final int JUST_EMAIL_ADDRESS = 2;

    public void DisplayEmailsFunction(List<Email> emailList) {

        Scanner scanner = new Scanner(System.in);

        int choice;
        boolean flaga = false;


        do {
            System.out.println("What would you like to display ?");
            System.out.println("1 - Whole e-mail");
            System.out.println("2 - Only e-mail's addresses");
            choice = scanner.nextInt();
            if (choice == WHOLE_EMAIL || choice == JUST_EMAIL_ADDRESS) {
                flaga = true;
            } else {
                flaga = false;
                System.out.println("Wrong Option! Choose correctly.");
            }


        } while (!flaga);


        switch (choice) {
            case WHOLE_EMAIL:
                int counter = 1;
                System.out.println("You have taken option 1- whole email");
                for (Email e : emailList) {
                    System.out.print(counter + ". ");
                    System.out.println(e);
                    System.out.println(e.getContent());
                    System.out.println("-----------------------------------------");
                    counter++;
                }
                break;
            case JUST_EMAIL_ADDRESS:
                System.out.println("You have taken option 2 - just email address");
                counter = 1;
                for (Email e : emailList) {
                    System.out.println(counter + ". > " + e.getFrom());
                    counter++;
                }
                break;

        }

    }
}

