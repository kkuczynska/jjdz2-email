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

    public Map<String, List<String>> searchPhoneNumbers(List<Email> emailList) {
        Map<String, List<String>> resultMap = new HashMap<>();
        Pattern patternPhone = Pattern.compile(phonePattern);
        boolean isDuplicate = false;
        for (Email email : emailList) {
            Matcher matcher = patternPhone.matcher(email.getContent());
            while (matcher.find()) {
                String phoneNumber = replaceWrongCharactersInNumber(matcher.group());
                if (resultMap.containsKey(email.getFrom())) {
                    LOGGER.info(MARKER, "Found User: " + email.getFrom());
                    isDuplicate = searchDuplicates(resultMap, phoneNumber);
                    if (!isDuplicate) {
                        LOGGER.info(MARKER, "No duplicates found, adding number: " + phoneNumber + " for: " + email.getFrom());
                        List<String> phoneNumberList;
                        phoneNumberList = resultMap.get(email.getFrom());
                        phoneNumberList.add(phoneNumber);
                        resultMap.put(email.getFrom(), phoneNumberList);
                    } else {
                        LOGGER.info(MARKER, "Phone number: " + phoneNumber + " already exists, no add");
                    }
                } else {
                    LOGGER.info(MARKER, "New user: " + email.getFrom());
                    List<String> phoneList = new ArrayList<>();
                    phoneList.add(phoneNumber);
                    LOGGER.info(MARKER, "Added number: " + phoneNumber + " for user: " + email.getFrom());
                    resultMap.put(email.getFrom(), phoneList);
                }
            }
        }
        return resultMap;
    }

    private boolean searchDuplicates(Map<String, List<String>> mapToSearch, String stringTooSearch) {
        boolean foundDuplicate = false;
        for (String key : mapToSearch.keySet()) {
            for (String value : mapToSearch.get(key)) {
                if (value.trim().equals(stringTooSearch)) {
                    foundDuplicate = true;
                    break;
                }
            }
            if (foundDuplicate)
                break;
        }
        return foundDuplicate;
    }

    private String replaceWrongCharactersInNumber(String phoneNumber) {
        return phoneNumber.trim().replace(" ", "").trim().replace("-", "");
    }
}
