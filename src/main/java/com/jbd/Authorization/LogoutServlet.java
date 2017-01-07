package com.jbd.Authorization;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(LogoutServlet.class);
    @Inject
    SessionData sessionData;

    @Inject
    FBConnection fbConnection;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sessionData.logout();
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
            fbConnection.clearAccessToken();
            System.out.println(sessionData.toString());
            LOGGER.info("User has been logout");
        }
        else
            LOGGER.info("No active session!");

        response.sendRedirect("/jbdee/App/Bye.jsp");

    }
}
