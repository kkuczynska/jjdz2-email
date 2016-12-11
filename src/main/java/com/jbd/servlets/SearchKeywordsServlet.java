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

@WebServlet(urlPatterns = "searchk")
public class SearchKeywordsServlet extends HttpServlet {

    @EJB
    Keywords keywords;
    @EJB
    JsonReader jsonReader;

    protected void doPost(HttpServletRequest req, HttpServletResponse response) {

        jsonReader.readQuestionJsonArray();
        for(int i = 0; i < jsonReader.readQuestionJsonArray().size(); i++) {
            keywords.gatherAnswers(req.getParameter("answer" + String.valueOf(i++)));
        }
        keywords.createKeywordsSet();

        System.out.println("keywords.getKeywordsSet() = " + keywords.createKeywordsSet());
        req.setAttribute("keywordsList", keywords.createKeywordsSet());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/searchk.jsp");
        try {
            dispatcher.forward(req, response);
        } catch (ServletException e) {
            //LOGGER
        } catch (IOException e) {
            //LOGGER
        }
    }
}
