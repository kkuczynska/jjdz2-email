package com.jbd;

import com.jbd.cutEmails.FiveDaysNoAnswer;
import com.jbd.cutEmails.RudeWordsInContent;
import com.jbd.cutEmails.TwoMailsNoAnswer;
import com.jbd.fileManagement.FileParser;
import com.jbd.fileManagement.PathGetter;
import com.jbd.searchEmails.ContentmentVerification;
import com.jbd.phoneNumbersSearch.DisplayPhoneNumbers;
import com.jbd.searchEmails.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import java.time.LocalDateTime;
import java.util.*;

public class JBDemail {

    private static final Logger LOGGER = LoggerFactory.getLogger(JBDemail.class);
    private static final Marker MAIN_MARKER = MarkerFactory.getMarker("Main");

    public static void main(String[] args) throws Exception {
        ArrayList<String> content = new ArrayList<>();
        TwoMailsNoAnswer twoMailsNoAnswer = new TwoMailsNoAnswer();
        RudeWordsInContent rudeWordsInContent = new RudeWordsInContent();
        FiveDaysNoAnswer fiveDaysNoAnswer = new FiveDaysNoAnswer();
        List<Date> sortedEmailDatesInDate = new ArrayList<>();
        List<LocalDateTime> afterAllList = new ArrayList<>();


        List<String> filesInStrings;
        List<Email> eMailKeeper = new ArrayList<>();
        List<Email> partialEMailKeeper = new ArrayList<>();
        List<String> eMailToLookFor = new ArrayList<>();
        Questions questionForm = new Questions();
        DisplayPhoneNumbers displayPhoneNumbers = new DisplayPhoneNumbers();
        PathGetter pG = new PathGetter();
        FileParser fP = new FileParser();
        ContentmentVerification cV = new ContentmentVerification();
        Scanner scanner = new Scanner(System.in);
        String input;
        int quit = 0;
        int path = 0;
        int answer = 0;

        System.out.println("Hello, this is JBDemail!\n" +
                "Set path to your emails files and answer questions.");

        LOGGER.info(MAIN_MARKER,"Program started.");

        while (quit == 0) {
            if (answer == 0) {
                LOGGER.info(MAIN_MARKER,"Search questions not set.");
                System.out.println("----------\n" +
                        "Search questions not set\n" +
                        "----------");
            }
            if (path == 0) {
                LOGGER.info(MAIN_MARKER,"Files path not set.");
                System.out.println("----------\n" +
                        "Files path not set - emails not parsed\n" +
                        "----------");
            }
            if (path == 0) {
                LOGGER.info(MAIN_MARKER,"Displaying basic options for user.");
                System.out.println("Type:\n" +
                        "1 - to answer search questions\n" +
                        "2 - to set path to files\n" +
                        "q - to quit");
            } else if (answer == 0) {
                LOGGER.info(MAIN_MARKER,"Displaying limited options for user.");
                System.out.println("Type:\n" +
                        "1 - to answer search questions again\n" +
                        "2 - to set path to files again\n" +
                        "6 - to look for phone numbers in emails\n" +
                        "7 - to display parsed emails\n" +
                        "8 - to display filtered emails\n" +
                        "9 - to display number of filtered emails\n" +
                        "10 - to check if Some of emails were cut wierdly\n" +
                        "q - to quit");
            } else {
                LOGGER.info(MAIN_MARKER,"Displaying full options for user.");
                System.out.println("Type:\n" +
                        "1 - to answer search questions again\n" +
                        "2 - to set path to files again\n" +
                        "3 - to filter emails by sender\n" +
                        "4 - to filter emails by date\n" +
                        "5 - to filter emails by keywords\n" +
                        "6 - to look for phone numbers in emails\n" +
                        "7 - to display parsed emails\n" +
                        "8 - to display filtered emails\n" +
                        "9 - to display number of filtered emails\n" +
                        "10 - to check if some of emails were cut wierdly\n" +
                        "q - to quit");
            }

            input = scanner.nextLine();
            LOGGER.info(MAIN_MARKER,"Scanned user input.");

            if ("1".equals(input)) {
                LOGGER.info(MAIN_MARKER,"User picked option 1.");
                questionForm.searchCriteriaForm();
                answer = 1;
            }
            if ("2".equals(input)) {
                LOGGER.info(MAIN_MARKER,"User picked option 2.");
                filesInStrings = pG.createFileListFromPath(pG.askUserAboutInputPath());
                LOGGER.info(MAIN_MARKER,"Found: " + pG.getFileList().size() + " files.");
                eMailKeeper = fP.parseEmails(filesInStrings);
                LOGGER.info(MAIN_MARKER,"Total emails parsed: " + eMailKeeper.size());
                partialEMailKeeper = eMailKeeper;
                path = 1;
            }
            if ("q".equals(input)) {
                LOGGER.info(MAIN_MARKER,"User picked option q. Program exit.");
                quit = 1;
            }
            if ("3".equals(input) && answer != 0) {
                LOGGER.info(MAIN_MARKER,"User picked option 3.");
                eMailToLookFor.addAll((SearchCriteria.getEmail()));
                partialEMailKeeper = cV.searchEmailByName(eMailToLookFor, partialEMailKeeper);
            }
            if ("4".equals(input) && answer != 0) {
                LOGGER.info(MAIN_MARKER,"User picked option 4.");
                String dateOfEmailToLookFor = SearchCriteria.getStartDate();
                String endDateOfEmailToLookFor = SearchCriteria.getEndDate();
                partialEMailKeeper = cV.searchEmailByDate(dateOfEmailToLookFor, endDateOfEmailToLookFor, partialEMailKeeper);
            }
            if ("5".equals(input) && answer != 0) {
                LOGGER.info(MAIN_MARKER,"User picked option 5.");
                partialEMailKeeper = cV.searchEmailByTitleWithKeyWords(SearchCriteria.getKeywords(), partialEMailKeeper);
            }
            if ("6".equals(input) && path != 0) {
                LOGGER.info(MAIN_MARKER,"User picked option 6.");
                System.out.println(displayPhoneNumbers.searchPhoneNumbers(partialEMailKeeper));
            }
            if ("7".equals(input) && path != 0) {
                LOGGER.info(MAIN_MARKER,"User picked option 7.");
                printEmails(eMailKeeper);
            }
            if ("8".equals(input) && path != 0) {
                LOGGER.info(MAIN_MARKER,"User picked option 8.");
                printEmails(partialEMailKeeper);
            }
            if ("9".equals(input) && path != 0) {
                LOGGER.info(MAIN_MARKER,"User picked option 9.");
                System.out.println(partialEMailKeeper.size());
            }
            if ("10".equals(input) && path != 0) {
                LOGGER.info(MAIN_MARKER,"User picked option 10.");
                fiveDaysNoAnswer.dateSort(eMailKeeper);
                fiveDaysNoAnswer.LocalDateTimeToDateParse();
                fiveDaysNoAnswer.erasingFreeDaysFromDates(sortedEmailDatesInDate);
                fiveDaysNoAnswer.dateToLocalDateTimeParse();
                fiveDaysNoAnswer.checkIfWasAnswer(afterAllList);
                fiveDaysNoAnswer.chceckIfContentBetween();

                twoMailsNoAnswer.addingAdressesToList(eMailKeeper);
                twoMailsNoAnswer.addingDatesToList(eMailKeeper);
                twoMailsNoAnswer.removingUserMailFromList(eMailKeeper);
                twoMailsNoAnswer.decideIfTwoAnswers();

                rudeWordsInContent.iteratingThroughList(eMailKeeper);
                rudeWordsInContent.ifRudeWord(content);
            }
        }
    }
    public static void printEmails(List<Email> list) {
        for (Email email : list) {

            System.out.println("--------------------");
            System.out.println(email.getData());
            System.out.println(email.toString());
            System.out.println(email.getContent());
        }
    }
}
