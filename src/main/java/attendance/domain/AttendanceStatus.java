package attendance.domain;

import static attendance.constants.Messages.INVALID_OPERATING_HOURS;

import attendance.utils.DateUtils;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class AttendanceStatus {
    private static final String SAFE = "출석";
    private static final String LATE = "지각";
    private static final String ABSENCE = "결석";

    private final String status;
    private final LocalDateTime localDateTime;

    private AttendanceStatus(final String status, final LocalDateTime localDateTime) {
        this.status = status;
        this.localDateTime = localDateTime;
    }

    public static AttendanceStatus from(final LocalDateTime localDateTime) {
        RedDay.isRedDay(localDateTime.getDayOfMonth());
        int hour = localDateTime.getHour();
        int minute = localDateTime.getMinute();
        checkOperatingHours(hour);

        if (DateUtils.checkDayOfWeekMonday(localDateTime)) {
            return new AttendanceStatus(createAttendanceStatusAtMonday(hour, minute), localDateTime);
        }
        return new AttendanceStatus(createAttendanceStatusWithoutMonday(hour, minute), localDateTime);
    }

    private static void checkOperatingHours(final int hour) {
        if (hour < 8 || hour >= 23) {
            throw new IllegalArgumentException(INVALID_OPERATING_HOURS.getErrorMessage());
        }
    }

    private static String createAttendanceStatusAtMonday(final int hour, final int minute) {
        if (hour == 13 && (minute > 5 && minute <= 30)) {
            return LATE;
        }
        if ((hour == 13 && minute > 30) || hour >= 14) {
            return ABSENCE;
        }
        return SAFE;
    }

    private static String createAttendanceStatusWithoutMonday(final int hour, final int minute) {
        if (hour == 10 && (minute > 5 && minute <= 30)) {
            return LATE;
        }
        if ((hour == 10 && minute > 30) || hour >= 11) {
            return ABSENCE;
        }
        return SAFE;
    }

    public int getMonth() {
        return 12;
    }

    public int getDay() {
        return localDateTime.getDayOfMonth();
    }

    public String getDayOfWeek() {
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN);
    }

    public String getTime() {
        return String.format("%02d:%02d", localDateTime.getHour(), localDateTime.getMinute());
    }

    public String getStatus() {
        return status;
    }
}
