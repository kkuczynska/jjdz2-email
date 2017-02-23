package com.jbd.searchKeywords;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.ejb.Stateless;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class JsonReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonReader.class);
    private static final Marker JSONREADER_MARKER = MarkerFactory.getMarker("JsonReader");

    private static final String jsonPath = "src/main/resources/KeywordsForm.json";
    private String QUESTIONS_ARRAY_NAME = "questions";

    public ArrayList<String> readAnswerJsonArray(String jsonNodeName) {
        FileReader fileReader = null;
        LOGGER.info(JSONREADER_MARKER, "Reading keywords from file: " + jsonPath);
        try {
            fileReader = new FileReader(jsonPath);
        } catch (FileNotFoundException e) {
            LOGGER.error(JSONREADER_MARKER, "Json file not found in directory: " + jsonPath);
            e.printStackTrace();
        }
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        LOGGER.info(JSONREADER_MARKER, "Parsing Json file: " + jsonPath);
        try {
            jsonObject = (JSONObject) jsonParser.parse(fileReader);
        } catch (IOException e) {
            LOGGER.debug(JSONREADER_MARKER, "IOException thrown while parsing file: " + jsonPath);
            e.printStackTrace();
        } catch (ParseException e) {
            LOGGER.debug(JSONREADER_MARKER, "ParseException thrown while parsing file: " + jsonPath);
            e.printStackTrace();
        }
        LOGGER.info(JSONREADER_MARKER, "Creating JsonArray from file: " + jsonPath);
        JSONArray jsonArray = (JSONArray) jsonObject.get(jsonNodeName);

        return jsonArray;
    }

    public List<String> readQuestionJsonArray() {
        FileReader fileReader = null;
        LOGGER.info(JSONREADER_MARKER, "Reading questions from file: " + jsonPath);
        try {
            fileReader = new FileReader(jsonPath);
        } catch (FileNotFoundException e) {
            LOGGER.error(JSONREADER_MARKER, "Json file not found in directory: " + jsonPath);
            e.printStackTrace();
        }
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        LOGGER.info(JSONREADER_MARKER, "Parsing Json file: " + jsonPath);
        try {
            jsonObject = (JSONObject) jsonParser.parse(fileReader);
        } catch (IOException e) {
            LOGGER.debug(JSONREADER_MARKER, "IOException thrown while parsing file: " + jsonPath);
            e.printStackTrace();
        } catch (ParseException e) {
            LOGGER.debug(JSONREADER_MARKER, "ParseException thrown while parsing file: " + jsonPath);
            e.printStackTrace();
        }
        JSONArray jsonArray = (JSONArray) jsonObject.get(QUESTIONS_ARRAY_NAME);
        LOGGER.info(JSONREADER_MARKER, "Creating jsonArray from file: " + jsonPath + " found array name: "
                + QUESTIONS_ARRAY_NAME);

        return (ArrayList<String>) jsonArray;
    }
}
