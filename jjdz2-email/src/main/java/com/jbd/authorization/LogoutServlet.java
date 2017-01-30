package com.jbd.authorization;

import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LogoutServlet.class);
    private static final Marker MARKER = MarkerFactory.getMarker("LogoutServlet");
    @Inject
    SessionData sessionData;

    @Inject
    FBConnection fbConnection;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sessionData.logout();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            fbConnection.clearAccessToken();
            System.out.println(sessionData.toString());
            LOGGER.info(MARKER, "User has been logout");
        } else
            LOGGER.info(MARKER, "No active session!");

        response.sendRedirect("/jbdee/index.jsp");

    }
}
