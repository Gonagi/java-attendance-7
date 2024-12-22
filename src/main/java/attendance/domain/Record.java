package attendance.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        AttendanceStatus attendanceStatus = AttendanceStatus.from(localDateTime);
        attendanceStatuses.add(attendanceStatus);
    }

    public String getNickName() {
        return nickName;
    }

    public List<AttendanceStatus> getAttendanceStatuses() {
        return attendanceStatuses;
    }
}
