package com.jbd;

import com.sun.net.httpserver.HttpServer;

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

@WebServlet(urlPatterns = "/sendData")
public class JBDemailServlet extends HttpServlet {

    @EJB
    ContentmentVerification verification;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Email e1 = new Email("kkk@wp.pl","test test");
        Email e2 = new Email("kkk123@wp.pl","test");
        List<Email> input = Arrays.asList(e1,e2);

        String keyword = req.getParameter("keywords");
        List<String> key = Arrays.asList(keyword);
        List<Email> results = verification.searchEmailByTitleWithKeyWords(key, input);

        System.out.println(results);
        req.setAttribute("results", results);

        System.out.println("lol3");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/form.jsp");
        dispatcher.forward(req, resp);
    }
}
