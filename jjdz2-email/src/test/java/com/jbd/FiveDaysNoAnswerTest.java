package com.jbd;

import com.jbd.cutEmails.FiveDaysNoAnswer;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class FiveDaysNoAnswerTest extends FiveDaysNoAnswer {
    public List<Date> sortedEmailDatesInDate = new ArrayList<>();
    public List<LocalDateTime> afterAllList = new ArrayList<>();
    FiveDaysNoAnswer fdna1 = new FiveDaysNoAnswer();
    LocalDateTime sixDaysAgo = LocalDateTime.now().minusDays(6);

    @Test
    public void shouldReturnTrueIfListIsEmpty() throws Exception {
        boolean sut = fdna1.chceckIfContentBetween();
        assertThat("List is not empty", sut, is(true));
    }

    @Test
    public void shouldRemoveDatesFromListWhenItsBeforeFiveDaysAgo() throws Exception {
        afterAllList.add(sixDaysAgo);
        fdna1.checkIfWasAnswer(afterAllList);
        if (afterAllList.size() == 0) {
            afterAllList = null;
        }
        assertNull(afterAllList);
    }

    @Test
    public void shouldRemoveWeekendsFromList() throws Exception {
        Date saturday = new Date(777777777);
        Date sunday = new Date(888888888);
        sortedEmailDatesInDate.add(saturday);
        sortedEmailDatesInDate.add(sunday);
        fdna1.erasingFreeDaysFromDates(sortedEmailDatesInDate);
        if (sortedEmailDatesInDate.size() == 0) {
            sortedEmailDatesInDate = null;
        }
        assertNull(sortedEmailDatesInDate);
    }
}
