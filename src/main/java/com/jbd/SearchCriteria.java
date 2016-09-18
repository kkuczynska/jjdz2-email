package com.jbd;

import java.util.ArrayList;
import java.util.List;

public class SearchCriteria {

    private static String EMAIL;
    private static String STARTDATE;
    private static String ENDDATE;
    private static String KEYWORDS;

    SearchCriteriaValidator searchCriteriaValidator = new SearchCriteriaValidator();

    public static String getEMAIL() {
        return EMAIL;
    }

    public static void setEMAIL(String EMAIL) {
        SearchCriteria.EMAIL = EMAIL;
        SearchCriteriaValidator.validateEmail(EMAIL);
    }

    public static String getSTARTDATE() {
        return STARTDATE;
    }

    public static void setSTARTDATE(String STARTDATE) {
        SearchCriteria.STARTDATE = STARTDATE;
    }

    public static String getENDDATE() {
        return ENDDATE;
    }

    public static void setENDDATE(String ENDDATE) {
        SearchCriteria.ENDDATE = ENDDATE;
    }

    public static List<String> getKEYWORDS() {
        List<String> keywordsParsed = keywordsParser(KEYWORDS);
        return keywordsParsed;
    }

    public static void setKEYWORDS(String KEYWORDS) {
        SearchCriteria.KEYWORDS = KEYWORDS;
    }

    private static List<String> keywordsParser(String keywords) {
        List<String> keywordsParsed = new ArrayList<>();

        String splitBy = ",";
        String[] keywordsParsing = keywords.split(splitBy);

        for (String stringElement: keywordsParsing) {
            keywordsParsed.add(stringElement);
        }

        return keywordsParsed;
    }

}
