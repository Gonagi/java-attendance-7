package attendance.domain;

import static attendance.constants.Constants.COMMA;
import static attendance.constants.Messages.NO_EXIST_NICKNAME;

import attendance.utils.FileUtils;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class RecordBook {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final Set<Record> records;

    private RecordBook(final Set<Record> records) {
        this.records = records;
    }

    public static RecordBook from(final String filePath) throws FileNotFoundException {
        Set<Record> records = new HashSet<>();
        List<String> info = FileUtils.readLinesFromFile(filePath);
        info.removeFirst();
        registerNickName(records, info);
        registerAttendanceStatus(records, info);
        return new RecordBook(records);
    }

    private static void registerNickName(final Set<Record> records, final List<String> info) {
        for (String line : info) {
            String[] split = line.split(COMMA);
            String nickname = split[0];
            Record record = Record.from(nickname);
            records.add(record);
        }
    }

    private static void registerAttendanceStatus(final Set<Record> records, final List<String> info) {
        for (String line : info) {
            String[] split = line.split(COMMA);
            String nickname = split[0];
            LocalDateTime localDateTime = LocalDateTime.parse(split[1], DATE_TIME_FORMATTER);
            Record recordByNickName = findRecordByNickName(records, nickname);
            recordByNickName.registerAttendance(localDateTime);
        }
    }

    private static Record findRecordByNickName(final Set<Record> records, final String nickName) {
        return records.stream()
                .filter(record -> Objects.equals(record.getNickName(), nickName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_EXIST_NICKNAME.getErrorMessage()));
    }

    public Record findRecordByNickNameAtRecordBook(final String nickName) {
        return records.stream()
                .filter(record -> Objects.equals(record.getNickName(), nickName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_EXIST_NICKNAME.getErrorMessage()));
    }
}

