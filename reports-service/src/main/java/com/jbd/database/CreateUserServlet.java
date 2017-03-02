package com.jbd.database;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;


@WebServlet("/create")
public class CreateUserServlet extends HttpServlet {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CreateUserServlet.class);
    private static final Marker MARKER = MarkerFactory.getMarker("CreateUserServlet");

    @Inject
    ManageDB manageDB;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Response user = ClientBuilder.newClient()
                .target("http://localhost:8080/jbdee/api/users")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get();

        List<User> userList = user.readEntity(List.class);

        LOGGER.info(MARKER, "Pobrano Użytkownika");
    }
}
