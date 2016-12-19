package com.jbd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchCriteria {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchCriteria.class);
    private static final Marker SEARCHCRITERIA_MARKER = MarkerFactory.getMarker("SearchCriteria");

    private static String EMAIL;
    private static String STARTDATE;
    private static String ENDDATE;
    private static String KEYWORDS;
    private static final String TIME = " 00:00";
    private static ArrayList<String> emailAdresses = new ArrayList<>();

    private static SearchCriteriaValidator searchCriteriaValidator = new SearchCriteriaValidator();

    public static ArrayList<String> getEMAIL() {
        return emailAdresses;
    }

    public static void setEMAIL(String EMAIL) {
        searchCriteriaValidator.validateEmail(EMAIL);
        SearchCriteria.EMAIL = EMAIL;
        emailAdresses.add(EMAIL);
        LOGGER.info(SEARCHCRITERIA_MARKER, "Element: " + EMAIL + " added to the emails list.");
    }

    public static String getSTARTDATE() {
        return STARTDATE + TIME;
    }

    public static void setSTARTDATE(String STARTDATE) {
        LOGGER.info(SEARCHCRITERIA_MARKER, "Start date has been set: " + STARTDATE);
        SearchCriteria.STARTDATE = STARTDATE;
        searchCriteriaValidator.validateStartDate(STARTDATE);
    }

    public static String getENDDATE() {
        return ENDDATE + TIME;
    }

    public static void setENDDATE(String ENDDATE) {
        LOGGER.info(SEARCHCRITERIA_MARKER, "End date has been set: " + ENDDATE);
        SearchCriteria.ENDDATE = ENDDATE;
        searchCriteriaValidator.validateEndDate(ENDDATE);
    }

    public static List<String> getKEYWORDS() {
        LOGGER.info(SEARCHCRITERIA_MARKER, "Keywords passed parsing method");
        List<String> keywordsParsed = searchCriteriaCommaParser(KEYWORDS);
        return keywordsParsed;
    }

    public static void setKEYWORDS(String KEYWORDS) {
        LOGGER.info(SEARCHCRITERIA_MARKER, "Keywords has been set: " + KEYWORDS);
        SearchCriteria.KEYWORDS = KEYWORDS;
    }

    private static List<String> searchCriteriaCommaParser(String stringToParse) {
        List<String> parsedList = new ArrayList<>();

        stringToParse = stringToParse.replaceAll(", ",",");
        LOGGER.info(SEARCHCRITERIA_MARKER, "Parsing: replaced all \", \" with single comma.");
        stringToParse = stringToParse.replaceAll(" ,",",");
        LOGGER.info(SEARCHCRITERIA_MARKER, "Parsing: replaced all \" ,\" with single comma.");

        String splitBy = ",";
        String[] keywordsParsing = stringToParse.split(splitBy);
        LOGGER.info(SEARCHCRITERIA_MARKER, "Parsing: input string: " + stringToParse + " parsed by comma: [" + splitBy + "]");

        for (String stringElement: keywordsParsing) {
            parsedList.add(stringElement);
            LOGGER.info(SEARCHCRITERIA_MARKER, "Parsing: parsed element added to the list: " + stringElement);
        }

        return parsedList;
    }

}
