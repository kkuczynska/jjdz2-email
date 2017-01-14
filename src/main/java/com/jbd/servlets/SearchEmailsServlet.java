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
import java.util.Set;

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
        if (("".equals(emailPath))) {
            req.setAttribute("emailsFound",
                    "          <span class=\"glyphicon glyphicon-exclamation-sign\" id=\"noPathMsg\"></span>\n" +
                            "          No path to email file given.\n" +
                            "           This field is required. \n");
        } else {
            try {
                emails = fileParser.parseEmails(pathGetter.createFileListFromPath(emailPath));
            } catch (Exception e) {
                LOGGER.debug(MARKER, "Wrong path has been given by user.");
                e.printStackTrace();
            }
        }
        Set<Email> emailSet = finalEmailsSet.createUniqueEmailsSet(emails);
        if (emailSet.size() > 0) {
            req.setAttribute("emailsFound", "Emails matching your criteria:");
        } else if (0 == emailSet.size() && !("".equals(emailPath))) {
            req.setAttribute("emailsFound", "No emails matching your criteria.");
        }
        req.setAttribute("finalEmailSet", emailSet);
        LOGGER.info(MARKER, "Set JSP attribute \"finalEmailSet\".");

        Map<String, List<String>> resultMap = displayPhoneNumbers.searchPhoneNumbers(emails);
        if ("yes".equals(req.getParameter("phoneNumbers"))) {
            if (resultMap.size() == 0) {
                req.setAttribute("phoneNumbersFound", "No phone numbers found.");
            } else {
                req.setAttribute("phoneNumbersFound", "Phone numbers found in your email file:");
            }
            req.setAttribute("displayNumbers", resultMap);
            LOGGER.info(MARKER, "Set JSP attribute \"displayNumbers\".");
        }

        req.setAttribute("emailFile", emailPath);
        req.setAttribute("emails", finalEmailsSet.emailsSeparatedWithComma(searchCriteria.getEmail()));
        req.setAttribute("startDate", searchCriteria.dateToDisplayInFrontEnd(searchCriteria.getStartDate()));
        req.setAttribute("endDate", searchCriteria.dateToDisplayInFrontEnd(searchCriteria.getEndDate()));
        req.setAttribute("keywords", finalEmailsSet.emailsSeparatedWithComma(searchCriteria.getKeywords()));

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
