package com.jbd;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;

public class SearchCriteriaValidator {

    public static final int MAX_DAYS_IN_MONTH = 31;
    public static final int MAX_MONTHS_IN_YEAR = 12;

    private static final String DATE_PATTERN = "([12][09][0-9][0-9])-([01][0-9])-([0-3][0-9])";

    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static boolean validateEmail(String email) {
        String[] unacceptableChars = {"!", "#", "$", "%", "^", "&", "*", "(", ")", "+",
                "=", "[", "]", "{", "}", "~", "`", "\\", "|", ":", ";", "\"", "'", ",", "<", ">", "?", "/"};

        boolean isEmailCorrect = true;
        boolean validateChars = validateUnacceptableCharsExisting(email,unacceptableChars);

        if(validateChars==false || !email.contains("@") || !email.contains(".")) {
            isEmailCorrect = false;
        }
        return isEmailCorrect;
    }

    public static boolean validateStartDate(String startDate) {
        boolean isStartdateCorrect = datePatternMatching(startDate);
        return isStartdateCorrect;
    }

    public static boolean validateEndDate(String endDate) {
        boolean isEnddateCorrect = datePatternMatching(endDate);

        if(isEnddateCorrect==true) {
            LocalDate endDateFormatted = LocalDate.parse(endDate, dateFormatter);
            boolean isEndDateTheSameAsStartDate = endDateFormatted
                    .isEqual(LocalDate.parse(SearchCriteria.getSTARTDATE(), dateFormatter));
            boolean isEndDateAfterStartDate = endDateFormatted
                    .isAfter(LocalDate.parse(SearchCriteria.getSTARTDATE(), dateFormatter));

            if(isEndDateTheSameAsStartDate==true) {
                isEnddateCorrect = true;
            } else if (isEndDateAfterStartDate == false) {
                isEnddateCorrect = false;
            }
        }

        return isEnddateCorrect;
    }

    private static boolean validateUnacceptableCharsExisting (String validateInput, String[] unacceptableChars) {
        boolean hasUnacceptableChars = true;

        for(String unacceptableChar : unacceptableChars) {
            if (validateInput.contains(unacceptableChar)) {
                hasUnacceptableChars = false;
            }
        }

        return hasUnacceptableChars;
    }

    private static boolean datePatternMatching(String date) {
        boolean hasCorrectPattern;

        boolean matches = Pattern.matches(DATE_PATTERN, date);
        if(matches==false) {
            hasCorrectPattern = false;
        } else {
            LocalDate endDateFormatted = LocalDate.parse(date, dateFormatter);
            hasCorrectPattern = validateDayMonthYear(endDateFormatted.getDayOfMonth(), endDateFormatted.getMonthValue(), endDateFormatted.getYear());
        }
        return hasCorrectPattern;
    }

    private static boolean validateDayMonthYear(int day, int month, long year) {
        boolean isDateCorrect = true;

        if(day > MAX_DAYS_IN_MONTH || day == 0 ||
                month > MAX_MONTHS_IN_YEAR || month == 0 ||
                year == 0) {
            isDateCorrect = false;
        }

        return isDateCorrect;
    }

}