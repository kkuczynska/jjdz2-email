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
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "searche")
public class SearchEmailsServlet extends HttpServlet {

    @EJB
    SearchCriteria searchCriteria;
    @EJB
    PathGetter pathGetter;
    @EJB
    FileLoad fileLoader;
    @EJB
    FinalEmailsSet finalEmailsSet;
    @EJB
    MakeEmailsFromString makeEmailsFromString;
    @EJB
    DisplayPhoneNumbers displayPhoneNumbers;

    protected void doPost(HttpServletRequest req, HttpServletResponse response) {

        List<String> filesInStrings = new ArrayList<>();
        List<Email> emails = new ArrayList<>();
        List<String> emailToFind = new ArrayList<>();

        searchCriteria.setEMAIL(req.getParameter("email"));
        searchCriteria.setSTARTDATE(req.getParameter("startDate"));
        searchCriteria.setENDDATE(req.getParameter("endDate"));
        searchCriteria.setKEYWORDS(req.getParameter("keywords"));

        emailToFind.addAll(searchCriteria.getEMAIL());

        pathGetter.createFileListFromPath(req.getParameter("emailPath"));
        filesInStrings.addAll(pathGetter.getFileList().stream().map(fileLoader::fileLoad).collect(Collectors.toList()));
        for (String emailAddress : filesInStrings) {
            emails.addAll(makeEmailsFromString.makeEmailList(emailAddress));
        }

        req.setAttribute("finalEmailSet", finalEmailsSet.createUniqueEmailsSet(emails));

        List<Email> eMailKeeper = Arrays.asList(new Email("Angus.Hardie@malcolm23hardie2.com", "subject", "2015-01-01 00:00", "test 515-417-846"),
                new Email("Angus.Hardie@malcolm23hardie2.com", "subject", "2015-01-01 00:00", "test 515417844"),
                new Email("Angus.Hardie@malcolm23hardie2.com", "subject", "2015-01-01 00:00", "test 515 417 846"),
                new Email("Angus.Hardied@malcolm23hardie2.com", "subject", "2015-01-01 00:00", "test 616478366"),
                new Email("Angus.dddHardie@malcolm23hardie2.com", "subject", "2015-01-01 00:00", "test 616478366"));

        Map<String, List<String>> resultMap = displayPhoneNumbers.searchPhoneNumbers(eMailKeeper);

        if (req.getParameter("phoneNumbers").equals("yes")) {
            req.setAttribute("displayNumbers", resultMap);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/searche.jsp");
        try {
            dispatcher.forward(req, response);
        } catch (ServletException e) {
            //LOGGER
        } catch (IOException e) {
            //LOGGER
        }
    }

}
