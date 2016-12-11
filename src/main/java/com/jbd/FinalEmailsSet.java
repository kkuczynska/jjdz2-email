package com.jbd;

import javax.ejb.Stateless;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Stateless
public class FinalEmailsSet {

    private ContentmentVerification contentmentVerification = new ContentmentVerification();
    private SearchCriteria searchCriteria = new SearchCriteria();

    public Set<Email> createUniqueEmailsSet(List<Email> emails) {

        Set<Email> emailsFinal = new LinkedHashSet<>();

        String getStartDate = searchCriteria.getSTARTDATE();
        List<String> getEmail = searchCriteria.getEMAIL();
        List<String> getKeywords = searchCriteria.getKEYWORDS();

        if(!(getEmail.size() == 1 && getEmail.get(0).equals(""))) {
            emailsFinal.addAll(contentmentVerification.searchEmailByName(getEmail, emails));
        }
        if(!("1111-01-01 00:00".equals(getStartDate))) {
            emailsFinal.addAll(contentmentVerification.searchEmailByDate(getStartDate, emails));
        }
        if(!(getKeywords.size() == 1 && getKeywords.get(0).equals(""))) {
            emailsFinal.addAll(contentmentVerification.searchEmailByTitleWithKeyWords(getKeywords, emails));
        }

        return emailsFinal;
    }
}
