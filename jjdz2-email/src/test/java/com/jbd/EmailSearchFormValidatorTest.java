package com.jbd;

import com.jbd.searchEmails.EmailSearchFormValidator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmailSearchFormValidatorTest {
    private static EmailSearchFormValidator emailSearchFormValidator = new EmailSearchFormValidator();

    @Test
    public void empty_email_should_return_false() throws Exception {
        String emptyEmail = "";
        assertEquals(false, emailSearchFormValidator.validateEmail(emptyEmail));
    }

    @Test
    public void email_with_unacceptable_characters_should_return_false() throws Exception {
        String emailWithUnacceptableCharacters = "email#$%(*address@wp.pl";
        assertEquals(false, emailSearchFormValidator.validateEmail(emailWithUnacceptableCharacters));
    }

    @Test
    public void email_without_dot_should_return_false() throws Exception {
        String emailWithoutDot = "adressemail@wp";
        assertEquals(false, emailSearchFormValidator.validateEmail(emailWithoutDot));
    }

    @Test
    public void email_without_at_should_return_false() {
        String emailWithoutAt = "emailaddress.com";
        assertEquals(false, emailSearchFormValidator.validateEmail(emailWithoutAt));
    }


    @Test
    public void exceeded_day_in_startdate_should_return_false() {
        String exceededDayInStartDate = "2016-12-56";
        assertEquals(false, emailSearchFormValidator.validateStartDate(exceededDayInStartDate));
    }

    @Test
    public void exceeded_month_in_startdate_should_return_false() {
        String exceededMonthInStartDate = "2016-56-12";
        assertEquals(false, emailSearchFormValidator.validateStartDate(exceededMonthInStartDate));
    }

    @Test
    public void exceeded_year_in_startdate_should_return_false() {
        String exceededYearInStartDate = "5698-02-16";
        assertEquals(false, emailSearchFormValidator.validateStartDate(exceededYearInStartDate));
    }

    @Test
    public void too_long_day_in_startdate_should_return_false() {
        String tooLongDayInStartDate = "2016-12-123";
        assertEquals(false, emailSearchFormValidator.validateStartDate(tooLongDayInStartDate));
    }

    @Test
    public void too_long_month_in_startdate_should_return_false() {
        String tooLongMonthInStartDate = "2016-123-12";
        assertEquals(false, emailSearchFormValidator.validateStartDate(tooLongMonthInStartDate));
    }

    @Test
    public void too_long_year_in_startdate_should_return_false() {
        String tooLongYearInStartDate = "569825-02-16";
        assertEquals(false, emailSearchFormValidator.validateStartDate(tooLongYearInStartDate));
    }

    @Test
    public void startdate_in_unacceptable_format_should_return_false() {
        String startDateInUnacceptableFormat = "2016/02/16";
        assertEquals(false, emailSearchFormValidator.validateStartDate(startDateInUnacceptableFormat));
    }


    @Test
    public void exceeded_day_in_enddate_should_return_false() {
        String exceededDayInEndDate = "2016-12-56";
        assertEquals(false, emailSearchFormValidator.validateStartDate(exceededDayInEndDate));
    }

    @Test
    public void exceeded_month_in_enddate_should_return_false() {
        String exceededMonthInEndDate = "2016-56-02";
        assertEquals(false, emailSearchFormValidator.validateStartDate(exceededMonthInEndDate));
    }

    @Test
    public void exceeded_year_in_enddate_should_return_false() {
        String exceededYearInEndDate = "5698-02-16";
        assertEquals(false, emailSearchFormValidator.validateStartDate(exceededYearInEndDate));
    }

    @Test
    public void tooLongDayInEndDateReturnsFalse() {
        String tooLongDayInEndDate = "2016-12-123";
        assertEquals(false, emailSearchFormValidator.validateStartDate(tooLongDayInEndDate));
    }

    @Test
    public void too_long_month_in_enddate_should_return_false() {
        String tooLongMonthInEndDate = "2016-123-12";
        assertEquals(false, emailSearchFormValidator.validateStartDate(tooLongMonthInEndDate));
    }

    @Test
    public void too_long_year_in_enddate_should_return_false() {
        String tooLongYearInEndDate = "569825-02-16";
        assertEquals(false, emailSearchFormValidator.validateStartDate(tooLongYearInEndDate));
    }

    @Test
    public void enddate_in_unacceptable_format_should_return_false() {
        String endDateInUnacceptableFormat = "16-02-2016";
        assertEquals(false, emailSearchFormValidator.validateStartDate(endDateInUnacceptableFormat));
    }
}