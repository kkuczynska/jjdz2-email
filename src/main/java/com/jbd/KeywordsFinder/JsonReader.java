package com.jbd.KeywordsFinder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.ejb.Stateless;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class JsonReader {

    private static final String jsonPath = "src/main/resources/KeywordsForm.json";
    private String QUESTIONS_ARRAY_NAME = "questions";

    public ArrayList<String> readAnswerJsonArray(String jsonNodeName) {
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

    public List<String> readQuestionJsonArray() {
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
        JSONArray jsonArray = (JSONArray) jsonObject.get(QUESTIONS_ARRAY_NAME);
        return (ArrayList<String>) jsonArray;
    }

}
