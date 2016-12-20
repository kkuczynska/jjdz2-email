package com.jbd.servlets;

import com.jbd.*;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "emails")
public class SearchEmailsServlet extends HttpServlet {

    @EJB
    SearchCriteria searchCriteria;
    @EJB
    PathGetter pathGetter;
    @EJB
    FileParser fileParser;
    @EJB
    FinalEmailsSet finalEmailsSet;
    @EJB
    DisplayPhoneNumbers displayPhoneNumbers;

    protected void doPost(HttpServletRequest req, HttpServletResponse response) {

        List<Email> emails = new ArrayList<>();
        List<String> emailToFind = new ArrayList<>();

        searchCriteria.setEMAIL(req.getParameter("email"));
        searchCriteria.setSTARTDATE(req.getParameter("startDate"));
        searchCriteria.setENDDATE(req.getParameter("endDate"));
        searchCriteria.setKEYWORDS(req.getParameter("keywords"));

        emailToFind.addAll(searchCriteria.getEMAIL());

        String emailPath = req.getParameter("emailPath");
        if (!"".equals(emailPath)) {
            try {
                emails = fileParser.parseEmails(pathGetter.createFileListFromPath(emailPath));
            } catch (Exception e) {
                //LOGGER
                e.printStackTrace();
            }
        }

        req.setAttribute("finalEmailSet", finalEmailsSet.createUniqueEmailsSet(emails));

        Map<String, List<String>> resultMap = displayPhoneNumbers.searchPhoneNumbers(emails);

        if ("yes".equals(req.getParameter("phoneNumbers"))) {
            req.setAttribute("displayNumbers", resultMap);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/emails.jsp");
        try {
            dispatcher.forward(req, response);
        } catch (ServletException e) {
            //LOGGER
        } catch (IOException e) {
            //LOGGER
        }
    }

}
