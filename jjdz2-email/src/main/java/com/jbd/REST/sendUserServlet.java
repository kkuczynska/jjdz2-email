package com.jbd.REST;

import com.jbd.authorization.SessionData;
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
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = "/sendUserServlet")

public class sendUserServlet extends HttpServlet {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(sendUserServlet.class);
    private static final Marker MARKER = MarkerFactory.getMarker("ManageDB");

    @Inject
    SessionData sessionData;

    @Inject
    ManageUser manageUser;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<SessionData> usersListFromDB = new ArrayList<>();
        usersListFromDB = manageUser.searchForAll();
        LOGGER.info(MARKER, "Get users from DB");

        Response user = ClientBuilder.newClient()
                .target("http://localhost:8081/reporting/reportApi/users")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(usersListFromDB));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<SessionData> usersListFromDB = new ArrayList<>();
        usersListFromDB = manageUser.searchForAllWithoutID();

    }
}
