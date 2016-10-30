package com.jbd.KeywordsFinder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;


public class JsonReader {
    private static String urgentEmailQuestion;
    private static String businessEmailQuestion;
    private static String familyEmailQuestion;
    private static String meetingEmailQuestion;

    private static ArrayList<String> emailUrgent;
    private static ArrayList<String> emailNotUrgent;
    private static ArrayList<String> emailPrivate;
    private static ArrayList<String> emailNotPrivate;
    private static ArrayList<String> emailAboutFamily;
    private static ArrayList<String> emailAboutMeeting;

    private static final String EMAIL_URGENT = "emailUrgent";
    private static final String EMAIL_NOT_URGENT = "emailNotUrgent";
    private static final String EMAIL_BUSINESS = "emailBusiness";
    private static final String EMAIL_NOT_BUSINESS = "emailNotBusiness";
    private static final String EMAIL_ABOUT_FAMILY = "emailAboutFamily";
    private static final String EMAIL_ABOUT_MEETING = "emailAboutMeeting";

    private static final String jsonPath = "src/main/resources/KeywordsForm.json";

    private static final int urgentEmailQuestionID = 0;
    private static final int businessQuestionID = 1;
    private static final int familyQuestionID = 2;
    private static final int meetingQuestionID = 3;


    public static ArrayList<String> getEmailUrgent() {
        try {
            emailUrgent = readAnswerJsonArray(EMAIL_URGENT);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return emailUrgent;
    }

    public static ArrayList<String> getEmailNotUrgent() {
        try {
            emailNotUrgent = readAnswerJsonArray(EMAIL_NOT_URGENT);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return emailNotUrgent;
    }

    public static ArrayList<String> getEmailBusiness() {
        try {
            emailPrivate = readAnswerJsonArray(EMAIL_BUSINESS);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return emailPrivate;
    }

    public static ArrayList<String> getEmailNotBusiness() {
        try {
            emailNotPrivate = readAnswerJsonArray(EMAIL_NOT_BUSINESS);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return emailNotPrivate;
    }

    public static ArrayList<String> getEmailAboutFamily() {
        try {
            emailAboutFamily = readAnswerJsonArray(EMAIL_ABOUT_FAMILY);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return emailAboutFamily;
    }

    public static ArrayList<String> getEmailAboutMeeting() {
        try {
            emailAboutMeeting = readAnswerJsonArray(EMAIL_ABOUT_MEETING);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return emailAboutMeeting;
    }

    public static String getUrgentEmailQuestion() {
        try {
            urgentEmailQuestion = readQuestionJsonArray(urgentEmailQuestionID);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return urgentEmailQuestion;
    }

    public static String getBusinessEmailQuestion() {
        businessEmailQuestion = readQuestionJsonArray(businessQuestionID);
        return businessEmailQuestion;
    }

    public static String getFarmilyEmailQuestion() {
        familyEmailQuestion = readQuestionJsonArray(familyQuestionID);
        return familyEmailQuestion;
    }

    public static String getMeetingEmailQuestion() {
        meetingEmailQuestion = readQuestionJsonArray(meetingQuestionID);
        return meetingEmailQuestion;
    }

    public static ArrayList<String> readAnswerJsonArray(String jsonNodeName) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(jsonPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) jsonParser.parse(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = (JSONArray) jsonObject.get(jsonNodeName);

        return jsonArray;
    }

    public static String readQuestionJsonArray(int questionListElement) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(jsonPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) jsonParser.parse(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = (JSONArray) jsonObject.get("questions");
        return (String) jsonArray.get(questionListElement);
    }

}
