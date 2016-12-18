package com.jbd.KeywordsFinder;

import com.jbd.UserCommunication;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static com.jbd.Questions.ANSWER_POSITIVE;

@Stateless
public class Keywords {

    private static Set<String> KEYWORDS_SET = new HashSet();
    private static ArrayList<String> answersIDs = new ArrayList<>();
    private static UserCommunication userCommunication = new UserCommunication();
    private static final String NO_MATCHES = "Sorry! \nThere is no keywords matching your query.";

    public void createAndDisplayKeywordsHashSet() {
        createKeywordsSet();
       // displayKeywords();
    }

    public void gatherAnswers(String answerID) {
        answersIDs.add(answerID);
    }

    public Set<String> createKeywordsSet() {

        JsonReader jsonReader = new JsonReader();

        System.out.println("answersIDs = " + answersIDs);
        for(int index = 0; index < answersIDs.size(); index++) {
            if (ANSWER_POSITIVE.equalsIgnoreCase(answersIDs.get(index))) {
                KEYWORDS_SET.addAll(jsonReader.readAnswerJsonArray(String.valueOf(index)));
            }
        }
        if (KEYWORDS_SET.isEmpty()) {
            userCommunication.sendUserMessage(NO_MATCHES);
        }
        answersIDs.clear();
        System.out.println("keywords size: " + KEYWORDS_SET.size());
        return KEYWORDS_SET;
    }

//    private static void displayKeywords() {
//        UserCommunication userCommunication = new UserCommunication();
//        for (String keyword : KEYWORDS_SET) {
//            userCommunication.sendUserMessage(keyword);
//        }
//    }

    public static Set<String> getKeywordsSet() {
        return KEYWORDS_SET;
    }
}
