package com.jbd;

import org.junit.Test;

import static org.junit.Assert.*;

public class SearchCriteriaValidatorTest {
    private static SearchCriteriaValidator searchCriteria = new SearchCriteriaValidator();
    private static String emptyEmail = "";
    private static String emailWithUnacceptableCharacters = "email#$%(*address@wp.pl";
    private static String emailWithoutDot = "adressemail@wp";
    private static String emailWithoutAt = "emailaddress.com";
    private static String exceededDayInStartDate = "56/12/2016";
    private static String exceededMonthInStartDate = "12/56/2016";
    private static String exceededYearInStartDate = "16/02/5698";
    private static String tooLongDayInStartDate = "123/12/2016";
    private static String tooLongMonthInStartDate = "12/123/2016";
    private static String tooLongYearInStartDate = "16/02/569825";
    private static String startDateInUnacceptableFormat = "16-02-2016";
    private static String exceededDayInEndDate = "56/12/2016";
    private static String exceededMonthInEndDate = "12/56/2016";
    private static String exceededYearInEndDate = "16/02/5698";
    private static String tooLongDayInEndDate = "123/12/2016";
    private static String tooLongMonthInEndDate = "12/123/2016";
    private static String tooLongYearInEndDate = "16/02/569825";
    private static String endDateInUnacceptableFormat = "16-02-2016";
    private static String startDayAfterEndDay_StartDate = "16/10/2016";
    private static String startDayAfterEndDay_EndDate = "10/10/2016";
    private static String startMonthAfterEndMonth_StartDate = "03/10/2016";
    private static String startMonthAfterEndMonth_EndDate = "03/03/2016";
    private static String startYearAfterEndYear_StartDate = "03/06/2017";
    private static String startYearAfterEndYear_EndDate = "03/06/2014";

    @Test
    public void emptyEmailReturnsTrue() throws Exception {
        assertEquals(true, searchCriteria.validateEmail(emptyEmail));
    }

    @Test
    public void emailWithUnacceptableCharactersReturnsFalse() throws Exception {
        assertEquals(false, searchCriteria.validateEmail(emailWithUnacceptableCharacters));
    }

    @Test
    public void emailWithoutDotReturnsFalse() throws Exception {
        assertEquals(false, searchCriteria.validateEmail(emailWithoutDot));
    }

    @Test
    public void emailWithoutAtReturnsFalse() {
        assertEquals(false, searchCriteria.validateEmail(emailWithoutAt));
    }


    @Test
    public void exceededDayInStartDateReturnsFalse() {
        assertEquals(false, searchCriteria.validateStartDate(exceededDayInStartDate));
    }

    @Test
    public void exceededMonthInStartDateReturnsFalse() {
        assertEquals(false, searchCriteria.validateStartDate(exceededMonthInStartDate));
    }

    @Test
    public void exceededYearInStartDateReturnsFalse() {
        assertEquals(false, searchCriteria.validateStartDate(exceededYearInStartDate));
    }

    @Test
    public void tooLongDayInStartDateReturnsFalse() {
        assertEquals(false, searchCriteria.validateStartDate(tooLongDayInStartDate));
    }

    @Test
    public void tooLongMonthInStartDateReturnsFalse() {
        assertEquals(false, searchCriteria.validateStartDate(tooLongMonthInStartDate));
    }

    @Test
    public void tooLongYearInStartDateReturnsFalse() {
        assertEquals(false, searchCriteria.validateStartDate(tooLongYearInStartDate));
    }

    @Test
    public void startDateInUnacceptableFormatReturnsFalse() {
        assertEquals(false, searchCriteria.validateStartDate(startDateInUnacceptableFormat));
    }


    @Test
    public void exceededDayInEndDateReturnsFalse() {
        assertEquals(false, searchCriteria.validateStartDate(exceededDayInEndDate));
    }

    @Test
    public void exceededMonthInEndDateReturnsFalse() {
        assertEquals(false, searchCriteria.validateStartDate(exceededMonthInEndDate));
    }

    @Test
    public void exceededYearInEndDateReturnsFalse() {
        assertEquals(false, searchCriteria.validateStartDate(exceededYearInEndDate));
    }

    @Test
    public void tooLongDayInEndDateReturnsFalse() {
        assertEquals(false, searchCriteria.validateStartDate(tooLongDayInEndDate));
    }

    @Test
    public void tooLongMonthInEndDateReturnsFalse() {
        assertEquals(false, searchCriteria.validateStartDate(tooLongMonthInEndDate));
    }

    @Test
    public void tooLongYearInEndDateReturnsFalse() {
        assertEquals(false, searchCriteria.validateStartDate(tooLongYearInEndDate));
    }

    @Test
    public void endDateInUnacceptableFormatReturnsFalse() {
        assertEquals(false, searchCriteria.validateStartDate(endDateInUnacceptableFormat));
    }


    @Test
    public void dayInStartDateAfterDayInEndDateReturnsFalse() {
        assertEquals(searchCriteria.validateStartDate(startDayAfterEndDay_EndDate),
                searchCriteria.validateStartDate(startDayAfterEndDay_StartDate));
    }

    @Test
    public void monthInStartDateAfterMonthInEndDateReturnsFalse() {
        assertEquals(searchCriteria.validateStartDate(startMonthAfterEndMonth_StartDate),
                searchCriteria.validateStartDate(startMonthAfterEndMonth_EndDate));
    }

    @Test
    public void yearInStartDateAfterYearInEndDateReturnsFalse() {
        assertEquals(searchCriteria.validateStartDate(startYearAfterEndYear_StartDate),
                searchCriteria.validateStartDate(startYearAfterEndYear_EndDate));
    }

}