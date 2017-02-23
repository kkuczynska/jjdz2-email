package com.jbd.database;

import com.jbd.authorization.SessionData;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/App/update")
public class UpdateServlet extends HttpServlet {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UpdateServlet.class);
    private static final Marker MARKER = MarkerFactory.getMarker("UpdateServlet");

    @Inject
    ManageUser manageUser;

    @Inject
    SessionData sessionData;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String[] isPrivileged = req.getParameterValues("isPrivileged");
            if (isPrivileged.length != 0) {
                System.out.println("Privileged " + isPrivileged.toString());
                for (int i = 0; i < isPrivileged.length; i++) {
                    SessionData user = manageUser.getUser(Long.parseLong(isPrivileged[i]));
                    int privileged = user.getPrivilege();
                    if (privileged == SessionData.ADMIN) {
                        user.setPrivilege(SessionData.LOCAL_USER);
                        LOGGER.info(MARKER, "Set privilege to: local");
                    } else {
                        user.setPrivilege(SessionData.ADMIN);
                        LOGGER.info(MARKER, "Set privilege to: Admin");
                    }
                    manageUser.updateUser(user);
                }
            }
            LOGGER.info(MARKER, "No parameters in request");
        }
        catch (NullPointerException e){
            e.printStackTrace();
            System.out.println("Brak parametrow");
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/App/AdminConsole.jsp");
        req.setAttribute("null", "No parameters!");
        dispatcher.forward(req, resp);


    }
}
