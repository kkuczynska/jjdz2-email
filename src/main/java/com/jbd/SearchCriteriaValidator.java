package com.jbd;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;

public class SearchCriteriaValidator {

    private static final String INCORRECT_EMAIL_MESSAGE = "Incorrect email address.";
    private static final String INCORRECT_DATE_MESSAGE = "Incorrect date.";

    public static final int MAX_DAYS_IN_MONTH = 31;
    public static final int MAX_MONTHS_IN_YEAR = 12;

    private static final String DATE_PATTERN = "([12][09][0-9][0-9])-([01][0-9])-([0-3][0-9])";

    private static UserCommunication userMessage = new UserCommunication();
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");

    public static boolean validateEmail(String email) {
        String[] unacceptableChars = {"!", "#", "$", "%", "^", "&", "*", "(", ")", "+",
                "=", "[", "]", "{", "}", "~", "`", "\\", "|", ":", ";", "\"", "'", ",", "<", ">", "?", "/"};

        boolean isEmailCorrect = true;
        boolean validateChars = validateUnacceptableCharsExisting(email,unacceptableChars);

        if(validateChars==false || !email.contains("@") || !email.contains(".")) {
            isEmailCorrect = false;
        }
        if(isEmailCorrect==false) {
            userMessage.sendUserMessage(INCORRECT_EMAIL_MESSAGE);
        }
        return isEmailCorrect;
    }

    public static boolean validateStartDate(String startDate) {
        boolean isStartdateCorrect = datePatternMatching(startDate.toString());
        if(isStartdateCorrect==false) {
            userMessage.sendUserMessage(INCORRECT_DATE_MESSAGE);
        }
        return isStartdateCorrect;
    }

    public static boolean validateEndDate(String endDate) {
        boolean isEnddateCorrect = datePatternMatching(endDate);

        LocalDate endDateFormatted = LocalDate.parse(endDate, dateFormatter);
        boolean isEndDateAfterStartDate = endDateFormatted.isAfter(LocalDate.parse(SearchCriteria.getSTARTDATE(), dateFormatter));
        if(isEndDateAfterStartDate==false) {
            isEnddateCorrect = false;
        }

        if (isEnddateCorrect == false) {
            userMessage.sendUserMessage(INCORRECT_DATE_MESSAGE);
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