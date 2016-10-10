package com.jbd;

import org.junit.Test;
import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class ContentmentVerificationTest {

    private List<Email> emailList = Arrays.asList(
            new Email("Marcin.bart@gmail.com", "tytul wazny", "2014-08-09", "test"),
            new Email("Pawel@wp.pl", "tytul niewazny", "2016-01-13", "test2"),
            new Email("Karolina@onet.pl", "tytul straszny", "1990-12-22", "test3")
    );

    ContentmentVerification cv = new ContentmentVerification();


    @Test
    public void should_return_email_with_correct_date() throws Exception {
        String date = "2013-01-01";
        List<Email> resultList = cv.searchEmailByDate(date, emailList);
        assertThat("Given data is later then data in List", resultList.size(), is(2));
    }

    @Test
    public void sholud_return_email_with_correct_email_by_name() throws Exception {
        List<String> emailString = Arrays.asList(
                new String("Marcin.bart@gmail.com"),
                new String("Karolina@onet.pl")
        );
        List<Email> resultList = cv.searchEmailByName(emailString,emailList);
        assertThat("Given emails are wrong", resultList.size(), is(2));

    }

    @Test
    public void should_return_email_with_keywords_in_title() throws Exception {
        List<String> keyWords = Arrays.asList(
                new String("wazny"),
                new String("straszny")
        );
        List<Email> resultList = cv.searchEmailByTitleWithKeyWords(keyWords,emailList);
        assertThat("Returned wrong Titles!", resultList.size(), is(2));

    }

}