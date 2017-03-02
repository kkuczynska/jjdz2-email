package com.jbd.REST;

import com.jbd.Report;
import com.jbd.authorization.SessionData;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@WebServlet(urlPatterns = "/App/createReport")
public class ReportServlet extends HttpServlet {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ReportServlet.class);
    private static final Marker MARKER = MarkerFactory.getMarker("ReportServlet");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8081/reporting/reportApi/")
                .path("users")
                .path("name");
        Response response1 = target.request()
                .get();
        LOGGER.info(MARKER, "Connected to http://localhost:8081/reporting/reportApi/users/name ");
        List<Report> reportList = new ArrayList<>();

        Report[] reports = response1.readEntity(Report[].class);

        for (int i =0; i < reports.length; i++ ){
         reportList.add(reports[i]);
        }
        LOGGER.info(MARKER, "Created successfully Report List From JSON");

        request.setAttribute("reportList", reportList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/App/AdminConsole.jsp");
        dispatcher.forward(request,response);


    }
}

