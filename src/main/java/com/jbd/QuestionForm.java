package com.jbd;

import com.jbd.KeywordsFinder.JsonReader;

import static com.jbd.SearchCriteria.*;

public class QuestionForm {

    private static String EMAIL_QUESTION = "Provide email address (xxxx@xx.xx)";
    private static String KEYWORD_QUESTION = "Provide keywords to find in email subject. Separate them with commas.";
    private static String STARTDATE_QUESTION = "Provide start date (YYYY-MM-DD)";
    private static String ENDDATE_QUESTION = "Provide end date (YYYY-MM-DD)";

    private static String EMAIL_QUESTION_WRONG_VALUE = "Wrong format, please try again (xxxx@xx.xx)";
    private static String STARTDATE_QUESTION_WRONG_VALUE = "Wrong format or invalid date, please provide new start date (YYYY-MM-DD)";
    private static String ENDDATE_QUESTION_WRONG_VALUE = "Wrong format or invalid date, please provide new end date (YYYY-MM-DD)";

    private UserCommunication userCommunication = new UserCommunication();
    private static final String KEYWORDS_HELP_QUESTION = "Do you need help with keywords? (yes / no)";

    public void searchCriteriaForm() {

        SearchCriteriaValidator searchCriteriaValidator = new SearchCriteriaValidator();
        SearchCriteria searchCriteria = new SearchCriteria();

        do {
            userCommunication.sendUserMessage(EMAIL_QUESTION);
            setEMAIL(userCommunication.getUserResponse());
            if(searchCriteriaValidator.validateEmail(searchCriteria.getEMAIL())==false) {
                EMAIL_QUESTION=EMAIL_QUESTION_WRONG_VALUE;
            }
        } while(!searchCriteriaValidator.validateEmail(searchCriteria.getEMAIL()));

        do {
        userCommunication.sendUserMessage(STARTDATE_QUESTION);
        setSTARTDATE(userCommunication.getUserResponse());
            if(searchCriteriaValidator.validateStartDate(searchCriteria.getSTARTDATE())==false) {
                STARTDATE_QUESTION=STARTDATE_QUESTION_WRONG_VALUE;
            }
        } while(!searchCriteriaValidator.validateStartDate(searchCriteria.getSTARTDATE()));

        do {
        userCommunication.sendUserMessage(ENDDATE_QUESTION);
        setENDDATE(userCommunication.getUserResponse());
            if(searchCriteriaValidator.validateEndDate(searchCriteria.getENDDATE())==false) {
                ENDDATE_QUESTION=ENDDATE_QUESTION_WRONG_VALUE;
            }
        } while(!searchCriteriaValidator.validateEndDate(searchCriteria.getENDDATE()));

        userCommunication.sendUserMessage(KEYWORD_QUESTION);
        setKEYWORDS(userCommunication.getUserResponse());
    }

    public void keywordsForm() {
        JsonReader json = new JsonReader();
        userCommunication.sendUserMessage(KEYWORDS_HELP_QUESTION);
        String answer = userCommunication.getUserResponse();

        if(answer.equals("yes")) {
            userCommunication.sendUserMessage(json.);
        }
    }
}
