package com.jbd.servlets;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "searche")
public class SearchEmailsServlet {

    @EJB


    protected void doPost(HttpServletRequest req, HttpServletResponse response) {

        String email = req.getParameter("email");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String keywords =  req.getParameter("keywords");



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
