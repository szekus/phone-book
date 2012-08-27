package com.opensource.phonebook.shared;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

public class CalendarUtil
{
    public static final String DATE_FORMAT = "MM/dd/yyyy";
    public static final String TIME_FORMAT = "hh:mma"; // with AM / PM
    public static final String DATE_TIME_FORMAT = DATE_FORMAT + " " + TIME_FORMAT;
    private static final long MS_PER_SEC = 1000;
    private static final long MS_PER_MIN = MS_PER_SEC * 60;
    private static final long MS_PER_HOUR = MS_PER_MIN * 60;
    private static final long MS_PER_DAY = MS_PER_HOUR * 24;
    private static final long MS_PER_YEAR = MS_PER_DAY * 365;

    private static Date _startUsaDaylightSavingsTimeWWIIDate = null;
    private static Date _endUsaDaylightSavingsTimeWWIIDate = null;

    public static int getAge(Date dateOfBirth)
    {
        return getAge(dateOfBirth, null);
    }

    public static int getAge(Date dateOfBirth, Date dateEnd)
    {
        if (dateOfBirth == null)
        {
            return 0;
        }
        if (dateEnd == null)
        {
            dateEnd = new Date();
        }
        int result = (int) Math.ceil(((double) dateEnd.getTime() - dateOfBirth.getTime()) / MS_PER_YEAR);
        return result - 1;
    }

    public final static String getStringFromDate(Date date)
    {
        String dob = null;
        if (date != null)
        {
            dob = DateTimeFormat.getFormat(DATE_FORMAT).format(date).toLowerCase();
        }
        return dob;
    }

}
