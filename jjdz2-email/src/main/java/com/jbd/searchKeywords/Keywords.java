package com.jbd.searchKeywords;

import com.jbd.UserCommunication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.jbd.Questions.ANSWER_POSITIVE;

@Stateless
public class Keywords {

    private static final Logger LOGGER = LoggerFactory.getLogger(Keywords.class);
    private static final Marker KEYWORDS_MARKER = MarkerFactory.getMarker("Keywords");

    private static Set<String> keywordsSet = new HashSet();
    private static ArrayList<String> answersIDs = new ArrayList<>();
    private static UserCommunication userCommunication = new UserCommunication();
    private static final String NO_MATCHES = "Sorry! \nThere is no keywords matching your query.";

    public void createAndDisplayKeywordsHashSet() {
        createKeywordsSet();
        displayKeywords();
    }

    public void gatherAnswers(String answerID) {
        answersIDs.add(answerID);
        LOGGER.info(KEYWORDS_MARKER, "Noted user response: " + answerID);
    }

    public List<String> getQuestionName() {
        JsonReader jsonReader = new JsonReader();
        List<String> questions = new ArrayList<>();
        questions.addAll(jsonReader.readAnswerJsonArray("questions"));
        return questions;
    }

    public Set<String> createKeywordsSet() {

        JsonReader jsonReader = new JsonReader();
        LOGGER.info(KEYWORDS_MARKER, "Creating HashSet with unique keywords based on user's answers.");
        for (int index = 0; index < answersIDs.size(); index++) {
            if (answersIDs.get(index).equalsIgnoreCase(ANSWER_POSITIVE)) {
                LOGGER.info(KEYWORDS_MARKER, "Adding keywords to set.");

                System.out.println("answersIDs = " + answersIDs);
                for (int count = 0; count < answersIDs.size(); count++) {
                    if (ANSWER_POSITIVE.equalsIgnoreCase(answersIDs.get(count))) {
                        keywordsSet.addAll(jsonReader.readAnswerJsonArray(String.valueOf(count)));
                    }
                }
                if (keywordsSet.isEmpty()) {
                    LOGGER.info(KEYWORDS_MARKER, "Confirming keywords set is populated with data, size is: "
                            + keywordsSet.size());
                    userCommunication.sendUserMessage(NO_MATCHES);
                }
                answersIDs.clear();
                System.out.println("keywords size: " + keywordsSet.size());

            }
        }
        return keywordsSet;
    }

    private static void displayKeywords() {
        UserCommunication userCommunication = new UserCommunication();
        LOGGER.info(KEYWORDS_MARKER, "Displaying all keywords matching user's query.");
        for (String keyword : keywordsSet) {
            userCommunication.sendUserMessage(keyword);
        }
    }

    public static Set<String> getKeywordsSet() {
        return keywordsSet;
    }

    public static int getNumberOfAnswers() {
        return answersIDs.size();
    }

    public static void clearAnswersList() {
        answersIDs.clear();
    }

}
