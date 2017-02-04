package com.jbd.servlets;

import com.jbd.DBA.ManageUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "simpleReport")
public class DisplayReportServlet extends HttpServlet{
    private static final Logger LOGGER = LoggerFactory.getLogger(DisplayReportServlet.class);
    private static final Marker MARKER = MarkerFactory.getMarker("DisplayReportServlet");

    @Inject
    ManageUser manageUser;


}
