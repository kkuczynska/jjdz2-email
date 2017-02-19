package com.jbd.servlets;

import com.jbd.DBA.Addressee;
import com.jbd.DBA.ManageUser;
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
    private List<Addressee> fromDatabase;

    @Inject
    ManageUser manageUser;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.trace(MARKER, "User requested report");
        toDisplay = new TreeMap<>();
        fromDatabase = manageUser.getAllAddressee();
        toDisplay = fromDatabase
                .stream()
                .collect(Collectors.groupingBy(Addressee::getAddressee, Collectors.counting()));

        toDisplay.put("test",6L);

        req.setAttribute("displayReport", toDisplay);

        LOGGER.trace("Keys: " + toDisplay.keySet().size());
        LOGGER.trace(MARKER, "Keys: " + toDisplay.keySet().size());

        System.out.println("KEYS!!!!!! " + toDisplay.keySet().size());

        LOGGER.info(MARKER, "report");


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
        //super.doGet(req, resp);

    }
}
