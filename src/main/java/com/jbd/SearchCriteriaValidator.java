package com.jbd;


import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchCriteriaValidator {

    public static void validateEmail(String email) {
        String[] unacceptableChars = {"!", "#", "$", "%", "^", "&", "*", "(", ")", "+",
                "=", "[", "]", "{", "}", "~", "`", "\\", "|", ":", ";", "\"", "'", ",", "<", ">", "?", "/"};

        int validationBinary = 0;

        if(!email.contains("@") || !email.contains(".")) {
            validationBinary = 1;
        }

        validateUnacceptableCharsExisting(email,unacceptableChars);

        if(validationBinary==1) {
            System.out.println("Podany adres jest niepoprawny.");
        }
    }

    public static void validateStartDate(String startDate) {
        validateDate(startDate);
    }

    private static int validateUnacceptableCharsExisting (String validateInput, String[] unacceptableChars) {
        int validationBinary = 0;

        for(String unacceptableChar : unacceptableChars) {
            if (validateInput.contains(unacceptableChar)) {
                validationBinary = 1;
            }
        }

        return validationBinary;
    }

    private static void validateDate(String date) {
        int validateBinary = 0;

        String datePattern = "([0-3][0-9])/([01][0-9])/([12][09][0-9][0-9])";

        Pattern compile = Pattern.compile(datePattern);
        Matcher matcher = compile.matcher(date);

        boolean matches = Pattern.matches(datePattern, date);
        if(matches==false) {
            validateBinary = 1;
        }

        while (matcher.find()) {
            int day = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(2));
            int year = Integer.parseInt(matcher.group(3));

            validateBinary = validateDayMonthYear(day, month, year);

        }

        if(validateBinary==1) {
            System.out.println("Błędna data.");
        }
    }

    private static int validateDayMonthYear(int day, int month, int year) {
        int validationBinary = 0;

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        if(day > 31 || day == 0 ||
                month > 12 || month == 0) {
            validationBinary = 1;
        }

        return validationBinary;
    }

}
