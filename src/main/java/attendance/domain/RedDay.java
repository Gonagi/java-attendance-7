package attendance.domain;

import static attendance.config.Constants.DECEMBER;
import static attendance.constants.Messages.INVALID_AT_RED_DAY;
import static attendance.utils.DateUtils.getTodayDayOfWeek;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum RedDay {
    ONE(1),
    SEVEN(7),
    EIGHT(8),
    FOURTEEN(14),
    FIFTEEN(15),
    TWENTY_ONE(21),
    TWENTY_TWO(22),
    TWENTY_FIVE(25),
    TWENTY_EIGHT(28),
    TWENTY_NINE(29);

    private final int day;
    private static final Set<Integer> RED_DAYS;

    static {
        RED_DAYS = EnumSet.allOf(RedDay.class).stream()
                .map(RedDay::getRedDay)
                .collect(Collectors.toSet());
    }

    RedDay(final int day) {
        this.day = day;
    }

    public static void isRedDay(final int day) {
        if (RED_DAYS.contains(day)) {
            throw new IllegalArgumentException(
                    String.format(INVALID_AT_RED_DAY.getErrorMessage(), DECEMBER, day, getTodayDayOfWeek()));
        }
    }

    public int getRedDay() {
        return day;
    }
}
