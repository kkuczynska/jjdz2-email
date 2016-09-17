package com.jbd;


public class Questions {

    private static final String EMAIL_QUESTION = "Podaj adres email.";
    private static final String KEYWORD_QUESTION = "Podaj słowa kluczowe w tytule.";
    private static final String STARTDATE_QUESTION = "Zakres dat: podaj datę początkową.";
    private static final String ENDDATE_QUESTION = "Zakres dat: podaj datę końcową.";


    public static String getEmailQuestion() {
        return EMAIL_QUESTION;
    }

    public static String getKeywordQuestion() {
        return KEYWORD_QUESTION;
    }

    public static String getStartdateQuestion() {
        return STARTDATE_QUESTION;
    }

    public static String getEnddateQuestion() {
        return ENDDATE_QUESTION;
    }

}
