package com.jbd.KeywordsFinder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.ejb.Stateless;
import java.util.Map;
import java.util.TreeMap;

@Stateless
public class KeywordsQuestionsMap {

    private static final Logger LOGGER = LoggerFactory.getLogger(KeywordsQuestionsMap.class);
    private static final Marker MARKER = MarkerFactory.getMarker("KeywordsQuestionsMap");

    public static final String QUESTION = "q";
    Map<String, String> questionsMap;

    public Map<String, String> createQuestionsMap() {
        questionsMap = new TreeMap<>();
        JsonReader jsonReader = new JsonReader();
        jsonReader.readQuestionJsonArray();
        LOGGER.info(MARKER, "Invoked questions map.");
        for (String question : jsonReader.readQuestionJsonArray()) {
            questionsMap.put(QUESTION + "" + questionsMap.size(), question);
            LOGGER.info(MARKER,
                    "Added question record: " + QUESTION + "" + questionsMap.size() + ", question: " + question);
        }
        return questionsMap;
    }


    public Map<String, String> getQuestionsMap() {
        return questionsMap;
    }
}
