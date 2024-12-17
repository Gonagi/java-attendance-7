package attendance.view;

import static attendance.constants.Messages.INPUT_ATTENDANCE_TIME;
import static attendance.constants.Messages.INPUT_NICKNAME;
import static attendance.constants.Messages.INVALID_INPUT_FORMAT;

import camp.nextstep.edu.missionutils.Console;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Pattern;

public class InputView {
    private final String ANSWER_REGEX = "[1234Q]";
    private final String TIME_REGEX = "\\d{2}:\\d{2}";
    private final String NICKNAME_REGEX = "([ㄱ-ㅎ|ㅏ-ㅣ|가-힣]+,)*[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]+";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public String inputFunction() {
        String function = Console.readLine();
        validateAnswer(function);
        return function;
    }

    public String inputNickNames() {
        System.out.println(INPUT_NICKNAME.getMessage());
        String nickNames = Console.readLine();
        validateNickNames(nickNames);
        return nickNames;
    }

    public LocalTime inputAttendanceTime() {
        System.out.println(INPUT_ATTENDANCE_TIME.getMessage());
        String attendanceTime = Console.readLine();
        validateAttendanceTime(attendanceTime);
        return LocalTime.parse(attendanceTime, TIME_FORMATTER);
    }


    private void validateNickNames(final String input) {
        validateNull(input);
        if (!Pattern.matches(NICKNAME_REGEX, input)) {
            throw new IllegalArgumentException(INVALID_INPUT_FORMAT.getErrorMessage());
        }
    }

    private void validateAttendanceTime(final String input) {
        validateNull(input);
        if (!Pattern.matches(TIME_REGEX, input)) {
            throw new IllegalArgumentException(INVALID_INPUT_FORMAT.getErrorMessage());
        }
    }

    private void validateAnswer(final String answer) {
        validateNull(answer);
        if (!Pattern.matches(ANSWER_REGEX, answer)) {
            throw new IllegalArgumentException(INVALID_INPUT_FORMAT.getErrorMessage());
        }
    }

    private void validateNull(final String input) {
        Optional.ofNullable(input)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_INPUT_FORMAT.getErrorMessage()));
    }
}
