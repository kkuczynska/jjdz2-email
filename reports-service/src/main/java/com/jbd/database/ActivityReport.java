package com.jbd.database;

import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.*;

@RequestScoped
public class ActivityReport {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ActivityReport.class);
    private static final Marker MARKER = MarkerFactory.getMarker("LoginFBServlet");    private List<User> userList = new ArrayList<>();
    private Map<String, Long> userMap;
    List<Report> reportList = new ArrayList<>();

    @Inject
    ManageDB manageDB;

    public List<Report> generateReport() {
        userList = manageDB.searchForAll();

        Collections.sort(userList);
        LOGGER.info(MARKER,"Collection Sorted");

        int counter = 0;

        for (int i = 0; i < userList.size(); i++) {
            if (counter == 0) {
                reportList.add(new Report(userList.get(i), 1));
                LOGGER.info(MARKER, "Added first Record to Report");
                counter++;
            } else {
                if (searchUser(userList.get(i))) {
                    LOGGER.info(MARKER, "Found User! Increasing visit counter and changing Last login time");
                    int number = searchAndReturnUser(userList.get(i));
                    reportList.get(number).getUser().setLoginTime(userList.get(i).getLoginTime());
                    reportList.get(number).setCounter(reportList.get(number).getCounter()+1);
               }

                else {
                    LOGGER.info(MARKER, "Did not found user in Report. Creating new one...");
                    reportList.add(new Report(userList.get(i), 1));
                }

            }

        }

    return  reportList;
    }

    public Boolean searchUser(User user) {
        Boolean found = false;
        for (Report r : reportList) {
            LOGGER.debug(MARKER,"Report List " + reportList);
            if (r.getUser().getLoginTime().getDayOfMonth() == user.getLoginTime().getDayOfMonth() && r.getUser().getUsername().equals(user.getUsername())) {
                LOGGER.debug("Report date: " +r.getUser().getLoginTime().getDayOfMonth() );
                LOGGER.debug("User date: " +user.getLoginTime().getDayOfMonth());
                LOGGER.debug("Report User Name: + " +r.getUser().getUsername() ) ;
                LOGGER.debug("User Name: " + user.getUsername());
                found = true;
                break;

            }

        }
        return found;
    }

    public int searchAndReturnUser(User user) {
        for (int i = 0; i < reportList.size(); i++) {
            if (reportList.get(i).getUser().getLoginTime().getDayOfMonth() == user.getLoginTime().getDayOfMonth() && reportList.get(i).getUser().getUsername().equals(user.getUsername()))
                return i;
        }
        return 1;
    }

}
