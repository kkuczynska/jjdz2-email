package com.jbd.DBA;

import com.jbd.authorization.SessionData;
import com.jbd.ContentmentVerification;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = "/App/search")
public class AdminUsers extends HttpServlet {

    @Inject
    ManageUser manageUser;

    @EJB
    ContentmentVerification verification;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<SessionData> userList = new ArrayList<>();
        userList = manageUser.searchForAll();

        req.setAttribute("userList", userList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/App/AdminConsole.jsp");
        dispatcher.forward(req, resp);



    }
}
