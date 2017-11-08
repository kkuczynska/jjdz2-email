package com.jbd.searchKeywords;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.ejb.Stateless;
import java.util.Map;
import java.util.TreeMap;

@Stateless
public class KeywordsForm {

    private static final Logger LOGGER = LoggerFactory.getLogger(KeywordsForm.class);
    private static final Marker MARKER = MarkerFactory.getMarker("KeywordsForm");

    static final String QUESTION = "q";
    private Map<String, String> keywordsQuestions;

    public Map<String, String> getKeywordsForm() {
        keywordsQuestions = new TreeMap<>();
        JsonReader jsonReader = new JsonReader();
        LOGGER.info(MARKER, "Invoked questions map.");
        jsonReader
                .readQuestionJsonArray()
                .forEach(question ->
                        keywordsQuestions.put(QUESTION + "" + keywordsQuestions.size(), question));
        LOGGER.info(MARKER, "Keywords questions map has been created.");
        return keywordsQuestions;
    }

    public Map<String, String> getKeywordsQuestions() {
        return keywordsQuestions;
    }
}
