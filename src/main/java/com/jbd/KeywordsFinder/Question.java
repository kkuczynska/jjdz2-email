package com.jbd.KeywordsFinder;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private String question;
    private int questionId;
    private List<String> questionList = new ArrayList<>();

    public Question(String question, int questionId) {
        this.question = question;
        this.questionId = questionId;
    }

    public List<String> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<String> questionList) {
        this.questionList = questionList;
        JsonReader jsonReader = new JsonReader();
        List<String> questions = jsonReader.readQuestionJsonArray();
        for(String question : questions) {
            new Question(question, questions.indexOf(question));
        }
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}
