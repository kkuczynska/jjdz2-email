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

        for (int i = 1; i < jsonReader.readQuestionJsonArray().size() + 1; i++) {
            String answer = "0";
            if ("yes".equals(req.getParameter("keywordsForm" + String.valueOf(i)))) {
                answer = "1";
            }
            keywords.gatherAnswers(answer);
        }
        keywords.createKeywordsSet();

        req.setAttribute("keywordsList", keywords.createKeywordsSet());
        req.setAttribute("questions", jsonReader.readQuestionJsonArray());

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
