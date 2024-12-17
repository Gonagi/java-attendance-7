package attendance.view;

import static attendance.constants.Messages.TODAY_AND_FUNCTIONS;

public class OutputView {
    public void printTodayAndFunctions(final int month, final int day, final String dayOfWeek) {
        System.out.printf(TODAY_AND_FUNCTIONS.getMessage(), month, day, dayOfWeek);
    }
}
