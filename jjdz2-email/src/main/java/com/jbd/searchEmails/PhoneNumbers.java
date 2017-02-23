package com.jbd.searchEmails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Stateless
public class PhoneNumbers {

    private static final Logger LOGGER = LoggerFactory.getLogger(PhoneNumbers.class);
    private static final Marker MARKER = MarkerFactory.getMarker("PhoneNumbers");

    private String message;

    public String setPhoneNumbersMessage(String phoneNumbers, Map<String, List<String>> resultMap, HttpServletRequest req) {
        if ("yes".equals(phoneNumbers)) {
            if (resultMap.size() == 0) {
                message = "No phone numbers found.";
            } else {
                message = "Phone numbers found in your email file:";
            }
            req.setAttribute("displayNumbers", resultMap);
            LOGGER.info(MARKER, "Set JSP attribute \"displayNumbers\".");
        }
        return message;
    }
}
