package com.jbd;


import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchCriteriaValidator {

    private static final String INCORRECT_EMAIL_MESSAGE = "Niepoprawny adres email.";
    private static final String INCORRECT_DATE_MESSAGE = "Niepoprawna data.";

    public static void validateEmail(String email) {
        String[] unacceptableChars = {"!", "#", "$", "%", "^", "&", "*", "(", ")", "+",
                "=", "[", "]", "{", "}", "~", "`", "\\", "|", ":", ";", "\"", "'", ",", "<", ">", "?", "/"};

        boolean validationBinary = true;
        boolean validateChars = validateUnacceptableCharsExisting(email,unacceptableChars);

        if(validateChars==false || !email.contains("@") || !email.contains(".")) {
            validationBinary = false;
        }
        if(validationBinary==false) {
            System.out.println(INCORRECT_EMAIL_MESSAGE);
        }

    }

    public static void validateStartDate(String startDate) {
        boolean validationBinary = validateDate(startDate);
        if(validationBinary==false) {
            System.out.println(INCORRECT_DATE_MESSAGE);
        }
    }

    public static void validateEndDate(String endDate) {
        boolean validationBinary = validateDate(endDate);




        if(validationBinary==false) {
            System.out.println(INCORRECT_DATE_MESSAGE);
        }
    }

    private static boolean validateUnacceptableCharsExisting (String validateInput, String[] unacceptableChars) {
        boolean validationBinary = true;

        for(String unacceptableChar : unacceptableChars) {
            if (validateInput.contains(unacceptableChar)) {
                validationBinary = false;
            }
        }

        return validationBinary;
    }

    private static boolean validateDate(String date) {
        boolean validationBinary = true;

        String datePattern = "([0-3][0-9])/([01][0-9])/([12][09][0-9][0-9])";

        Pattern compile = Pattern.compile(datePattern);
        Matcher matcher = compile.matcher(date);

        boolean matches = Pattern.matches(datePattern, date);
        if(matches==false) {
            validationBinary = false;
        }

        while (matcher.find()) {
            int day = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(2));
            int year = Integer.parseInt(matcher.group(3));

            validationBinary = validateDayMonthYear(day, month, year);

        }

        return validationBinary;
    }

    private static boolean validateDayMonthYear(int day, int month, int year) {
        boolean validationBinary = true;

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        if(day > 31 || day == 0 ||
                month > 12 || month == 0) {
            validationBinary = false;
        }

        return validationBinary;
    }

}
