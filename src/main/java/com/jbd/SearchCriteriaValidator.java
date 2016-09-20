package com.jbd;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchCriteriaValidator {

    private static final String INCORRECT_EMAIL_MESSAGE = "Niepoprawny adres email.";
    private static final String INCORRECT_DATE_MESSAGE = "Niepoprawna data.";

    private static final String DATE_PATTERN = "([0-3][0-9])/([01][0-9])/([12][09][0-9][0-9])";
    public static Pattern compile = Pattern.compile(DATE_PATTERN);

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
        boolean validationBinary = datePatternMatching(startDate);
        if(validationBinary==false) {
            System.out.println(INCORRECT_DATE_MESSAGE);
        }
    }

    public static void validateEndDate(String endDate) {
        boolean validationBinary = datePatternMatching(endDate);
        SearchCriteriaValidator dateGrouping = new SearchCriteriaValidator();

        List<Integer> endDateList = dateGrouping.datePatternGrouping(endDate);
        List<Integer> startDateList = dateGrouping.datePatternGrouping(SearchCriteria.getSTARTDATE());


        if(startDateList.get(2) > endDateList.get(2) ) {
            validationBinary = false;
        } else if(startDateList.get(1) > endDateList.get(1)) {
            validationBinary = false;
        } else if(startDateList.get(0) > endDateList.get(0)) {
            validationBinary = false;
        }


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

    private static boolean datePatternMatching(String date) {
        boolean validationBinary = true;

        SearchCriteriaValidator dateGrouping = new SearchCriteriaValidator();
        Matcher matcher = compile.matcher(date);

        boolean matches = Pattern.matches(DATE_PATTERN, date);
        if(matches==false) {
            validationBinary = false;
        }
        List<Integer> dateList = dateGrouping.datePatternGrouping(date);
        validationBinary = validateDayMonthYear(dateList.get(0), dateList.get(1), dateList.get(2));

        return validationBinary;
    }

    public List<Integer> datePatternGrouping(String date) {
        List<Integer> dateGrouped = new ArrayList<>();

        Matcher matcher = compile.matcher(date);

        while (matcher.find()) {
            int day = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(2));
            int year = Integer.parseInt(matcher.group(3));

            dateGrouped.add(day);
            dateGrouped.add(month);
            dateGrouped.add(year);
        }

        return dateGrouped;
    }

    private static boolean validateDayMonthYear(int day, int month, int year) {
        boolean validationBinary = true;

        if(day > 31 || day == 0 ||
                month > 12 || month == 0) {
            validationBinary = false;
        }

        return validationBinary;
    }

}
