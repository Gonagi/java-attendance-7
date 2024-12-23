package attendance.view;

import static attendance.constants.Messages.CONFIRM_ATTENDANCE;
import static attendance.constants.Messages.TODAY_AND_FUNCTIONS;

import attendance.domain.AttendanceStatus;

public class OutputView {
    public void printTodayAndFunctions(final int month, final int day, final String dayOfWeek) {
        System.out.printf(TODAY_AND_FUNCTIONS.getMessage(), month, day, dayOfWeek);
        System.out.println();
    }

    public void printConfirmAttendance(final AttendanceStatus attendanceStatus) {
        System.out.printf(CONFIRM_ATTENDANCE.getMessage(), attendanceStatus.getMonth(), attendanceStatus.getDay(),
                attendanceStatus.getDayOfWeek(), attendanceStatus.getTime(), attendanceStatus.getStatus());
    }
}
