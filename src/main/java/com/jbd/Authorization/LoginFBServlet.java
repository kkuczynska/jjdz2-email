package com.jbd.Authorization;

import com.jbd.DBA.ManageUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginFBServlet")
public class LoginFBServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(LoginFBServlet.class);

    @Inject
    FBConnection fbConnection;

    @Inject
    FBGraph fbGraph;

    @Inject
    SessionData sessionData;

    @Inject
    ManageUser manageUser;

    private static final long serialVersionUID = 1L;
    private String code = "";
    private SessionData userFromDatabase;
    private List<SessionData> usersFromDatabase;
    private int counter = 0;
    String privilege = "local";
    private boolean isNotInDB;
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        LOGGER.info("Trying to log in to FB");
        code = req.getParameter("code");
        if (code == null || code.equals("")) {
            throw new RuntimeException(
                    "ERROR: Didn't get code parameter in callback.");
        }
        LOGGER.info("Logging in FB has been successful");

        String accessToken = fbConnection.getAccessToken(code);
        LOGGER.debug("Access token: " + accessToken);
        fbGraph.setAccessToken(accessToken);

        String graph = fbGraph.getFBGraph();
        LOGGER.info("Generated FBGraph");
        Map<String, String> fbProfileData = fbGraph.getGraphData(graph);

        if(counter == 0) {
            SessionData adminUser = new SessionData();
            adminUser.setUsermail("marbar1812@gmail.com");
            adminUser.setUsername("Marcin Bartoszek");
            adminUser.setPrivilege("Admin");
            manageUser.saveUser(adminUser);
            LOGGER.info("Added admin user");
            counter +=1;
        }
        usersFromDatabase = manageUser.searchForAll();
        for (SessionData user:usersFromDatabase) {
            String userName = fbProfileData.get("first_name") + " " + fbProfileData.get("last_name");
            if (user.getUsername().equals(userName) && user.getUsermail().equals(fbProfileData.get("email"))){
                if(user.getPrivilege().equals("Admin")){
                privilege = "Admin";
                }
                else
                privilege = "local";
                isNotInDB = false;
                break;
            }
            else
                privilege = "local";
                isNotInDB = true;
        }

        sessionData.login(code, fbProfileData.get("first_name") + " " + fbProfileData.get("last_name"), fbProfileData.get("email"), privilege);
        if(isNotInDB){
            LOGGER.info("User is not in DB! Adding...");
            String userName = fbProfileData.get("first_name") + " " + fbProfileData.get("last_name");
            String userMail = fbProfileData.get("email");
            SessionData sessionData = new SessionData();
            sessionData = createUserToDB(userName, userMail,privilege);
            manageUser.saveUser(sessionData);
            LOGGER.info("Added succesfully- " + sessionData.getUsername());
        }
        LOGGER.info("Logged User: " + sessionData.getUsername());
        LOGGER.debug("Session data :" + sessionData);

        String name = fbProfileData.get("first_name");

        if (sessionData.isLogged()) {
            req.setAttribute("name", name);
            res.sendRedirect("/jbdee/App/AdminConsole.jsp");
        }


    }

    public SessionData createUserToDB(String userName, String userMail, String privilege){
        SessionData user = new SessionData();
        user.setUsername(userName);
        user.setUsermail(userMail);
        user.setPrivilege(privilege);
        return user;
    }


}



