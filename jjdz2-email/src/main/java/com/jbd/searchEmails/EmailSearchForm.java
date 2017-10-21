package com.jbd.searchEmails;

import com.jbd.SearchCriteriaValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class EmailSearchForm {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailSearchForm.class);
    private static final Marker MARKER = MarkerFactory.getMarker("EmailSearchForm");

    private static String email;
    private static String startDate;
    private static String endDate;
    private static String keywords;
    private static final String TIME = " 00:00";
    private static ArrayList<String> emailAdresses = new ArrayList<>();

    private static SearchCriteriaValidator searchCriteriaValidator = new SearchCriteriaValidator();

    public static ArrayList<String> getEmail() {
        return emailAdresses;
    }

    public static void setEmail(String email) {
        emailAdresses = new ArrayList<>();
        searchCriteriaValidator.validateEmail(email);
        EmailSearchForm.email = email;
        emailAdresses.addAll(searchCriteriaCommaParser(email));
        LOGGER.info(MARKER, "Element: " + email + " added to the emails list.");
    }

    public static String getStartDate() {
        return startDate;
    }

    public static void setStartDate(String startDate) {
        if ("".equals(startDate)) {
            startDate = SearchCriteriaValidator.DEFAULT_STARTDATE;
        } else {
            startDate = startDate + TIME;
        }
        LOGGER.info(MARKER, "Start date has been set: " + startDate);
        EmailSearchForm.startDate = startDate;
        //searchCriteriaValidator.validateStartDate(startDate);
    }

    public static String getEndDate() {
        return endDate;
    }

    public static void setEndDate(String endDate) {
        if ("".equals(endDate)) {
            endDate = SearchCriteriaValidator.DEFAULT_ENDDATE;
        } else {
            endDate = endDate + TIME;
        }
        EmailSearchForm.endDate = endDate;
        LOGGER.info(MARKER, "End date has been set: " + endDate);
        //  searchCriteriaValidator.validateEndDate(endDate);
    }

    public static List<String> getKeywords() {
        LOGGER.info(MARKER, "Keywords passed parsing method");
        return searchCriteriaCommaParser(keywords);
    }

    public static void setKeywords(String keywords) {
        EmailSearchForm.keywords = keywords;
        LOGGER.info(MARKER, "Keywords has been set: " + keywords);
    }

    private static List<String> searchCriteriaCommaParser(String stringToParse) {
        List<String> parsedList = new ArrayList<>();

        stringToParse = stringToParse.replaceAll(", ", ",");
        LOGGER.info(MARKER, "Parsing: replaced all \", \" with single comma.");
        stringToParse = stringToParse.replaceAll(" ,", ",");
        LOGGER.info(MARKER, "Parsing: replaced all \" ,\" with single comma.");

        String splitBy = ",";
        String[] keywordsParsing = stringToParse.split(splitBy);
        LOGGER.info(MARKER, "Parsing: input string: " + stringToParse + " parsed by comma: [" + splitBy + "]");

        for (String stringElement : keywordsParsing) {
            parsedList.add(stringElement);
            LOGGER.info(MARKER, "Parsing: parsed element added to the list: " + stringElement);
        }
        return parsedList;
    }

    public String dateToDisplayInFrontEnd(String date) {
        String dateParsed = "";

        if (!(SearchCriteriaValidator.DEFAULT_STARTDATE.equalsIgnoreCase(date) ||
                SearchCriteriaValidator.DEFAULT_ENDDATE.equalsIgnoreCase(date))) {
            dateParsed = date.substring(0, 10);
        }

        return dateParsed;
    }
}
