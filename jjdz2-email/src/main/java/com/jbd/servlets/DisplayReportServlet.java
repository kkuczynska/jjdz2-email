package com.jbd.servlets;

import com.jbd.DBA.ManageUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "simpleReport")
public class DisplayReportServlet extends HttpServlet{
    private static final Logger LOGGER = LoggerFactory.getLogger(DisplayReportServlet.class);
    private static final Marker MARKER = MarkerFactory.getMarker("DisplayReportServlet");

    @Inject
    ManageUser manageUser;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info(MARKER, "_");
        super.doGet(req, resp);

    }
}
