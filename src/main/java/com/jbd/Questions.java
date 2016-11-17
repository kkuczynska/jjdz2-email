package com.jbd;

import com.jbd.KeywordsFinder.JsonReader;
import com.jbd.KeywordsFinder.Keywords;

import static com.jbd.SearchCriteria.*;

public class Questions {

    public static final String ANSWER_POSITIVE = "1";
    public static final String ANSWER_NEGATIVE = "0";
    private static String EMAIL_QUESTION = "Provide email address (xxxx@xx.xx)";
    private static String KEYWORD_QUESTION = "Provide keywords to find in email subject. Separate them with commas.";
    private static String STARTDATE_QUESTION = "Provide start date (YYYY-MM-DD)";
    private static String ENDDATE_QUESTION = "Provide end date (YYYY-MM-DD)";

    private static String EMAIL_QUESTION_WRONG_VALUE = "Wrong format, please try again (xxxx@xx.xx)";
    private static String STARTDATE_QUESTION_WRONG_VALUE = "Wrong format or invalid date, please provide new start date (YYYY-MM-DD)";
    private static String ENDDATE_QUESTION_WRONG_VALUE = "Wrong format or invalid date, please provide new end date (YYYY-MM-DD)";

    private static final String KEYWORDS_HELP_QUESTION = "Do you need help with keywords? (yes / no)";
    private static final String KEYWORDS_HEADER = "\n--------------------------------------" +
                                                  "\nKeywords matching your query: " +
                                                  "\n--------------------------------------";
    private final String ANSWER_NO = "no";
    private final String ANSWER_YES = "yes";

    private UserCommunication userCommunication = new UserCommunication();
    private SearchCriteriaValidator searchCriteriaValidator = new SearchCriteriaValidator();
    private SearchCriteria searchCriteria = new SearchCriteria();
    private String WRONG_INPUT = "Accepted answers: yes , no";

    public void searchCriteriaForm() {
        keywordsForm();
        generalForm();
    }

    private void generalForm() {
        do {
            userCommunication.sendUserMessage(EMAIL_QUESTION);
            setEMAIL(userCommunication.getUserResponse());
            if (searchCriteriaValidator.validateEmail(searchCriteria.getEMAIL()) == false) {
                EMAIL_QUESTION = EMAIL_QUESTION_WRONG_VALUE;
            }
        } while (!searchCriteriaValidator.validateEmail(searchCriteria.getEMAIL()));

        do {
            userCommunication.sendUserMessage(STARTDATE_QUESTION);
            setSTARTDATE(userCommunication.getUserResponse());
            if (searchCriteriaValidator.validateStartDate(searchCriteria.getSTARTDATE()) == false) {
                STARTDATE_QUESTION = STARTDATE_QUESTION_WRONG_VALUE;
            }
        } while (!searchCriteriaValidator.validateStartDate(searchCriteria.getSTARTDATE()));

        do {
            userCommunication.sendUserMessage(ENDDATE_QUESTION);
            setENDDATE(userCommunication.getUserResponse());
            if (searchCriteriaValidator.validateEndDate(searchCriteria.getENDDATE()) == false) {
                ENDDATE_QUESTION = ENDDATE_QUESTION_WRONG_VALUE;
            }
        } while (!searchCriteriaValidator.validateEndDate(searchCriteria.getENDDATE()));

        userCommunication.sendUserMessage(KEYWORD_QUESTION);
        setKEYWORDS(userCommunication.getUserResponse());
    }

    public void keywordsForm() {
        JsonReader jsonReader = new JsonReader();
        Keywords keywords = new Keywords();
        String answer = "";

        do {
            userCommunication.sendUserMessage(KEYWORDS_HELP_QUESTION);
            answer = userCommunication.getUserResponse();

            if (answer.equalsIgnoreCase(ANSWER_YES)) {
                for (String question : jsonReader.readQuestionJsonArray()) {
                    String keywordFormAnswer;

                    do {
                        userCommunication.sendUserMessage(question);
                        keywordFormAnswer = userCommunication.getUserResponse();
                        if (keywordFormAnswer.equalsIgnoreCase(ANSWER_YES)) {
                            keywords.gatherAnswers(ANSWER_POSITIVE);
                        } else if (keywordFormAnswer.equalsIgnoreCase(ANSWER_NO)) {
                            keywords.gatherAnswers(ANSWER_NEGATIVE);
                        } else {
                            userCommunication.sendUserMessage(WRONG_INPUT);
                        }
                    } while(!(keywordFormAnswer.equalsIgnoreCase(ANSWER_YES) ||
                                keywordFormAnswer.equalsIgnoreCase(ANSWER_NO)));

                }
                userCommunication.sendUserMessage(KEYWORDS_HEADER);
                keywords.createAndDisplayKeywordsHashSet();
            } else if (answer.equalsIgnoreCase(ANSWER_NO)) {
                break;
            } else {
                userCommunication.sendUserMessage(WRONG_INPUT);
            }

        } while (!(answer.equalsIgnoreCase(ANSWER_YES) || answer.equalsIgnoreCase(ANSWER_NO)));
    }
}
