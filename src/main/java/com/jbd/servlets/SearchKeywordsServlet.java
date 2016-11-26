//package com.jbd.servlets;
//
//import javax.ejb.EJB;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet(urlPatterns = "searchk")
//public class SearchKeywordsServlet extends HttpServlet{
//
//    @EJB
//
//
//    protected void doPost(HttpServletRequest req, HttpServletResponse response) {
//
//
//
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/searchk.jsp");
//        try {
//            dispatcher.forward(req, response);
//        } catch (ServletException e) {
//            //LOGGER
//        } catch (IOException e) {
//            //LOGGER
//        }
//    }
//}
