package com.jbd;

import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class SearchCriteria {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchCriteria.class);
    private static final Marker SEARCHCRITERIA_MARKER = MarkerFactory.getMarker("SearchCriteria");

    private static String email;
    private static String startDate;
    private static String endDate;
    private static String keywords;
    private static final String TIME = "00:00";
    private static ArrayList<String> emailAdresses = new ArrayList<>();

    private static SearchCriteriaValidator searchCriteriaValidator = new SearchCriteriaValidator();

    public static ArrayList<String> getEmail() {
        return emailAdresses;
    }

    public static void setEmail(String email) {
        emailAdresses = new ArrayList<>();
        searchCriteriaValidator.validateEmail(email);
        SearchCriteria.email = email;
        emailAdresses.addAll(searchCriteriaCommaParser(email));
        LOGGER.info(SEARCHCRITERIA_MARKER, "Element: " + email + " added to the emails list.");
    }

    public static String getStartDate() {
        return startDate + " " + TIME;
    }

    public static void setStartDate(String startDate) {
        if ("".equals(startDate)) {
            startDate = "1111-01-01";
        }
        LOGGER.info(SEARCHCRITERIA_MARKER, "Start date has been set: " + startDate);
        SearchCriteria.startDate = startDate;
        searchCriteriaValidator.validateStartDate(startDate);
    }

    public static String getEndDate() {
        return endDate + " " + TIME;
    }

    public static void setEndDate(String endDate) {
        if ("".equals(endDate)) {
            endDate = "9999-12-12";
        }
        SearchCriteria.endDate = endDate;
        LOGGER.info(SEARCHCRITERIA_MARKER, "End date has been set: " + endDate);
        searchCriteriaValidator.validateEndDate(endDate);
    }

    public static List<String> getKeywords() {
        LOGGER.info(SEARCHCRITERIA_MARKER, "Keywords passed parsing method");
        List<String> keywordsParsed = searchCriteriaCommaParser(keywords);
        return keywordsParsed;
    }

    public static void setKeywords(String keywords) {
        SearchCriteria.keywords = keywords;
        LOGGER.info(SEARCHCRITERIA_MARKER, "Keywords has been set: " + keywords);
    }

    private static List<String> searchCriteriaCommaParser(String stringToParse) {
        List<String> parsedList = new ArrayList<>();

        stringToParse = stringToParse.replaceAll(", ", ",");
        LOGGER.info(SEARCHCRITERIA_MARKER, "Parsing: replaced all \", \" with single comma.");
        stringToParse = stringToParse.replaceAll(" ,", ",");
        LOGGER.info(SEARCHCRITERIA_MARKER, "Parsing: replaced all \" ,\" with single comma.");

        String splitBy = ",";
        String[] keywordsParsing = stringToParse.split(splitBy);
        LOGGER.info(SEARCHCRITERIA_MARKER, "Parsing: input string: " + stringToParse + " parsed by comma: [" + splitBy + "]");

        for (String stringElement : keywordsParsing) {
            parsedList.add(stringElement);
            LOGGER.info(SEARCHCRITERIA_MARKER, "Parsing: parsed element added to the list: " + stringElement);
        }
        return parsedList;
    }

    public String dateToDisplayInFrontEnd(String date) {
        String dateParsed = "";

        if(!(SearchCriteriaValidator.DEFAULT_STARTDATE.equalsIgnoreCase(date) ||
                SearchCriteriaValidator.DEFAULT_ENDDATE.equalsIgnoreCase(date))) {
            dateParsed = date.substring(0, 10);
        }

        return dateParsed;
    }
}
