package com.jbd.KeywordsFinder;

import com.jbd.servlets.SearchKeywordsServlet;
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

    public Map<String, String> createKeywordsMap() {
        Map<String, String> keywordsMap = new TreeMap<>();
        JsonReader jsonReader = new JsonReader();
        jsonReader.readQuestionJsonArray();
        LOGGER.info(MARKER, "Invoked readJsonArray.");
        for(String question : jsonReader.readQuestionJsonArray()) {
            keywordsMap.put(QUESTION + keywordsMap.size(), question);
            LOGGER.info(MARKER,
                    "Added keyword record: " + QUESTION + "" + keywordsMap.size() + ", question " + question);
        }
        return keywordsMap;
    }

}
