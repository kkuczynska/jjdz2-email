package com.jbd.displayReport;

import com.jbd.database.ManageUser;
import org.slf4j.Logger;
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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "simpleReport")
public class DisplayReportServlet extends HttpServlet{
    private static final Logger LOGGER = LoggerFactory.getLogger(DisplayReportServlet.class);
    private static final Marker MARKER = MarkerFactory.getMarker("DisplayReportServlet");
    private Map<String, Long> toDisplay;
    private List<String> fromDatabase;

    @Inject
    ManageUser manageUser;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info(MARKER, "User requested report.");
        toDisplay = new TreeMap<>();
        fromDatabase = manageUser.getAllAddressee();
        toDisplay = fromDatabase
                .stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        req.setAttribute("displayReport", toDisplay);
        LOGGER.info(MARKER, "Report ready. Displaying: " + toDisplay.size() + " records.");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/simpleReport.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            LOGGER.debug(MARKER, "Caught ServletException " + e);
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.debug(MARKER, "Caught IOException " + e);
            e.printStackTrace();
        }
    }
}
