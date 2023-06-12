package kr.ac.cbnu.saengsaengyaktong.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static Date getTodayDate() {
        final Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date getDatePart(Date value) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(value);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }
}
