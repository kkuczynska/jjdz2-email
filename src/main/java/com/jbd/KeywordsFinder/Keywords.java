package com.jbd.KeywordsFinder;

import com.jbd.UserCommunication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static com.jbd.Questions.ANSWER_POSITIVE;

public class Keywords {

    private static Set<String> KEYWORDS_SET = new HashSet();
    private static ArrayList<String> answersIDs = new ArrayList<>();
    private static UserCommunication userCommunication = new UserCommunication();
    private static final String NO_MATCHES = "Sorry! \nThere is no keywords matching your query.";

    public void createAndDisplayKeywordsHashSet() {
        createKeywordsSet();
        displayKeywords();
    }

    public void gatherAnswers(String answerID) {
        answersIDs.add(answerID);
    }

    private static void createKeywordsSet() {
        JsonReader jsonReader = new JsonReader();
        for (int index = 0; index < answersIDs.size(); index++) {
            System.out.println("element: " + answersIDs.get(index));
            if (answersIDs.get(index).equalsIgnoreCase(ANSWER_POSITIVE)) {
                KEYWORDS_SET.addAll(jsonReader.readAnswerJsonArray(answersIDs.get(index)));
            }
        }
        if (KEYWORDS_SET.isEmpty()) {
            userCommunication.sendUserMessage(NO_MATCHES);
        }
    }

    private static void displayKeywords() {
        UserCommunication userCommunication = new UserCommunication();
        for (String keyword : KEYWORDS_SET) {
            userCommunication.sendUserMessage(keyword);
        }
    }

}
