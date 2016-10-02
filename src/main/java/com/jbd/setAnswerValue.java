package com.jbd;

import java.util.Scanner;

import static com.jbd.SearchCriteria.*;

public class setAnswerValue {


    public static void setValue(String questionAsked) {

        Scanner scanner = new Scanner(System.in);

        switch(questionAsked) {
            case "EMAIL_QUESTION" : setEMAIL(scanner.nextLine());
                break;
            case "KEYWORD_QUESTION" : setKEYWORDS(scanner.nextLine());
                break;
            case "STARTDATE_QUESTION" : setSTARTDATE(scanner.nextLine());
                break;
            case "ENDDATE_QUESTION" : setENDDATE(scanner.nextLine());
                break;
        }

    }


}
