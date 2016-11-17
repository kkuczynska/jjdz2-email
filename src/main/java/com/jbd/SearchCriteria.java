package com.jbd;

import java.util.ArrayList;
import java.util.List;

public class SearchCriteria {

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
    }

    public static String getSTARTDATE() {
        return STARTDATE + TIME;
    }

    public static void setSTARTDATE(String STARTDATE) {
        SearchCriteria.STARTDATE = STARTDATE;
        searchCriteriaValidator.validateStartDate(STARTDATE);
    }

    public static String getENDDATE() {
        return ENDDATE + TIME;
    }

    public static void setENDDATE(String ENDDATE) {
        SearchCriteria.ENDDATE = ENDDATE;
        //searchCriteriaValidator.validateEndDate(ENDDATE); To Improve!!
    }

    public static List<String> getKEYWORDS() {
        List<String> keywordsParsed = searchCriteriaCommaParser(KEYWORDS);
        return keywordsParsed;
    }

    public static void setKEYWORDS(String KEYWORDS) {
        SearchCriteria.KEYWORDS = KEYWORDS;
    }

    private static List<String> searchCriteriaCommaParser(String stringToParse) {
        List<String> parsedList = new ArrayList<>();

        stringToParse = stringToParse.replaceAll(", ",",");
        stringToParse = stringToParse.replaceAll(" ,",",");

        String splitBy = ",";
        String[] keywordsParsing = stringToParse.split(splitBy);

        for (String stringElement: keywordsParsing) {
            parsedList.add(stringElement);
        }

        return parsedList;
    }

}
