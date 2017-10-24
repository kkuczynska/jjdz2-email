package com.jbd.phoneNumbersSearch;

import com.jbd.searchEmails.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Stateless
public class SearchPhoneNumbersInEmails {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchPhoneNumbersInEmails.class);
    private static final Marker MARKER = MarkerFactory.getMarker("SearchPhoneNumbersInEmails");

    private String phonePattern = "(\\s\\d{3}[\\-,\\s]?\\d{3}[\\-,\\s]?\\d{3}\\b)"; // 515417888
    private Map<String, List<String>> phoneNumbersMap = new HashMap<>();

    public Map<String, List<String>> searchPhoneNumbers(List<Email> emailList) {
        Pattern patternPhone = Pattern.compile(phonePattern);
        emailList.forEach(email -> collectPhoneNumbers(phoneNumbersMap, email, patternPhone.matcher(email.getContent())));
        return phoneNumbersMap;
    }

    private void collectPhoneNumbers(Map<String, List<String>> resultMap, Email email, Matcher matcher) {
        while (matcher.find()) {
            String phoneNumber = replaceWrongCharactersInNumber(matcher.group());
            collectNumbersToMap(resultMap, email, phoneNumber);
        }
    }

    private void collectNumbersToMap(Map<String, List<String>> resultMap, Email email, String phoneNumber) {
        if (resultMap.containsKey(email.getFrom())) {
            if (!findDuplicatedNumbers(resultMap, phoneNumber)) {
                LOGGER.info(MARKER, "No duplicates found, adding number: " + phoneNumber + " for: " + email.getFrom());
                putElementToMap(resultMap, email, phoneNumber, resultMap.get(email.getFrom()));
            } else {
                LOGGER.info(MARKER, "Phone number: " + phoneNumber + " already exists");
            }
        } else {
            LOGGER.info(MARKER, "Added number: " + phoneNumber + " for user: " + email.getFrom());
            putElementToMap(resultMap, email, phoneNumber, new ArrayList<>());
        }
    }

    private void putElementToMap(Map<String, List<String>> resultMap, Email email, String phoneNumber, List<String> phoneNumberList) {
        phoneNumberList.add(phoneNumber);
        resultMap.put(email.getFrom(), phoneNumberList);
    }

    private boolean findDuplicatedNumbers(Map<String, List<String>> mapToSearch, String stringToSearch) {
        final boolean[] foundDuplicate = {false};
        mapToSearch.keySet().forEach(key -> {
            mapToSearch.get(key).forEach(value -> {
                if (value.equals(stringToSearch)) {
                    foundDuplicate[0] = true;
                }
            });
        });
        return foundDuplicate[0];
    }

    private String replaceWrongCharactersInNumber(String phoneNumber) {
        return phoneNumber.trim().replace(" ", "").trim().replace("-", "");
    }
}
