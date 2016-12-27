package com.jbd;

import javax.ejb.Stateless;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.*;

@Stateless
public class ContentmentVerification {
    private DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private List<Email> foundEmailsList;

    public List<Email> searchEmailByDate(String startDateOfEmailToSearch,String endDateOfEmailToSearch, List<Email> mailListToSearch) { //startDateOfEmailToSearch musi być w formacie "yyyy-MM-dd"

        foundEmailsList = new ArrayList<>();
        for (Email email : mailListToSearch) {
            LocalDateTime date = LocalDateTime.parse(startDateOfEmailToSearch,formatter1);
            LocalDateTime dateEnd = LocalDateTime.parse(endDateOfEmailToSearch,formatter1);
            if (email.getData().isAfter(date) && email.getData().isBefore(dateEnd)) {
                foundEmailsList.add(email);
            }
        }
        return foundEmailsList;
    }

    public List<Email> searchEmailByName(List<String> searchingMailList, List<Email> mailListToSearch) {
        foundEmailsList = new ArrayList<>();

        for (Email email : mailListToSearch) {
            for (String singleNameEmail : searchingMailList) {
                Pattern pattern = Pattern.compile(singleNameEmail);
                Matcher matcher = pattern.matcher(email.getFrom());
                if (matcher.find()) {
                    foundEmailsList.add(email);
                    continue;
                }
            }
        }
        return foundEmailsList;
    }

    public List<Email> searchEmailByTitleWithKeyWords(List<String> keyWordsListToSearchProperTitle, List<Email> mailListToSearch) {
        foundEmailsList = new ArrayList<>();

        for (Email email : mailListToSearch) {
            for (String item : keyWordsListToSearchProperTitle) {
                Pattern pattern = Pattern.compile("\\b" + item + "\\b");
                Matcher matcher = pattern.matcher(email.getSubject());
                if (matcher.find()) {
                    foundEmailsList.add(email);
                    break;
                }
            }
        }
        return foundEmailsList;
    }
}
