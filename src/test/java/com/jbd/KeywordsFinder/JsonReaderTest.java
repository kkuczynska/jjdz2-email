package com.jbd.KeywordsFinder;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class JsonReaderTest {

    private static ArrayList<String> ANSWERS_LIST = new ArrayList<>(Arrays.asList(
            "służbowy",
            "służbowo",
            "szef",
            "biznes",
            "praca",
            "współpracownik",
            "biuro",
            "asytentka",
            "asystent",
            "sekretarka",
            "prezes"
    ));

    @Test
    public void complete_list_of_answers_should_return_true() throws Exception {
        String emailBusiness = "1";

        JsonReader jsonReader = new JsonReader();
        ArrayList<String> answers = jsonReader.readAnswerJsonArray(emailBusiness);

        assertTrue(answers.containsAll(ANSWERS_LIST));
    }

    @Test
    public void set_without_duplicates_should_return_true() {
        ArrayList<String> answersList = new ArrayList<>(Arrays.asList(
                "teraz",
                "służbowo",
                "szef",
                "wczoraj",
                "biurko",
                "praca",
                "współpracownik",
                "tapczan",
                "film",
                "lista",
                "człowiek",
                "goście"
        ));
        ArrayList<String> finalAnswersList = new ArrayList<>(Arrays.asList(
                "teraz",
                "służbowo",
                "szef",
                "wczoraj",
                "biurko",
                "praca",
                "współpracownik",
                "tapczan",
                "film",
                "lista",
                "człowiek",
                "goście",
                "służbowy",
                "biznes",
                "biuro",
                "asytentka",
                "asystent",
                "sekretarka",
                "prezes"
        ));


        Set<String> answersSet = new HashSet<>();
        answersSet.addAll(answersList);
        answersSet.addAll(ANSWERS_LIST);

        assertTrue(answersSet.size() == finalAnswersList.size());
    }

    @Test
    public void question_match_should_return_true() throws IOException, ParseException {
        int questionID = 3;
        String question = "Are you looking for an email about a meeting?";

        JsonReader jsonReader = new JsonReader();
        String finalQuestion = jsonReader.readQuestionJsonArray().get(questionID);

        assertTrue(finalQuestion.equals(question));
    }
}