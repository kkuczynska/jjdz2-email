package com.jbd;

import static com.jbd.SearchCriteria.*;

public class QuestionForm {

    private static String EMAIL_QUESTION = "Provide email address (xxxx@xx.xx)";
    private static String KEYWORD_QUESTION = "Provide keywords to find in email subject. Separate them with commas.";
    private static String STARTDATE_QUESTION = "Provide start date (YYYY-MM-DD)";
    private static String ENDDATE_QUESTION = "Provide end date (YYYY-MM-DD)";

    private static String EMAIL_QUESTION_WRONG_VALUE = "Wrong format, please try again (xxxx@xx.xx)";
    private static String STARTDATE_QUESTION_WRONG_VALUE = "Wrong format or invalid date, please provide new start date (YYYY-MM-DD)";
    private static String ENDDATE_QUESTION_WRONG_VALUE = "Wrong format or invalid date, please provide new end date (YYYY-MM-DD)";

    public void askQuestionsAndSetAnswers() {
        UserCommunication sendMessage = new UserCommunication();
        SearchCriteriaValidator searchCriteriaValidator = new SearchCriteriaValidator();
        SearchCriteria searchCriteria = new SearchCriteria();

        do {
            sendMessage.sendUserMessage(EMAIL_QUESTION);
            setEMAIL(sendMessage.getUserResponse());
            if(searchCriteriaValidator.validateEmail(searchCriteria.getEMAIL())==false) {
                EMAIL_QUESTION=EMAIL_QUESTION_WRONG_VALUE;
            }
        } while(!searchCriteriaValidator.validateEmail(searchCriteria.getEMAIL()));

        do {
        sendMessage.sendUserMessage(STARTDATE_QUESTION);
        setSTARTDATE(sendMessage.getUserResponse());
            if(searchCriteriaValidator.validateStartDate(searchCriteria.getSTARTDATE())==false) {
                STARTDATE_QUESTION=STARTDATE_QUESTION_WRONG_VALUE;
            }
        } while(!searchCriteriaValidator.validateStartDate(searchCriteria.getSTARTDATE()));

        do {
        sendMessage.sendUserMessage(ENDDATE_QUESTION);
        setENDDATE(sendMessage.getUserResponse());
            if(searchCriteriaValidator.validateEndDate(searchCriteria.getENDDATE())==false) {
                ENDDATE_QUESTION=ENDDATE_QUESTION_WRONG_VALUE;
            }
        } while(!searchCriteriaValidator.validateEndDate(searchCriteria.getENDDATE()));

        sendMessage.sendUserMessage(KEYWORD_QUESTION);
        setKEYWORDS(sendMessage.getUserResponse());
    }
}
