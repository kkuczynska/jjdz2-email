package com.jbd.servlets;

import com.jbd.KeywordsFinder.JsonReader;
import com.jbd.KeywordsFinder.Keywords;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "keywords")
public class SearchKeywordsServlet extends HttpServlet {

    @EJB
    Keywords keywords;
    @EJB
    JsonReader jsonReader;

    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {

        req.setAttribute("questions", jsonReader.readQuestionJsonArray());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/keywords.jsp");
        try {
            dispatcher.forward(req, response);
        } catch (ServletException e) {
            //LOGGER
        } catch (IOException e) {
            //LOGGER
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse response) {

        jsonReader.readQuestionJsonArray();

        List<Integer> questionID = new ArrayList<>();
        for (int questionIndex = 1;
             questionIndex < jsonReader.readQuestionJsonArray().size() + 1; questionIndex++) {
            String answer = "0";
            if ("yes".equalsIgnoreCase(req.getParameter(String.valueOf(questionIndex)))) {
                answer = "1";
            }
            keywords.gatherAnswers(answer);
            questionID.add(questionIndex);
        }

        req.setAttribute("keywordsList", keywords.createKeywordsSet());
        req.setAttribute("questions", jsonReader.readQuestionJsonArray());
        req.setAttribute("questionID", questionID);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/keywords.jsp");
        try {
            dispatcher.forward(req, response);
        } catch (ServletException e) {
            //LOGGER
        } catch (IOException e) {
            //LOGGER
        }
        keywords.getKeywordsSet().clear();
    }
}
