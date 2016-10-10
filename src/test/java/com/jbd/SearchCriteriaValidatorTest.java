package com.jbd;

import org.junit.Test;

import static org.junit.Assert.*;

public class SearchCriteriaValidatorTest {
    private static SearchCriteriaValidator searchCriteria = new SearchCriteriaValidator();




    @Test
    public void empty_email_should_return_false() throws Exception {
        String emptyEmail = "";
        assertEquals(false, searchCriteria.validateEmail(emptyEmail));
    }

    @Test
    public void email_with_unacceptable_characters_should_return_false() throws Exception {
        String emailWithUnacceptableCharacters = "email#$%(*address@wp.pl";
        assertEquals(false, searchCriteria.validateEmail(emailWithUnacceptableCharacters));
    }

    @Test
    public void email_without_dot_should_return_false() throws Exception {
        String emailWithoutDot = "adressemail@wp";
        assertEquals(false, searchCriteria.validateEmail(emailWithoutDot));
    }

    @Test
    public void email_without_at_should_return_false() {
        String emailWithoutAt = "emailaddress.com";
        assertEquals(false, searchCriteria.validateEmail(emailWithoutAt));
    }


    @Test
    public void exceeded_day_in_startdate_should_return_false() {
        String exceededDayInStartDate = "56/12/2016";
        assertEquals(false, searchCriteria.validateStartDate(exceededDayInStartDate));
    }

    @Test
    public void exceeded_month_in_startdate_should_return_false() {
        String exceededMonthInStartDate = "12/56/2016";
        assertEquals(false, searchCriteria.validateStartDate(exceededMonthInStartDate));
    }

    @Test
    public void exceeded_year_in_startdate_should_return_false() {
        String exceededYearInStartDate = "16/02/5698";
        assertEquals(false, searchCriteria.validateStartDate(exceededYearInStartDate));
    }

    @Test
    public void too_long_day_in_startdate_should_return_false() {
        String tooLongDayInStartDate = "123/12/2016";
        assertEquals(false, searchCriteria.validateStartDate(tooLongDayInStartDate));
    }

    @Test
    public void too_long_month_in_startdate_should_return_false() {
        String tooLongMonthInStartDate = "12/123/2016";
        assertEquals(false, searchCriteria.validateStartDate(tooLongMonthInStartDate));
    }

    @Test
    public void too_long_year_in_startdate_should_return_false() {
        String tooLongYearInStartDate = "16/02/569825";
        assertEquals(false, searchCriteria.validateStartDate(tooLongYearInStartDate));
    }

    @Test
    public void startdate_in_unacceptable_format_should_return_false() {
        String startDateInUnacceptableFormat = "16-02-2016";
        assertEquals(false, searchCriteria.validateStartDate(startDateInUnacceptableFormat));
    }


    @Test
    public void exceeded_day_in_enddate_should_return_false() {
        String exceededDayInEndDate = "56/12/2016";
        assertEquals(false, searchCriteria.validateStartDate(exceededDayInEndDate));
    }

    @Test
    public void exceeded_month_in_enddate_should_return_false() {
        String exceededMonthInEndDate = "12/56/2016";
        assertEquals(false, searchCriteria.validateStartDate(exceededMonthInEndDate));
    }

    @Test
    public void exceeded_year_in_enddate_should_return_false() {
        String exceededYearInEndDate = "16/02/5698";
        assertEquals(false, searchCriteria.validateStartDate(exceededYearInEndDate));
    }

    @Test
    public void tooLongDayInEndDateReturnsFalse() {
        String tooLongDayInEndDate = "123/12/2016";
        assertEquals(false, searchCriteria.validateStartDate(tooLongDayInEndDate));
    }

    @Test
    public void too_long_month_in_enddate_should_return_false() {
        String tooLongMonthInEndDate = "12/123/2016";
        assertEquals(false, searchCriteria.validateStartDate(tooLongMonthInEndDate));
    }

    @Test
    public void too_long_year_in_enddate_should_return_false() {
        String tooLongYearInEndDate = "16/02/569825";
        assertEquals(false, searchCriteria.validateStartDate(tooLongYearInEndDate));
    }

    @Test
    public void enddate_in_unacceptable_format_should_return_false() {
        String endDateInUnacceptableFormat = "16-02-2016";
        assertEquals(false, searchCriteria.validateStartDate(endDateInUnacceptableFormat));
    }


    @Test
    public void day_in_startdate_after_day_in_enddate_should_return_false() {
        String startDayAfterEndDay_StartDate = "16/10/2016";
        String startDayAfterEndDay_EndDate = "10/10/2016";
        assertEquals(searchCriteria.validateStartDate(startDayAfterEndDay_EndDate),
                searchCriteria.validateStartDate(startDayAfterEndDay_StartDate));
    }

    @Test
    public void month_in_startdate_after_month_in_enddate_should_return_false() {
        String startMonthAfterEndMonth_StartDate = "03/10/2016";
        String startMonthAfterEndMonth_EndDate = "03/03/2016";

        assertEquals(searchCriteria.validateStartDate(startMonthAfterEndMonth_StartDate),
                searchCriteria.validateStartDate(startMonthAfterEndMonth_EndDate));
    }

    @Test
    public void year_in_startdate_after_year_in_enddate_should_return_false() {
        String startYearAfterEndYear_StartDate = "03/06/2017";
        String startYearAfterEndYear_EndDate = "03/06/2014";

        assertEquals(searchCriteria.validateStartDate(startYearAfterEndYear_StartDate),
                searchCriteria.validateStartDate(startYearAfterEndYear_EndDate));
    }

}