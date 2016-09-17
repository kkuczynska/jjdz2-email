package com.jbd;

import java.util.Scanner;

public class QuestionForm {

    private static final String EMAIL_QUESTION = "Podaj adres email.";
    private static final String KEYWORD_QUESTION = "Podaj słowa kluczowe w tytule.";
    private static final String STARTDATE_QUESTION = "Zakres dat: podaj datę początkową.";
    private static final String ENDDATE_QUESTION = "Zakres dat: podaj datę końcową.";

    public String askQuestion(String question) {

        System.out.println(question);

        Scanner scanner = new Scanner(System.in);
        String answer = scanner.toString();

        return answer;

    }


}
