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
    FinalEmailsSet finalEmailsSet;
    @EJB
    MakeEmailsFromString makeEmailsFromString;

    protected void doPost(HttpServletRequest req, HttpServletResponse response) {

        List<String> filesInStrings = new ArrayList<>();
        List<Email> emails = new ArrayList<>();
        List<String> emailToFind = new ArrayList<>();

        String path = req.getParameter("emailPath");
        searchCriteria.setEMAIL(req.getParameter("email"));
        searchCriteria.setSTARTDATE(req.getParameter("startDate"));
        searchCriteria.setENDDATE(req.getParameter("endDate"));
        searchCriteria.setKEYWORDS(req.getParameter("keywords"));

        emailToFind.addAll(searchCriteria.getEMAIL());
       // emailToFind.add(searchCriteria.getSTARTDATE());

        pathGetter.createFileListFromPath(path);
        filesInStrings.addAll(pathGetter.getFileList().stream().map(fileLoader::fileLoad).collect(Collectors.toList()));
        for (String emailAddress : filesInStrings) {
            emails.addAll(makeEmailsFromString.makeEmailList(emailAddress));
        }

        req.setAttribute("finalEmailSet", finalEmailsSet.createUniqueEmailsSet(emailToFind, emails));

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
