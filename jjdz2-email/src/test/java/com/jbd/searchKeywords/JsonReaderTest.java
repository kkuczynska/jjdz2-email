package com.jbd.searchKeywords;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.jbd.searchKeywords.Keywords.getNumberOfAnswers;
import static org.junit.Assert.assertTrue;

public class JsonReaderTest {

    private Set<String> answersSet = new HashSet<>();
    private Keywords keywords = new Keywords();

    private static ArrayList<String> BUSINESS_KEYWORDS = new ArrayList<>(Arrays.asList(
            "business",
            "official",
            "officialy",
            "formally",
            "manager",
            "boss",
            "work",
            "job",
            "wokplace",
            "cowoker",
            "office",
            "assistant",
            "secretary",
            "CEO",
            "chairman",
            "president"
    ));

    @Test
    public void complete_list_of_answers_should_return_true() throws Exception {
        String emailBusiness = "1";

        JsonReader jsonReader = new JsonReader();
        ArrayList<String> answers = jsonReader.readAnswerJsonArray(emailBusiness);

        assertTrue(answers.containsAll(BUSINESS_KEYWORDS));
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