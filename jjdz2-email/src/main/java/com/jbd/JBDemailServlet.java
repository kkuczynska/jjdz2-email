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

@WebServlet(urlPatterns = "/App/sendData")
public class JBDemailServlet extends HttpServlet {

    @EJB
    ContentmentVerification verification;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Email e1 = new Email("qqq@wp.pl", "test test");
        Email e2 = new Email("www@wp.pl", "test 1");
        Email e3 = new Email("123@wp.pl", "okno");
        Email e4 = new Email("567@wp.pl", "3test");
        List<Email> input = Arrays.asList(e1, e2, e3, e4);

        String keyword = req.getParameter("keywords");
        List<String> key = Arrays.asList(keyword);
        List<Email> results = verification.searchEmailByTitleWithKeyWords(key, input);

        System.out.println(results);

        req.setAttribute("results", results);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/App/form.jsp");
        dispatcher.forward(req, resp);
    }
}
