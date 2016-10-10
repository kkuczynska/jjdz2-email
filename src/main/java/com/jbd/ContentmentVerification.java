package com.jbd;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.*;

public class ContentmentVerification {

    private List<Email> foundEmailsList;

    public List<Email> searchEmailByDate(String startDateOfEmailToSearch, List<Email> mailListToSearch) { //startDateOfEmailToSearch musi być w formacie "yyyy-MM-dd"

        foundEmailsList = new ArrayList<>();
        for (Email email : mailListToSearch) {


            LocalDate date = LocalDate.parse(startDateOfEmailToSearch);
            if (email.getData().isAfter(date)) {
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
