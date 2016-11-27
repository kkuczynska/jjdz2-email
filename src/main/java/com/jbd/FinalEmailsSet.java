package com.jbd;

import javax.ejb.Stateless;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateless
public class FinalEmailsSet {

    private Set<Email> emailsFinal = new HashSet<>();
    private ContentmentVerification contentmentVerification = new ContentmentVerification();
    private SearchCriteria searchCriteria = new SearchCriteria();

    public Set<Email> createUniqueEmailsSet(List<String> emailToFind, List<Email> emails) {

        String getStartdate = searchCriteria.getSTARTDATE();
        List<String> getEmail = searchCriteria.getEMAIL();
        List<String> getKeywords = searchCriteria.getKEYWORDS();
        if(!("".equals(getStartdate) || "1111-01-01".equals(getStartdate))) {
            emailsFinal.addAll(contentmentVerification.searchEmailByDate(getStartdate, emails));
        }
        if(!getEmail.isEmpty()) {
            emailsFinal.addAll(contentmentVerification.searchEmailByName(emailToFind, emails));
        }
        if(!getKeywords.isEmpty()) {
            emailsFinal.addAll(contentmentVerification.searchEmailByTitleWithKeyWords(getKeywords, emails));
        }

        return emailsFinal;
    }
}
