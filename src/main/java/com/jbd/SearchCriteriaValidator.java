package com.jbd;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchCriteriaValidator {

    private static final String INCORRECT_EMAIL_MESSAGE = "Niepoprawny adres email.";
    private static final String INCORRECT_DATE_MESSAGE = "Niepoprawna data.";

    public static final int MAX_DAYS_IN_MONTH = 31;
    public static final int MAX_MONTHS_IN_YEAR = 12;

    private static final String DATE_PATTERN = "([0-3][0-9])/([01][0-9])/([12][09][0-9][0-9])";
    public static Pattern compile = Pattern.compile(DATE_PATTERN);

    private static UserCommunication userMessage = new UserCommunication();

    public void validateEmail(String email) {
        String[] unacceptableChars = {"!", "#", "$", "%", "^", "&", "*", "(", ")", "+",
                "=", "[", "]", "{", "}", "~", "`", "\\", "|", ":", ";", "\"", "'", ",", "<", ">", "?", "/"};

        boolean validationFlag = true;
        boolean validateChars = validateUnacceptableCharsExisting(email,unacceptableChars);

        if(validateChars==false || !email.contains("@") || !email.contains(".")) {
            validationFlag = false;
        }
        if(validationFlag==false) {
            userMessage.sendUserMessage(INCORRECT_EMAIL_MESSAGE);
        }

    }

    public void validateStartDate(String startDate) {
        boolean validationFlag = datePatternMatching(startDate);
        if(validationFlag==false) {
            userMessage.sendUserMessage(INCORRECT_DATE_MESSAGE);
        }
    }

    public void validateEndDate(String endDate) {
        boolean validationFlag = datePatternMatching(endDate);

        if(validationFlag==true) {
            SearchCriteriaValidator dateGrouping = new SearchCriteriaValidator();

            List<Integer> endDateList = dateGrouping.datePatternGrouping(endDate);
            List<Integer> startDateList = dateGrouping.datePatternGrouping(SearchCriteria.getSTARTDATE());

            long startYear = dateGrouping.datePatternGroupingYear(SearchCriteria.getSTARTDATE());
            long endYear = dateGrouping.datePatternGroupingYear(endDate);

            int startMonth = startDateList.get(1);
            int endMonth = endDateList.get(1);
            int startDay = startDateList.get(0);
            int endDay = endDateList.get(0);

            if (startYear > endYear) {
                validationFlag = false;
            } else if (startYear == endYear
                    && startMonth > endMonth) {
                validationFlag = false;
            } else if (startYear == endYear
                    && startMonth == endMonth
                    && startDay > endDay) {
                validationFlag = false;
            }
        }

        if (validationFlag == false) {
            userMessage.sendUserMessage(INCORRECT_DATE_MESSAGE);
        }
    }

    private static boolean validateUnacceptableCharsExisting (String validateInput, String[] unacceptableChars) {
        boolean validationFlag = true;

        for(String unacceptableChar : unacceptableChars) {
            if (validateInput.contains(unacceptableChar)) {
                validationFlag = false;
            }
        }

        return validationFlag;
    }

    private static boolean datePatternMatching(String date) {
        boolean validationFlag = true;

        SearchCriteriaValidator dateGrouping = new SearchCriteriaValidator();
        Matcher matcher = compile.matcher(date);

        boolean matches = Pattern.matches(DATE_PATTERN, date);
        if(matches==false) {
            validationFlag = false;
        } else {
            List<Integer> dateList = dateGrouping.datePatternGrouping(date);
            long year = dateGrouping.datePatternGroupingYear(date);
            validationFlag = validateDayMonthYear(dateList.get(0), dateList.get(1), year);
        }
        return validationFlag;
    }

    private List<Integer> datePatternGrouping(String date) {
        List<Integer> dateGrouped = new ArrayList<>();

        Matcher matcher = compile.matcher(date);

        while (matcher.find()) {
            int day = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(2));

            dateGrouped.add(day);
            dateGrouped.add(month);
        }

        return dateGrouped;
    }

    private long datePatternGroupingYear(String date) {
        long dateYear = 0000;

        Matcher matcher = compile.matcher(date);

        while (matcher.find()) {
            dateYear = Integer.parseInt(matcher.group(3));
        }

        return dateYear;
    }

    private static boolean validateDayMonthYear(int day, int month, long year) {
        boolean validationFlag = true;

        if(day > MAX_DAYS_IN_MONTH || day == 0 ||
                month > MAX_MONTHS_IN_YEAR || month == 0 ||
                year == 0) {
            validationFlag = false;
        }

        return validationFlag;
    }

}
