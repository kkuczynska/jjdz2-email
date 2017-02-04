package com.jbd;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Stateless
public class DisplayPhoneNumbers {
    private static final Logger LOGGER = LogManager.getLogger(JBDemail.class);

    String phonePattern = "(\\s\\d{3}[\\-,\\s]?\\d{3}[\\-,\\s]?\\d{3}\\b)"; // 515417888

    private Pattern patternPhone;

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

    private String returnFormattedPhoneNumber(String phoneNumber) {
        String formatedPhoneNumber;
        formatedPhoneNumber = phoneNumber.trim().replace(" ", "");
        if (formatedPhoneNumber.contains("-")) {
            formatedPhoneNumber = phoneNumber.trim().replace("-", "");
        }
        return formatedPhoneNumber;
    }

    public Map<String, List<String>> searchPhoneNumbers(List<Email> emailList) {
        Map<String, List<String>> resultMap = new TreeMap<>();
        patternPhone = Pattern.compile(phonePattern);
        boolean isDuplicate = false;

        for (Email email : emailList) {
            Matcher matcher = patternPhone.matcher(email.getContent());
            while (matcher.find()) {
                String phoneNumber = returnFormattedPhoneNumber(matcher.group());
                if (resultMap.isEmpty()) {
                    LOGGER.info("Map is empty, adding record");
                    List<String> phoneList = new ArrayList<>();
                    phoneList.add(phoneNumber.trim());
                    resultMap.put(email.getFrom(), phoneList);
                    LOGGER.info("Added record to map: " + resultMap.get(email.getFrom()) + " for user : " + email.getFrom());
                    } else {
                    if (resultMap.containsKey(email.getFrom())) {
                        LOGGER.info("Found User: " + email.getFrom());
                        isDuplicate = searchDuplicates(resultMap, phoneNumber);
                        if (!isDuplicate) {
                            LOGGER.info("No duplicates found, adding number: " + phoneNumber +" for: " +email.getFrom());
                            List<String> phoneNumberList;
                            phoneNumberList = resultMap.get(email.getFrom());
                            phoneNumberList.add(phoneNumber);
                            resultMap.put(email.getFrom(), phoneNumberList);
                        } else {
                            LOGGER.info("Phone number: " + phoneNumber + " already exists, no add");
                        }
                    } else {
                        LOGGER.info("New user: " + email.getFrom());
                        List<String> phoneList = new ArrayList<>();
                        phoneList.add(phoneNumber);
                        LOGGER.info("Added number: " + phoneNumber + " for user: " + email.getFrom());
                        resultMap.put(email.getFrom(), phoneList);
                    }

                }

            }


        }

        LOGGER.info("Results: " + resultMap.toString());
        System.out.println("Phone Numbers: ");
        System.out.println("---------------");
        for (String key : resultMap.keySet()) {
            System.out.print(key + ": " + "[ ");
            for (String value : resultMap.get(key)) {
                System.out.print("-"+ value + " " );
            }
            System.out.print("]");
            System.out.println();

        }
        return resultMap;
    }
}
