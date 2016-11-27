package com.jbd.KeywordsFinder;

import com.jbd.UserCommunication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static com.jbd.Questions.ANSWER_POSITIVE;

public class Keywords {

    private static final Logger LOGGER = LoggerFactory.getLogger(Keywords.class);
    private static final Marker KEYWORDS_MARKER = MarkerFactory.getMarker("Keywords");

    private static Set<String> KEYWORDS_SET = new HashSet();
    private static ArrayList<String> answersIDs = new ArrayList<>();
    private static UserCommunication userCommunication = new UserCommunication();
    private static final String NO_MATCHES = "Sorry! \nThere is no keywords matching your query.";

    public void createAndDisplayKeywordsHashSet() {
        createKeywordsSet();
        displayKeywords();
    }

    public void gatherAnswers(String answerID) {
        LOGGER.info(KEYWORDS_MARKER, "Creating answers from keywords form.");
        answersIDs.add(answerID);
    }

    private static void createKeywordsSet() {
        JsonReader jsonReader = new JsonReader();
        LOGGER.info(KEYWORDS_MARKER, "Creating HashSet with unique keywords based on user's answers.");
        for (int index = 0; index < answersIDs.size(); index++) {
            if (answersIDs.get(index).equalsIgnoreCase(ANSWER_POSITIVE)) {
                LOGGER.info(KEYWORDS_MARKER, "Adding keywords to set.");
                KEYWORDS_SET.addAll(jsonReader.readAnswerJsonArray(String.valueOf(index)));
            }
        }
        if (KEYWORDS_SET.isEmpty()) {
            LOGGER.info(KEYWORDS_MARKER, "Confirming keywords set is populated with data, size is: "
                    + KEYWORDS_SET.size());
            userCommunication.sendUserMessage(NO_MATCHES);
        }
    }

    private static void displayKeywords() {
        UserCommunication userCommunication = new UserCommunication();
        LOGGER.info(KEYWORDS_MARKER, "Displaying all keywords matching user's query.");
        for (String keyword : KEYWORDS_SET) {
            userCommunication.sendUserMessage(keyword);
        }
    }

}
