package com.jbd;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MakeEmailsFromString {
    private List<Email> emailsFromString = new ArrayList<>();
    private String EMAILREGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private String FROMREGEX = "(from\\s)("+EMAILREGEX+")";
    private String SUBJECTREGEX = "(subject:\\s)(.*)";
    private String DATEREGEX = "(\\ndate:\\s)(.*)";
    //private String CONTENTREGEX = "(x-list.*|list.*)(\\n\\n)(?s)(.*)(\\n\\n\\n)";//(?s)<yolo>(.*?)</yolo>

    public List<Email> makeEmailList(String fromString){
        Matcher fromMatcher = getMatcherFor(FROMREGEX, fromString);
        Matcher subjectMatcher = getMatcherFor(SUBJECTREGEX, fromString);
        Matcher dateMatcher = getMatcherFor(DATEREGEX, fromString);
        //Matcher contentMatcher = getMatcherFor(CONTENTREGEX, fromString);

        while(fromMatcher.find() && subjectMatcher.find() && dateMatcher.find()){
            Email e = new Email(fromMatcher.group(2),subjectMatcher.group(2),dateMatcher.group(2), "add contentMatcher when ready");
            emailsFromString.add(e);
        }

        return emailsFromString;
    }

    private Matcher getMatcherFor(String fromregex, String fromString) {
        Pattern pattern = Pattern.compile(fromregex, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(fromString);
    }


}