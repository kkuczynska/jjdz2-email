package com.jbd.servlets;


import com.jbd.DBA.Form;
import com.jbd.DBA.Form_Details;
import com.jbd.DBA.ManageUser;

import com.jbd.KeywordsFinder.Keywords;
import com.jbd.KeywordsFinder.KeywordsQuestionsMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.jbd.KeywordsFinder.KeywordsQuestionsMap.QUESTION;

@WebServlet(urlPatterns = "keywords")
public class SearchKeywordsServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchKeywordsServlet.class);
    private static final Marker MARKER = MarkerFactory.getMarker("SearchKeywordsServlet");
    private int questionnaireCounter = 1;

    @EJB
    Keywords keywords;
    @EJB
    KeywordsQuestionsMap keywordsQuestionsMap;
    @Inject
    ManageUser manageUser;

    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {

        LOGGER.info(MARKER, "User redirected to keywords.jsp with doGet().");
        req.setAttribute("questions", keywordsQuestionsMap.createQuestionsMap());
        LOGGER.info(MARKER, "Set JSP attribute \"question\" with keywords questionnaire.");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/keywords.jsp");
        LOGGER.info(MARKER, "Dispatcher to keywords.jsp");
        try {
            dispatcher.forward(req, response);
        } catch (ServletException e) {
            LOGGER.debug(MARKER, "Caught ServletException " + e);
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.debug(MARKER, "Caught IOException " + e);
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) {

        for (int questionIndex = 0;
             questionIndex < keywordsQuestionsMap.getQuestionsMap().size(); questionIndex++) {
            String answer = "0";
            LOGGER.info(MARKER, "questionIndex " + questionIndex);
            if ("yes".equalsIgnoreCase(req.getParameter(QUESTION + "" + String.valueOf(questionIndex)))) {
                LOGGER.info(MARKER, "parameter: " + QUESTION + "" + String.valueOf(questionIndex));
                answer = "1";
                req.setAttribute("checked" + QUESTION + "" + String.valueOf(questionIndex) + "yes", "checked");
            } else if ("no".equalsIgnoreCase(req.getParameter(QUESTION + "" + String.valueOf(questionIndex)))) {
                req.setAttribute("checked" + QUESTION + "" + String.valueOf(questionIndex) + "no", "checked");
            }
            keywords.gatherAnswers(answer);
            LOGGER.info(MARKER, "Noted user response: " + answer);
        }

        if (keywords.createKeywordsSet().size() < 1) {
            req.setAttribute("keywordsMsg", "No keywords found.");
        } else {
            req.setAttribute("keywordsMsg", "Keyword matching your criteria:");
            req.setAttribute("keywordsList", keywords.createKeywordsSet());
        }
        LOGGER.info(MARKER, "Set JSP attribute \"keywordsList\".");
        req.setAttribute("questions", keywordsQuestionsMap.getQuestionsMap());
        LOGGER.info(MARKER, "Set JSP attribute \"questions\" with keywords questionnaire.");

        if (req.getParameter("q0") != null && req.getParameter("q1") != null
                && req.getParameter("q2") != null && req.getParameter("q2") != null) {
            Form form = new Form();
            String name = "Question Form " + questionnaireCounter;
            form.setName(name);
            form.setCreationTime(LocalDateTime.now());
            LOGGER.info("Created new form: " + form.getName());
            manageUser.saveForm(form);
            questionnaireCounter++;

            //Connecting details with form
            Form forConnectingWithDetails = new Form();
            forConnectingWithDetails = manageUser.getFormByName(name);
            List<String> questions = keywords.getQuestionName();
            int questionNumber =1;

            for (int questionIndex = 0; questionIndex < questions.size(); questionIndex++) {
                Form_Details form_details = new Form_Details();
                //form_details.setQuestion(questions.get(questionIndex));
                form_details.setQuestion(questionNumber++);
                form_details.setResponse(req.getParameter("q" + questionIndex));
                form_details.setForm(forConnectingWithDetails);
                LOGGER.info("Created new form_details:");
                manageUser.saveFormDetails(form_details);
            }
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/keywords.jsp");
        LOGGER.info(MARKER, "Dispatcher to keywords.jsp");
        try {
            dispatcher.forward(req, response);
        } catch (ServletException e) {
            LOGGER.debug(MARKER, "Caught ServletException " + e);
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.debug(MARKER, "Caught IOException " + e);
            e.printStackTrace();
        }
        keywords.getKeywordsSet().clear();
        LOGGER.debug(MARKER, "Cleared keywords set. Size: " + keywords.getKeywordsSet().size());
        keywords.clearAnswersList();
        LOGGER.debug(MARKER, "Cleared answers list. Size: " + keywords.getNumberOfAnswers());
    }
}
