package com.starwarcasting.util;

import org.junit.Test;

import static com.starwarcasting.util.DateUtils.DF_DD_MMM_YYYY_HH_MM;
import static com.starwarcasting.util.DateUtils.DF_YYYY_MM_DD_T_HH_MM_SS_SSS;
import static org.junit.Assert.assertEquals;

public class DateUtilTest {

    /**
     * This is used check whether Date is converted into appropriate format or not
     */
    @Test
    public void testDates() {
        assertEquals(new DateUtils().getFormattedDate("2014-12-09T13:50:51.644000Z",
                DF_YYYY_MM_DD_T_HH_MM_SS_SSS, DF_DD_MMM_YYYY_HH_MM),
                "09 Dec, 2014 14:01");
    }
}