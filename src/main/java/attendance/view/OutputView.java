package attendance.view;

import static attendance.constants.Messages.ATTENDANCE_STATUS_BY_NICKNAME;
import static attendance.constants.Messages.ATTENDANCE_STATUS_COUNT;
import static attendance.constants.Messages.ATTENDANCE_STATUS_WITH_ABSENT;
import static attendance.constants.Messages.ATTENDANCE_STATUS_WITH_SAFE_OR_LATE;
import static attendance.constants.Messages.CHANGE_ATTENDANCE_STATUS;
import static attendance.constants.Messages.CONFIRM_ATTENDANCE;
import static attendance.constants.Messages.EXPELLED_PERSON;
import static attendance.constants.Messages.INTERVIEWEE;
import static attendance.constants.Messages.RISK_AT_EXPELLED_PERSON;
import static attendance.constants.Messages.RISK_AT_EXPELLED_PERSON_DETAIL;
import static attendance.constants.Messages.TODAY_AND_FUNCTIONS;
import static attendance.constants.Messages.WARNING_PERSON;

import attendance.domain.AttendanceStatus;
import attendance.domain.Record;
import java.util.List;
import java.util.Objects;

public class OutputView {
    public void printTodayAndFunctions(final int month, final int day, final String dayOfWeek) {
        System.out.printf(TODAY_AND_FUNCTIONS.getMessage(), month, day, dayOfWeek);
        System.out.println();
    }

    public void printConfirmAttendance(final AttendanceStatus attendanceStatus) {
        System.out.printf(CONFIRM_ATTENDANCE.getMessage(), attendanceStatus.getMonth(), attendanceStatus.getDay(),
                attendanceStatus.getDayOfWeek(), attendanceStatus.getTime(), attendanceStatus.getStatus());
        System.out.println();
    }

    public void printChangeAttendance(final AttendanceStatus oldAttendanceStatus,
                                      final AttendanceStatus newAttendanceStatus) {
        System.out.printf(CHANGE_ATTENDANCE_STATUS.getMessage(), oldAttendanceStatus.getMonth(),
                oldAttendanceStatus.getDay(),
                oldAttendanceStatus.getDayOfWeek(), oldAttendanceStatus.getTime(), oldAttendanceStatus.getStatus(),
                newAttendanceStatus.getTime(), newAttendanceStatus.getStatus());
        System.out.println();
    }

    public void printAttendanceStatusByNickName(final Record record) {
        System.out.printf(ATTENDANCE_STATUS_BY_NICKNAME.getMessage(), record.getNickName());
        for (AttendanceStatus attendanceStatus : record.getAttendanceStatuses()) {
            if (Objects.equals(attendanceStatus.getTime(), "00:00")) {
                System.out.printf(ATTENDANCE_STATUS_WITH_ABSENT.getMessage(), attendanceStatus.getMonth(),
                        attendanceStatus.getDay(), attendanceStatus.getDayOfWeek(), attendanceStatus.getStatus());
                continue;
            }
            System.out.printf(ATTENDANCE_STATUS_WITH_SAFE_OR_LATE.getMessage(), attendanceStatus.getMonth(),
                    attendanceStatus.getDay(), attendanceStatus.getDayOfWeek(), attendanceStatus.getTime(),
                    attendanceStatus.getStatus());
        }
        System.out.println();
        System.out.printf(ATTENDANCE_STATUS_COUNT.getMessage(), record.getSafeCount(), record.getLateCount(),
                record.getAbsentCount());
        System.out.println();
        printInterviewee(record);
        System.out.println();
    }

    public void printExpelledPeople(final List<Record> expelledPeople) {
        System.out.println(RISK_AT_EXPELLED_PERSON.getMessage());
        for (Record record : expelledPeople) {
            System.out.printf(RISK_AT_EXPELLED_PERSON_DETAIL.getMessage(), record.getNickName(),
                    record.getRealAbsentCount(), record.getRealLateCount(), record.getExpelledStatus());
        }
        System.out.println();
    }

    private void printInterviewee(final Record record) {
        int absentCount = record.getAbsentCount();

        if (absentCount > 5) {
            System.out.println(EXPELLED_PERSON.getMessage());
        } else if (absentCount >= 3) {
            System.out.println(INTERVIEWEE.getMessage());
        } else if (absentCount == 2) {
            System.out.println(WARNING_PERSON.getMessage());
        }
    }
}
