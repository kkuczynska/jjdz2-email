package com.jbd.DBA;

import com.jbd.Authorization.SessionData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final Logger LOGGER = LogManager.getLogger(UpdateServlet.class);

    @Inject
    ManageUser manageUser;

    @Inject
    SessionData sessionData;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String[] isPrivileged = req.getParameterValues("isPrivileged");
        if (isPrivileged.length != 0) {
            System.out.println("Privileged " + isPrivileged.toString());
            for (int i = 0; i < isPrivileged.length; i++) {
                SessionData user = manageUser.getUser(Long.parseLong(isPrivileged[i]));
                String privileged = user.getPrivilege();
                if (privileged.equals("Admin")) {
                    user.setPrivilege("local");
                    LOGGER.info("Set privilege to: local");
                } else {
                    user.setPrivilege("Admin");
                    LOGGER.info("Set privilege to: Admin");
                }
                manageUser.updateUser(user);
            }
        }
        LOGGER.info("No parameters in request");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/App/AdminConsole.jsp");
        dispatcher.forward(req, resp);


    }
}
