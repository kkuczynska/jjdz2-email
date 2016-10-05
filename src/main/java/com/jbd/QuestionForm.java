package com.jbd;

import static com.jbd.SearchCriteria.*;

public class QuestionForm {

    private static final String EMAIL_QUESTION = "Podaj adres email w formacie xxxx@xx.xx";
    private static final String KEYWORD_QUESTION = "Podaj słowa kluczowe w tytule. Słowa oddziel przecinkami.";
    private static final String STARTDATE_QUESTION = "Zakres dat: podaj datę początkową w formacie DD/MM/YYYY";
    private static final String ENDDATE_QUESTION = "Zakres dat: podaj datę końcową w formacie DD/MM/YYYY";

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
