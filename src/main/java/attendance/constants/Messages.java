package attendance.constants;

public enum Messages {
    // input
    INPUT_NICKNAME("닉네임을 입력해 주세요."),
    INPUT_ATTENDANCE_TIME("등교 시간을 입력해 주세요."),

    // output
    TODAY_AND_FUNCTIONS("오늘은 %d월 %02d일 %s입니다. 기능을 선택해 주세요.\n"
            + "1. 출석 확인\n"
            + "2. 출석 수정\n"
            + "3. 크루별 출석 기록 확인\n"
            + "4. 제적 위험자 확인\n"
            + "Q. 종료\n"),
    CONFIRM_ATTENDANCE("%d월 %02d일 %s %s (%s)"),

    // error
    INVALID_INPUT_FORMAT("잘못된 형식을 입력하였습니다."),
    NO_EXIST_NICKNAME("등록되지 않은 닉네임입니다."),
    INVALID_AT_RED_DAY("%d월 %02d일 %s은 등교일이 아닙니다."),
    INVALID_OPERATING_HOURS("[ERROR] 캠퍼스 운영 시간에만 출석이 가능합니다.");
    private final String message;

    Messages(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorMessage() {
        return "[ERROR] " + message;
    }
}
