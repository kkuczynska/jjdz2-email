package com.jbd.cutEmails;

import com.jbd.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TwoMailsNoAnswer{
    private static final Logger LOGGER = LoggerFactory.getLogger(TwoMailsNoAnswer.class);
    private static final Marker TWOMAILSNOANSWER_MARKER = MarkerFactory.getMarker("TwoMailsNoAnswer");

    String userMail = "Angus.Hardie@malcolmhardie.com";
    public ArrayList<LocalDateTime> dates = new ArrayList<>();
    public ArrayList<String> from = new ArrayList<>();
    public List<String> adresses = new ArrayList<>();
    public LocalDateTime recieverMailDate;
    public LocalDateTime ourOneBeforeLastDate;

    public void addingAdressesToList(List<Email> eMailKeeper) {
        for (Email email : eMailKeeper) {
            adresses.add(email.getFrom());
        }
    }

    public void addingDatesToList(List<Email> eMailKeeper) {
        for (Email email : eMailKeeper) {
            dates.add(email.getData());
        }
        Collections.sort(dates);
    }

    public void removingUserMailFromList(List<Email> eMailKeeper) {
        for (Email e : eMailKeeper) {
            adresses.removeIf(user -> user.contains(userMail));
        }
    }

    public void decideIfTwoAnswers() {// This code waits for else feature
         // Boolean checkIf = recieverMailDate.isBefore(ourOneBeforeLastDate);
       // LOGGER.info(TWOMAILSNOANSWER_MARKER, "if true we send two emails and get no answer   :   " + checkIf);
    }
}


