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
import java.util.ArrayList;

@WebServlet(urlPatterns = "searchk")
public class SearchKeywordsServlet extends HttpServlet {

    @EJB
    Keywords keywords;
    @EJB
    JsonReader jsonReader;

    protected void doPost(HttpServletRequest req, HttpServletResponse response) {

        jsonReader.readQuestionJsonArray();

        System.out.println("jsonReader = " + jsonReader.readQuestionJsonArray().size());
        for (int i = 1; i < jsonReader.readQuestionJsonArray().size() + 1; i++) {
            String answer = "0";
            if ("yes".equals(req.getParameter("keywordsForm" + String.valueOf(i)))) {
                answer = "1";
            }
            keywords.gatherAnswers(answer);
        }
        keywords.createKeywordsSet();

        req.setAttribute("keywordsList", keywords.createKeywordsSet());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/searchk.jsp");
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
