package com.jbd.servlets;

import com.jbd.KeywordsFinder.JsonReader;
import com.jbd.KeywordsFinder.Keywords;
import com.jbd.KeywordsFinder.KeywordsQuestionsMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.jbd.KeywordsFinder.KeywordsQuestionsMap.QUESTION;

@WebServlet(urlPatterns = "keywords")
public class SearchKeywordsServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchKeywordsServlet.class);
    private static final Marker MARKER = MarkerFactory.getMarker("SearchKeywordsServlet");

    @EJB
    Keywords keywords;
    @EJB
    JsonReader jsonReader;
    @EJB
    KeywordsQuestionsMap keywordsQuestionsMap;

    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {

        LOGGER.info(MARKER, "User redirected to keywords.jsp with doGet().");
        req.setAttribute("questions", keywordsQuestionsMap.createKeywordsMap());
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

    protected void doPost(HttpServletRequest req, HttpServletResponse response) {

        keywordsQuestionsMap.createKeywordsMap();
        LOGGER.info(MARKER, "Invoked creaion of keywords map.");

        for (int questionIndex = 0;
             questionIndex < keywordsQuestionsMap.createKeywordsMap().size(); questionIndex++) {
            String answer = "0";
            if ("yes".equalsIgnoreCase(req.getParameter(QUESTION + "" + String.valueOf(questionIndex)))) {
                answer = "1";
            }
            keywords.gatherAnswers(answer);
            LOGGER.info(MARKER, "Noted user reaponse: " + answer);
        }

        req.setAttribute("keywordsList", keywords.createKeywordsSet());
        LOGGER.info(MARKER, "Set JSP attribute \"keywordsList\".");
        req.setAttribute("questions", keywordsQuestionsMap.createKeywordsMap());
        LOGGER.info(MARKER, "Set JSP attribute \"questions\" with keywords questionnaire.");

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
    }

}
