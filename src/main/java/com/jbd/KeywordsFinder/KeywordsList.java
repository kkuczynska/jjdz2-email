package com.jbd.KeywordsFinder;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class KeywordsList {

    public Set CreateKeywordsSet(EnumSet keywordsSet, String jsonNodeName) throws IOException, ParseException {
        JsonReader jsonReader = new JsonReader();
        ArrayList<String> answersList = jsonReader.readAnswerJsonArray(jsonNodeName);

        Set<String> keywordsFinalSet = new HashSet<String>();

        keywordsSet.addAll(answersList);

        return keywordsFinalSet;
    }


}
