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

    private static final String DATE_PATTERN = "([0-3][0-9])/([01][0-9])/([12][09][0-9][0-9])";

    private static UserCommunication userMessage = new UserCommunication();
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static boolean validateEmail(String email) {
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
        return validationFlag;
    }

    public static boolean validateStartDate(String startDate) {
        boolean validationFlag = datePatternMatching(startDate.toString());
        if(validationFlag==false) {
            userMessage.sendUserMessage(INCORRECT_DATE_MESSAGE);
        }
        return validationFlag;
    }

    public static void validateEndDate(String endDate) {
        boolean validationFlag = datePatternMatching(endDate);

        LocalDate endDateFormatted = LocalDate.parse(endDate, dateFormatter);
        boolean isEndDateAfterStartDate = endDateFormatted.isAfter(LocalDate.parse(SearchCriteria.getSTARTDATE(), dateFormatter));
        if(isEndDateAfterStartDate==false) {
            validationFlag = false;
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
        boolean validationFlag;

        boolean matches = Pattern.matches(DATE_PATTERN, date);
        if(matches==false) {
            validationFlag = false;
        } else {
            LocalDate endDateFormatted = LocalDate.parse(date, dateFormatter);
            validationFlag = validateDayMonthYear(endDateFormatted.getDayOfMonth(), endDateFormatted.getMonthValue(), endDateFormatted.getYear());
        }
        return validationFlag;
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