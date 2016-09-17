package com.jbd;

import java.util.List;

public class SearchCriteria {

    private static String EMAIL;
    private static String STARTDATE;
    private static String ENDDATE;
    private static List<String> KEYWORDS;


    public static String getEMAIL() {
        return EMAIL;
    }

    public static void setEMAIL(String EMAIL) {
        SearchCriteria.EMAIL = EMAIL;
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
        return KEYWORDS;
    }

    public static void setKEYWORDS(List<String> KEYWORDS) {
        SearchCriteria.KEYWORDS = KEYWORDS;
    }

}
