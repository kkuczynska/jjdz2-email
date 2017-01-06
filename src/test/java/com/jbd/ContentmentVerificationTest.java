package com.jbd;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ContentmentVerificationTest {
    private List<Email> emailList = Arrays.asList(
            new Email("Marcin.bart@gmail.com", "tytul wazny", "2014-08-09 00:00", "test"), //2014-08-09
            new Email("Pawel@wp.pl", "tytul niewazny", "2016-01-13 00:00", "test2"),//2016-01-13
            new Email("Karolina@onet.pl", "tytul straszny", "Sat, 22 Dec 1990 02:32:43 +0100", "test3")//1990-12-22
    );

    ContentmentVerification cv = new ContentmentVerification();

    @Test
    public void should_return_email_with_correct_date() throws Exception {
        String date = "2013-01-01 00:00";
        String endDate = "2016-10-01 00:00";
        List<Email> resultList = cv.searchEmailByDate(date,endDate, emailList);
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