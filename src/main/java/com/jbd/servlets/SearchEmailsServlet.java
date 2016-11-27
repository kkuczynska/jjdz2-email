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
import java.util.List;
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
    ContentmentVerification contentmentVerification;
    @EJB
    MakeEmailsFromString makeEmailsFromString;

    protected void doPost(HttpServletRequest req, HttpServletResponse response) {

        String email = req.getParameter("email");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String keywords = req.getParameter("keywords");
        String path = req.getParameter("emailPath");

        searchCriteria.setEMAIL(email);
        searchCriteria.setSTARTDATE(startDate);
        searchCriteria.setENDDATE(endDate);
        searchCriteria.setKEYWORDS(keywords);
        pathGetter.createFileListFromPath(path);
        String startDateFormatted = searchCriteria.getSTARTDATE();

        List<String> filesInStrings = new ArrayList<>();
        List<Email> emails = new ArrayList<>();
        List<Email> emailsMatchingQuery;

        filesInStrings.addAll(pathGetter.getFileList().stream().map(fileLoader::fileLoad).collect(Collectors.toList()));

        for (String s : filesInStrings) {
            emails.addAll(makeEmailsFromString.makeEmailList(s));
        }

        emailsMatchingQuery = contentmentVerification.searchEmailByDate(startDateFormatted, emails);

        req.setAttribute("emailsMatchingQuery", emailsMatchingQuery);

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
