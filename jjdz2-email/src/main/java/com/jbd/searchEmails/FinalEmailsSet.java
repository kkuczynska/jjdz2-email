package com.jbd.searchEmails;

import com.jbd.EmailSearchForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.ejb.Stateless;
import java.util.*;

@Stateless
public class FinalEmailsSet {

    private static final Logger LOGGER = LoggerFactory.getLogger(FinalEmailsSet.class);
    private static final Marker MARKER = MarkerFactory.getMarker("FinalEmailsSet");

    private ContentmentVerification contentmentVerification = new ContentmentVerification();
    private EmailSearchForm emailSearchForm = new EmailSearchForm();

    public Set<Email> createUniqueEmailsSet(List<Email> emails) {

        Set<Email> emailsFinal = new LinkedHashSet<>();
        List<Email> emailsFoundByEmail = new ArrayList<>();
        List<Email> emailsFoundByEmailAndDate = new ArrayList<>();

        String getStartDate = emailSearchForm.getStartDate();
        String getEndDate = emailSearchForm.getEndDate();
        List<String> getEmail = emailSearchForm.getEmail();
        List<String> getKeywords = emailSearchForm.getKeywords();

        if (1 == getEmail.size() && "".equals(getEmail.get(0))
                && "1111-01-01 00:00".equals(getStartDate) && "9999-12-12 00:00".equals(getEndDate)
                && 1 == getKeywords.size() && "".equals(getKeywords.get(0))) {
            emailsFinal.addAll(emails);
            LOGGER.info(MARKER, "No search criteria given. Displayed all " + emailsFinal.size() + " emails.");
        } else {
            if (!(1 == getEmail.size() && "".equals(getEmail.get(0)))) {
                emailsFoundByEmail.addAll(contentmentVerification.searchEmailByName(getEmail, emails));
                if (emailsFoundByEmail.size() > 0) {
                    LOGGER.info(MARKER, "Found " + emailsFoundByEmail.size() + " messages by email address.");
                }
                emailsFoundByEmailAndDate.addAll(contentmentVerification.searchEmailByDate(getStartDate, getEndDate, emailsFoundByEmail));
                if (emailsFoundByEmailAndDate.size() > 0) {
                    LOGGER.info(MARKER, "Filtered " + emailsFoundByEmailAndDate.size() + " messages by date.");
                }
                emailsFinal.addAll(contentmentVerification.searchEmailByTitleWithKeyWords(getKeywords, emailsFoundByEmailAndDate));
                if (emailsFinal.size() > 0) {
                    LOGGER.info(MARKER, "Filtered " + emailsFinal.size() + " messages by keywords.");
                }
            } else if (!("1111-01-01 00:00".equals(getStartDate) && "9999-12-12 00:00".equals(getEndDate))) {
                emailsFoundByEmailAndDate.addAll(contentmentVerification.searchEmailByDate(getStartDate, getEndDate, emails));
                if (emailsFoundByEmailAndDate.size() > 0) {
                    LOGGER.info(MARKER, "Found " + emailsFoundByEmailAndDate.size() + " messages by date.");
                }
                emailsFinal.addAll(contentmentVerification.searchEmailByTitleWithKeyWords(getKeywords, emailsFoundByEmailAndDate));
                if (emailsFinal.size() > 0) {
                    LOGGER.info(MARKER, "Filtered " + emailsFinal.size() + " messages by keywords.");
                }
            } else if (!(1 == getKeywords.size() && "".equals(getKeywords.get(0)))) {
                emailsFinal.addAll(contentmentVerification.searchEmailByTitleWithKeyWords(getKeywords, emails));
                if (emailsFinal.size() > 0) {
                    LOGGER.info(MARKER, "Found " + emailsFinal.size() + " messages by keywords.");
                }
            }
        }
        LOGGER.info(MARKER, "Final email set completed.");
        return emailsFinal;
    }

    public String joinStringsWithComma(List<String> listToParse) {
        StringJoiner sj = new StringJoiner(", ");
        listToParse.forEach(s -> {
            sj.add(s);
        });
        return sj.toString();
    }
}
