package com.jbd;

import static com.jbd.SearchCriteria.*;

public class QuestionForm {

    private static final String EMAIL_QUESTION = "Provide email address (xxxx@xx.xx)";
    private static final String KEYWORD_QUESTION = "Provide keywords to find in email subject. Separate them with commas.";
    private static final String STARTDATE_QUESTION = "Provide start date (YYYY-MM-DD)";
    private static final String ENDDATE_QUESTION = "Provide end date (YYYY-MM-DD)";

    public void askQuestionsAndSetAnswers() {
        UserCommunication sendMessage = new UserCommunication();

        sendMessage.sendUserMessage(EMAIL_QUESTION);
        setEMAIL(sendMessage.getUserResonse());

        sendMessage.sendUserMessage(STARTDATE_QUESTION);
        setSTARTDATE(sendMessage.getUserResonse());

        sendMessage.sendUserMessage(ENDDATE_QUESTION);
        setENDDATE(sendMessage.getUserResonse());

        sendMessage.sendUserMessage(KEYWORD_QUESTION);
        setKEYWORDS(sendMessage.getUserResonse());
    }
}
