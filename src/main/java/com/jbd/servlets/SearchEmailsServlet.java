package com.jbd.servlets;

import com.jbd.*;
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
import java.util.Map;

@WebServlet(urlPatterns = "emails")
public class SearchEmailsServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchEmailsServlet.class);
    private static final Marker MARKER = MarkerFactory.getMarker("SearchEmailsServlet");

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

        searchCriteria.setEmail(req.getParameter("email"));
        LOGGER.info(MARKER, "Set value for email field.");
        searchCriteria.setStartDate(req.getParameter("startDate"));
        LOGGER.info(MARKER, "Set value for start date field.");
        searchCriteria.setEndDate(req.getParameter("endDate"));
        LOGGER.info(MARKER, "Set value for end date field.");
        searchCriteria.setKeywords(req.getParameter("keywords"));
        LOGGER.info(MARKER, "Set value for keywords field.");

        String emailPath = req.getParameter("emailPath");
        LOGGER.info(MARKER, "Set value for emailPath.");
        if (!("".equals(emailPath))) {
            try {
                emails = fileParser.parseEmails(pathGetter.createFileListFromPath(emailPath));
            } catch (Exception e) {
                LOGGER.debug(MARKER, "Wrong path has been given by user.");
                e.printStackTrace();
            }
        }

        req.setAttribute("finalEmailSet", finalEmailsSet.createUniqueEmailsSet(emails));
        LOGGER.info(MARKER, "Set JSP attribute \"finalEmailSet\".");

        Map<String, List<String>> resultMap = displayPhoneNumbers.searchPhoneNumbers(emails);
        if ("yes".equals(req.getParameter("phoneNumbers"))) {
            req.setAttribute("displayNumbers", resultMap);
            LOGGER.info(MARKER, "Set JSP attribute \"displayNumbers\".");
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/emails.jsp");
        LOGGER.info(MARKER, "Dispatcher to emails.jsp");
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
}
