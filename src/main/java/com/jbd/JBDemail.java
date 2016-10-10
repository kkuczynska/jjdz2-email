package com.jbd;

public class JBDemail {
    public static void main(String[] args) {

        QuestionForm askQuestionsAndQuestionForm = new QuestionForm();
        askQuestionsAndQuestionForm.askQuestionsAndSetAnswers();

        PathGetter pG = new PathGetter();
        pG.createFileListFromPath(pG.askUserAboutInputPath());

    }
}
