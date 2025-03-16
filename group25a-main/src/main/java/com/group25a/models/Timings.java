package com.group25a.models;

import java.sql.Time; // Ensure this is used for the time field.
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Timings {

    public static List<java.sql.Time> getTimings() {
        List<java.sql.Time> times = new ArrayList<>();
        // increment by 30 minutes from 8:00 AM to 5:00 PM
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        while (calendar.get(Calendar.HOUR_OF_DAY) < 17) {
            times.add(new Time(calendar.getTimeInMillis()));
            calendar.add(Calendar.MINUTE, 30);
        }

        return times;
    }

    public static java.sql.Time[] getTimingsAsArray() {
        List<java.sql.Time> times = getTimings();
        return times.toArray(new java.sql.Time[0]);
    }

}
