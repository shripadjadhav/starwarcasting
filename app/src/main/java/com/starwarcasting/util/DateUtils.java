package com.starwarcasting.util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * This is date utility class and is used to perform date related operations
 *
 * @author Shripad
 */
public class DateUtils {

    /**
     * This is constant variable to store date format
     * e.g 07 Oct, 2018 13:30
     */
    public static final String DF_DD_MMM_YYYY_HH_MM = "dd MMM, yyyy HH:mm";

    /**
     * This is constant variable to store date format
     * e.g 2018-10-07T13:30.123z
     */
    public static final String DF_YYYY_MM_DD_T_HH_MM_SS_SSS = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    /**
     * This is used to convert date from one format to another format
     */
    public String getFormattedDate(String strDate, String fromFormat, String toFormat) {
        try {
            SimpleDateFormat sdfFrom = new SimpleDateFormat(fromFormat, Locale.ENGLISH);
            Date date = sdfFrom.parse(strDate);
            SimpleDateFormat sdfTo = new SimpleDateFormat(toFormat, Locale.ENGLISH);
            return sdfTo.format(date);
        } catch (Exception e) {
            Log.e("getFormattedDate", e + "");
        }
        return "";
    }

}
