package com.jbd.authorization;

import com.jbd.database.ManageUser;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@WebServlet("/LoginFBServlet")
public class LoginFBServlet extends HttpServlet {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LoginFBServlet.class);
    private static final Marker MARKER = MarkerFactory.getMarker("LoginFBServlet");

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
    int privilege = 2;
    private boolean isNotInDB;

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        LOGGER.info(MARKER, "Trying to log in to FB");
        code = req.getParameter("code");
        if (code == null || code.equals("")) {
            throw new RuntimeException(
                    "ERROR: Didn't get code parameter in callback.");
        }
        LOGGER.info(MARKER, "Logging in FB has been successful");

        String accessToken = fbConnection.getAccessToken(code);
        LOGGER.debug(MARKER, "Access token: " + accessToken);
        fbGraph.setAccessToken(accessToken);

        String graph = fbGraph.getFBGraph();
        LOGGER.info(MARKER, "Generated FBGraph");
        Map<String, String> fbProfileData = fbGraph.getGraphData(graph);

        String userName = fbProfileData.get("first_name") + " " + fbProfileData.get("last_name");
        String userMail = fbProfileData.get("email");

        if (counter == 0) {
            SessionData adminUser = new SessionData();
            adminUser.setUsermail("marbar1812@gmail.com");
            adminUser.setUsername("Marcin Bartoszek");
            adminUser.setPrivilege(SessionData.ADMIN);
            adminUser.setLoginTime(LocalDateTime.now());
            manageUser.saveUser(adminUser);
            LOGGER.info(MARKER, "Added admin user");
            counter += 1;
        }
        usersFromDatabase = manageUser.searchForAll();
        for (SessionData user : usersFromDatabase) {
            if (user.getUsername().equals(userName) && user.getUsermail().equals(fbProfileData.get("email"))) {
                if (user.getPrivilege() == SessionData.ADMIN) {
                    privilege = SessionData.ADMIN;
                } else
                    privilege = SessionData.LOCAL_USER;
                isNotInDB = false;
                break;
            } else
                privilege = SessionData.LOCAL_USER;
            isNotInDB = true;
        }

        sessionData.login(code, fbProfileData.get("first_name") + " " + fbProfileData.get("last_name"), fbProfileData.get("email"), privilege);

        if (isNotInDB) {
            LOGGER.info(MARKER, "User is not in DB! Adding...");
            SessionData sessionData;
            sessionData = createUserToDB(userName, userMail, privilege);
            manageUser.saveUser(sessionData);
            LOGGER.info(MARKER, "Added succesfully- " + sessionData.getUsername());
        }

        sendJsonToReportSystem(createUserForReport(userName, userMail));


        LOGGER.info(MARKER, "Logged User: " + sessionData.getUsername() + " Privilege: " + sessionData.getPrivilege());
        LOGGER.debug(MARKER, "Session data :" + sessionData);

        String name = fbProfileData.get("first_name");

        if (sessionData.isLogged()) {
            req.setAttribute("name", name);
            res.sendRedirect("/jbdee/index.jsp");
        }


    }

    public SessionData createUserToDB(String userName, String userMail, int privilege) {
        SessionData user = new SessionData();
        user.setUsername(userName);
        user.setUsermail(userMail);
        user.setPrivilege(privilege);
        user.setLoginTime(LocalDateTime.now());
        return user;
    }

    public SessionData createUserForReport(String userName, String userMail) {
        SessionData user = new SessionData();
        user.setUsername(userName);
        user.setUsermail(userMail);
        user.setLoginTime(LocalDateTime.now());
        return user;
    }


    public void sendJsonToReportSystem(SessionData sessionData) {
        Response user = ClientBuilder.newClient()
                .target("http://localhost:8081/reporting/reportApi/users")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(sessionData));
    }


}



