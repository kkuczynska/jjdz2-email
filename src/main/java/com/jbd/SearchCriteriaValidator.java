package com.jbd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class SearchCriteriaValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchCriteriaValidator.class);
    private static final Marker SEARCHCRITERIAVALIDATOR_MARKER = MarkerFactory.getMarker("SearchCriteriaValidator");

    public static final int MAX_DAYS_IN_MONTH = 31;
    public static final int MAX_MONTHS_IN_YEAR = 12;
    public static final String DEFAULT_ENDDATE = "9999-12-12 00:00";
    public static final String DEFAULT_STARTDATE = "1111-01-01 00:00";

    private static final String DATE_PATTERN = "([12][09][0-9][0-9])-([01][0-9])-([0-3][0-9]) ([0-9]{2}):([0-9]{2})";
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");

    public static boolean validateEmail(String email) {
        boolean isEmailCorrect = true;
        boolean validateChars = validateUnacceptableCharsExisting(email);
        if (validateChars == false || !email.contains("@") || !email.contains(".")) {
            isEmailCorrect = false;
            LOGGER.debug(SEARCHCRITERIAVALIDATOR_MARKER, "Incorrect user input: " + email);
        }
        return isEmailCorrect;
    }

    public static boolean validateStartDate(String startDate) {
        boolean isStartdateCorrect = datePatternMatching(startDate);
        LOGGER.info(SEARCHCRITERIAVALIDATOR_MARKER, "Validation of start date: " + startDate + "outcome: " + isStartdateCorrect);
        return isStartdateCorrect;
    }

    public static boolean validateEndDate(String endDate) {
        boolean isEnddateCorrect = datePatternMatching(endDate);
         if (isEnddateCorrect == true) {
        LocalDate endDateFormatted = LocalDate.parse(endDate, dateFormatter);
        boolean isEndDateAfterStartDate = endDateFormatted
                .isAfter(LocalDate.parse(SearchCriteria.getStartDate(), dateFormatter));
        LOGGER.debug(SEARCHCRITERIAVALIDATOR_MARKER,
                "End date after start date validation, outcome: " + isEndDateAfterStartDate);

        if (isEndDateAfterStartDate == false) {
            isEnddateCorrect = false;
        }
          }
        LOGGER.info(SEARCHCRITERIAVALIDATOR_MARKER, "Validation of end date: " + endDate + " outcome: " + isEnddateCorrect);
        return isEnddateCorrect;
    }

    private static boolean validateUnacceptableCharsExisting(String validateInput) {
        String[] unacceptableChars = {"!", "#", "$", "%", "^", "&", "*", "(", ")", "+",
                "=", "[", "]", "{", "}", "~", "`", "\\", "|", ":", ";", "\"", "'", "<", ">", "?", "/"};
        boolean hasUnacceptableChars = true;
        for (String unacceptableChar : unacceptableChars) {
            if (validateInput.contains(unacceptableChar)) {
                hasUnacceptableChars = false;
                LOGGER.debug(SEARCHCRITERIAVALIDATOR_MARKER, "Input contains unacceptable characters: [" + unacceptableChar + "]");
            }
        }
        return hasUnacceptableChars;
    }

    private static boolean datePatternMatching(String date) {
        boolean hasCorrectPattern = true;
        if (!(DEFAULT_ENDDATE.equals(date) || DEFAULT_STARTDATE.equals(date))) {
            boolean matches = Pattern.matches(DATE_PATTERN, date);
            if (matches == false) {
                hasCorrectPattern = false;
                LOGGER.debug(SEARCHCRITERIAVALIDATOR_MARKER, "Date " + date + " does not match pattern: " + DATE_PATTERN);
            } else {
                LocalDate endDateFormatted = LocalDate.parse(date, dateFormatter);
                LOGGER.debug(SEARCHCRITERIAVALIDATOR_MARKER, "Date " + date + " does match pattern: " + DATE_PATTERN);
                hasCorrectPattern = validateDayMonthYear(endDateFormatted.getDayOfMonth(),
                        endDateFormatted.getMonthValue(), endDateFormatted.getYear());
            }
        }
        return hasCorrectPattern;
    }

    private static boolean validateDayMonthYear(int day, int month, long year) {
        boolean isDateCorrect = true;
        if (day > MAX_DAYS_IN_MONTH || day == 0 ||
                month > MAX_MONTHS_IN_YEAR || month == 0 ||
                year == 0) {
            isDateCorrect = false;
            LOGGER.debug(SEARCHCRITERIAVALIDATOR_MARKER, "Date is not correct: " + day + " " + month + " " + year);
        } else {
            LOGGER.debug(SEARCHCRITERIAVALIDATOR_MARKER, "Date is correct: " + day + " " + month + " " + year);
        }
        return isDateCorrect;
    }

}