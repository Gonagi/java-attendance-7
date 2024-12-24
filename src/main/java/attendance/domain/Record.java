package attendance.domain;

import static attendance.constants.Constants.LATE;
import static attendance.constants.Constants.SAFE;
import static attendance.constants.Messages.DUPLICATE_ATTENDANCE;
import static attendance.constants.Messages.INVALID_INPUT_FORMAT;
import static attendance.utils.DateUtils.getLocalDateTime;
import static attendance.utils.DateUtils.getTodayDay;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
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
        AttendanceStatus newAttendanceStatus = AttendanceStatus.from(getLocalDateTime(day, localTime));
        attendanceStatuses.add(newAttendanceStatus);
        return newAttendanceStatus;
    }

    public void registerMissingAttendanceStatus() {
        for (int day = 1; day <= getTodayDay() - 1; day++) {
            if (!RedDay.checkRedDay(day) && !checkAttendanceStatusByDay(day)) {
                LocalTime fakeTime = LocalTime.of(0, 0);
                AttendanceStatus attendanceStatus = AttendanceStatus.from(getLocalDateTime(day, fakeTime));
                attendanceStatuses.add(attendanceStatus);
            }
        }
        attendanceStatuses.sort(Comparator.comparingInt(AttendanceStatus::getDay));
    }

    public int getSafeCount() {
        return (int) attendanceStatuses.stream()
                .filter(attendanceStatus -> Objects.equals(attendanceStatus.getStatus(), SAFE))
                .count();
    }

    public int getRealLateCount() {
        return (int) attendanceStatuses.stream()
                .filter(attendanceStatus -> Objects.equals(attendanceStatus.getStatus(), LATE))
                .count();
    }

    public int getRealAbsentCount() {
        return (int) attendanceStatuses.stream()
                .filter(attendanceStatus -> Objects.equals(attendanceStatus.getStatus(), "결석"))
                .count();
    }

    public int getLateCount() {
        return getRealLateCount() % 3;
    }

    public int getAbsentCount() {
        int lateCount = getRealLateCount();
        int absentCount = getRealAbsentCount();
        return absentCount + lateCount / 3;
    }

    public String getExpelledStatus() {
        if (getAbsentCount() > 5) {
            return "제적";
        } else if (getAbsentCount() >= 3) {
            return "면담";
        }
        return "경고";
    }

    private boolean checkAttendanceStatusByDay(final int day) {
        return attendanceStatuses.stream()
                .anyMatch(attendanceStatus -> Objects.equals(attendanceStatus.getDay(), day));
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Record record = (Record) o;
        return Objects.equals(nickName, record.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickName);
    }
}
