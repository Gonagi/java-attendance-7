package attendance.domain;

import static attendance.constants.Messages.DUPLICATE_ATTENDANCE;
import static attendance.constants.Messages.INVALID_INPUT_FORMAT;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Record {
    private final String nickName;
    private final List<AttendanceStatus> attendanceStatuses;

    private Record(final String nickName, final List<AttendanceStatus> attendanceStatuses) {
        this.nickName = nickName;
        this.attendanceStatuses = attendanceStatuses;
    }

    public static Record from(final String nickName) {
        return new Record(nickName, new ArrayList<>());
    }

    public void registerAttendance(final LocalDateTime localDateTime) {
        checkDuplicateAttendanceStatus(localDateTime.getDayOfMonth());
        AttendanceStatus attendanceStatus = AttendanceStatus.from(localDateTime);
        attendanceStatuses.add(attendanceStatus);
    }

    public AttendanceStatus getAttendanceStatusByDay(final int day) {
        return attendanceStatuses.stream()
                .filter(attendanceStatus -> Objects.equals(attendanceStatus.getDay(), day))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_INPUT_FORMAT.getErrorMessage()));
    }

    public AttendanceStatus changeAttendanceStatusWithDayAndTime(final int day, final LocalTime localTime) {
        AttendanceStatus oldAttendanceStatus = getAttendanceStatusByDay(day);
        attendanceStatuses.remove(oldAttendanceStatus);
        AttendanceStatus newAttendanceStatus = AttendanceStatus.from(
                LocalDateTime.of(LocalDate.of(2024, 12, day), localTime));
        attendanceStatuses.add(newAttendanceStatus);
        return newAttendanceStatus;
    }

    private void checkDuplicateAttendanceStatus(final int day) {
        attendanceStatuses.stream()
                .filter(attendanceStatus -> Objects.equals(attendanceStatus.getDay(), day))
                .findFirst()
                .ifPresent(attendanceStatus -> {
                    throw new IllegalArgumentException(DUPLICATE_ATTENDANCE.getErrorMessage());
                });
    }

    public String getNickName() {
        return nickName;
    }

    public List<AttendanceStatus> getAttendanceStatuses() {
        return attendanceStatuses;
    }
}
