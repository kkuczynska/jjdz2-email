package com.jbd;

import com.jbd.searchKeywords.JsonReader;
import com.jbd.searchKeywords.Keywords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import static com.jbd.SearchCriteria.*;

public class Questions {

    private static final Logger LOGGER = LoggerFactory.getLogger(Questions.class);
    private static final Marker QUESTIONS_MARKER = MarkerFactory.getMarker("Questions");

    public static final String ANSWER_POSITIVE = "1";
    public static final String ANSWER_NEGATIVE = "0";
    private static String EMAIL_QUESTION = "Provide email address (xxxx@xx.xx)";
    private static String STARTDATE_QUESTION = "Provide start date (YYYY-MM-DD)";
    private static String ENDDATE_QUESTION = "Provide end date (YYYY-MM-DD)";
    private static final String KEYWORDS_HELP_QUESTION = "Do you need help with keywords? (yes / no)";
    private static final String KEYWORDS_HEADER = "\n--------------------------------------" +
            "\nKeywords matching your query: " +
            "\n--------------------------------------";

    private UserCommunication userCommunication = new UserCommunication();
    private SearchCriteriaValidator searchCriteriaValidator = new SearchCriteriaValidator();
    private SearchCriteria searchCriteria = new SearchCriteria();
    String emailAddress = "";


    public void searchCriteriaForm() {
        keywordsForm();
        generalForm();
    }

    private void generalForm() {
        int numberOfEmailAddresses;
        LOGGER.info(QUESTIONS_MARKER, "General search form displaying started.");
        String NUMBER_OF_EMAILS_QUESTION = "How many email addresses would you like to find?";
        userCommunication.sendUserMessage(NUMBER_OF_EMAILS_QUESTION);
        numberOfEmailAddresses = Integer.valueOf(userCommunication.getUserResponse());

        for (int index = 0; index < numberOfEmailAddresses; index++) {
            do {
                userCommunication.sendUserMessage(EMAIL_QUESTION);
                emailAddress = userCommunication.getUserResponse();
                setEmail(emailAddress);
                if (!searchCriteriaValidator.validateEmail(emailAddress)) {
                    LOGGER.debug(QUESTIONS_MARKER, "Incorrect user input: " + emailAddress);
                    EMAIL_QUESTION = "Wrong format, please try again (xxxx@xx.xx)";
                } else if (!(numberOfEmailAddresses == 1 && index == 0)) {
                    EMAIL_QUESTION = "Provide additional email address.";
                }
            } while (!searchCriteriaValidator.validateEmail(emailAddress));
        }

        do {
            userCommunication.sendUserMessage(STARTDATE_QUESTION);
            setStartDate(userCommunication.getUserResponse());
            if (!searchCriteriaValidator.validateStartDate(searchCriteria.getStartDate())) {
                LOGGER.debug(QUESTIONS_MARKER, "Incorrect user input: " + searchCriteria.getStartDate());
                STARTDATE_QUESTION = "Wrong format or invalid date, please provide new start date (YYYY-MM-DD)";
            }
        } while (!searchCriteriaValidator.validateStartDate(searchCriteria.getStartDate()));

        do {
            userCommunication.sendUserMessage(ENDDATE_QUESTION);
            setEndDate(userCommunication.getUserResponse());
            if (!searchCriteriaValidator.validateEndDate(searchCriteria.getEndDate())) {
                LOGGER.debug(QUESTIONS_MARKER, "Incorrect user input: " + searchCriteria.getEndDate());
                ENDDATE_QUESTION = "Wrong format or invalid date, please provide new end date (YYYY-MM-DD)";
            }
        } while (!searchCriteriaValidator.validateEndDate(searchCriteria.getEndDate()));

        String KEYWORD_QUESTION = "Provide keywords to find in email subject. Separate them with commas.";
        userCommunication.sendUserMessage(KEYWORD_QUESTION);
        setKeywords(userCommunication.getUserResponse());
    }

    public void keywordsForm() {
        JsonReader jsonReader = new JsonReader();
        Keywords keywords = new Keywords();
        String answer = "";

        LOGGER.info(QUESTIONS_MARKER, "Keywords form displaying started.");
        String ANSWER_YES = "yes";
        String ANSWER_NO = "no";
        do {
            userCommunication.sendUserMessage(KEYWORDS_HELP_QUESTION);
            answer = userCommunication.getUserResponse();

            String WRONG_INPUT = "Accepted answers: yes , no";
            if (answer.equalsIgnoreCase(ANSWER_YES)) {
                for (String question : jsonReader.readQuestionJsonArray()) {
                    String keywordFormAnswer;

                    do {
                        String POSSIBLE_ANSWERS = " (yes / no)";
                        userCommunication.sendUserMessage(question + POSSIBLE_ANSWERS);
                        keywordFormAnswer = userCommunication.getUserResponse();
                        if (keywordFormAnswer.equalsIgnoreCase(ANSWER_YES)) {
                            keywords.gatherAnswers(ANSWER_POSITIVE);
                        } else if (keywordFormAnswer.equalsIgnoreCase(ANSWER_NO)) {
                            keywords.gatherAnswers(ANSWER_NEGATIVE);
                        } else {
                            LOGGER.debug(QUESTIONS_MARKER, "Incorrect user input: " + keywordFormAnswer);
                            userCommunication.sendUserMessage(WRONG_INPUT);
                        }
                    } while (!(keywordFormAnswer.equalsIgnoreCase(ANSWER_YES) ||
                            keywordFormAnswer.equalsIgnoreCase(ANSWER_NO)));

                }
                userCommunication.sendUserMessage(KEYWORDS_HEADER);
                keywords.createAndDisplayKeywordsHashSet();
            } else if (answer.equalsIgnoreCase(ANSWER_NO)) {
                break;
            } else {
                LOGGER.debug(QUESTIONS_MARKER, "Incorrect user input: " + answer);
                userCommunication.sendUserMessage(WRONG_INPUT);
            }

        } while (!(answer.equalsIgnoreCase(ANSWER_YES) || answer.equalsIgnoreCase(ANSWER_NO)));
    }
}
