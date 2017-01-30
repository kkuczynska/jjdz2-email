package com.jbd;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class DisplayPhoneNumbersTest {
    List<Email> eMailKeeper = Arrays.asList(new Email("marcin@wp.pl", "subject", "2015-01-01 00:00", "test 515-417-846"),
            new Email("marcin@wp.pl", "subject", "2015-01-01 00:00", "test 515417844"),
            new Email("marcin@wp.pl", "subject", "2015-01-01 00:00", "test 515 417 846"),
            new Email("wojtek@wp.pl", "subject", "2015-01-01 00:00", "test 616478366"),
            new Email("wojtek@wp.pl", "subject", "2015-01-01 00:00", "test 616478366"));

    private Map<String, List<String>> resultMap;
    private List<String> resultList;
    DisplayPhoneNumbers displayPhoneNumbers = new DisplayPhoneNumbers();

    @Test
    public void forTwoDifferentNumberAndOneTheSameShouldReturnListSize2() throws Exception {
        resultMap = displayPhoneNumbers.searchPhoneNumbers(eMailKeeper);
        resultList = resultMap.get("marcin@wp.pl");
        assertThat("Size of the list number is not equal 2! ", resultList.size(), is(2));
    }

    @Test
    public void forTheSamePhoneNumberShouldReturnSizeEqual1() throws Exception {
        resultMap = displayPhoneNumbers.searchPhoneNumbers(eMailKeeper);
        resultList = resultMap.get("wojtek@wp.pl");
        assertThat("Add the same phone number to List! Should be unique", resultList.size(), is(1));
    }


}