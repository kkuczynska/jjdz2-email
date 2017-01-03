package com.jbd.servlets;

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

import static com.jbd.KeywordsFinder.KeywordsQuestionsMap.QUESTION;

@WebServlet(urlPatterns = "display")
public class DisplayKeywordsServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchKeywordsServlet.class);
    private static final Marker MARKER = MarkerFactory.getMarker("SearchKeywordsServlet");

    @EJB
    Keywords keywords;
    @EJB
    KeywordsQuestionsMap keywordsQuestionsMap;



    protected void doPost(HttpServletRequest req, HttpServletResponse response) {

        System.out.println("q0: "+ req.getParameter("q0"));
        System.out.println("q1: "+ req.getParameter("q1"));
        System.out.println("q2: "+ req.getParameter("q2"));
        System.out.println("q3: "+ req.getParameter("q3"));
        System.out.println("qqqqqq5!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!: "+ req.getParameter("qq5"));

        for (int questionIndex = 0;
             questionIndex < keywordsQuestionsMap.getQuestionsMap().size(); questionIndex++) {
            String answer = "0";
            LOGGER.info(MARKER, "questionIndex " + questionIndex);
            if ("yes".equalsIgnoreCase(req.getParameter(QUESTION + "" + String.valueOf(questionIndex)))) {
                LOGGER.info(MARKER, "parameter: " + QUESTION + "" + String.valueOf(questionIndex));
                answer = "1";
            }
            keywords.gatherAnswers(answer);
            LOGGER.info(MARKER, "Noted user response: " + answer);
        }

        req.setAttribute("keywordsList", keywords.createKeywordsSet());
        LOGGER.info(MARKER, "Set JSP attribute \"keywordsList\".");
        req.setAttribute("questions", keywordsQuestionsMap.getQuestionsMap());
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
