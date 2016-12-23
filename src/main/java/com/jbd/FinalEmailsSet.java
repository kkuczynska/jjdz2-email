package com.jbd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.ejb.Stateless;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Stateless
public class FinalEmailsSet {

    private static final Logger LOGGER = LoggerFactory.getLogger(FinalEmailsSet.class);
    private static final Marker MARKER = MarkerFactory.getMarker("FinalEmailsSet");

    private ContentmentVerification contentmentVerification = new ContentmentVerification();
    private SearchCriteria searchCriteria = new SearchCriteria();

    public Set<Email> createUniqueEmailsSet(List<Email> emails) {

        Set<Email> emailsFinal = new LinkedHashSet<>();
        emailsFinal.clear();
        LOGGER.info(MARKER, "Cleared Emails Set");

        String getStartDate = searchCriteria.getSTARTDATE();
        List<String> getEmail = searchCriteria.getEMAIL();
        List<String> getKeywords = searchCriteria.getKEYWORDS();

        if(!(getEmail.size() == 1 && getEmail.get(0).equals(""))) {
            emailsFinal.addAll(contentmentVerification.searchEmailByName(getEmail, emails));
            LOGGER.info(MARKER, "Added emails found by email address to set.");
        }
        if(!("1111-01-01 00:00".equals(getStartDate))) {
            emailsFinal.addAll(contentmentVerification.searchEmailByDate(getStartDate, emails));
            LOGGER.info(MARKER, "Added emails found by start date to set.");
        }
        if(!(getKeywords.size() == 1 && getKeywords.get(0).equals(""))) {
            emailsFinal.addAll(contentmentVerification.searchEmailByTitleWithKeyWords(getKeywords, emails));
            LOGGER.info(MARKER, "Added emails found by keywords to set.");
        }
        LOGGER.info(MARKER, "Final email set completed.");
        return emailsFinal;
    }
}
