package attendance.utils;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public final class DateUtils {
    private DateUtils() {
    }

    public static String getTodayDayOfWeek() {
        LocalDateTime now = getLocalDateTime();
        DayOfWeek dayOfWeek = now.getDayOfWeek();

        return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN);
    }

    public static boolean checkDayOfWeekMonday(final LocalDateTime localDateTime) {
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        String displayName = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN);
        return displayName.equals("월요일");
    }

    public static int getTodayMonth() {
        LocalDateTime now = getLocalDateTime();
        Month month = now.getMonth();

        return month.getValue();
    }

    public static int getTodayDay() {
        LocalDateTime now = getLocalDateTime();
        return now.getDayOfMonth();
    }

    private static LocalDateTime getLocalDateTime() {
        return DateTimes.now();
    }
}
