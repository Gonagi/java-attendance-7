package attendance.constants;

public enum Messages {
    // input
    INPUT_NICKNAME("닉네임을 입력해 주세요."),
    INPUT_ATTENDANCE_TIME("등교 시간을 입력해 주세요."),
    INPUT_CHANGE_DAY("수정하려는 날짜(일)를 입력해 주세요."),
    INPUT_CHANGE_TIME("언제로 변경하겠습니까?"),

    // output
    TODAY_AND_FUNCTIONS("오늘은 %d월 %02d일 %s입니다. 기능을 선택해 주세요.\n"
            + "1. 출석 확인\n"
            + "2. 출석 수정\n"
            + "3. 크루별 출석 기록 확인\n"
            + "4. 제적 위험자 확인\n"
            + "Q. 종료\n"),
    CONFIRM_ATTENDANCE("%d월 %02d일 %s %s (%s)\n"),
    CHANGE_ATTENDANCE_STATUS("%d월 %02d일 %s %s (%s) -> %s (%s) 수정 완료!\n"),
    ATTENDANCE_STATUS_BY_NICKNAME("\n이번 달 %s의 출석 기록입니다.\n"),
    ATTENDANCE_STATUS_WITH_SAFE_OR_LATE("%d월 %02d일 %s %s (%s)\n"),
    ATTENDANCE_STATUS_WITH_ABSENT("%d월 %02d일 %s --:-- (%s)\n"),
    ATTENDANCE_STATUS_COUNT("출석: %d회\n"
            + "지각: %d회\n"
            + "결석: %d회\n"),
    WARNING_PERSON("경고 대상자입니다."),
    INTERVIEWEE("면담 대상자입니다."),
    EXPELLED_PERSON("제적 대상자입니다."),
    RISK_AT_EXPELLED_PERSON("제적 위험자 조회 결과"),
    RISK_AT_EXPELLED_PERSON_DETAIL("- %s: 결석 %d회, 지각 %d회 (%s)\n"),
    // error
    INVALID_INPUT_FORMAT("잘못된 형식을 입력하였습니다."),
    NO_EXIST_NICKNAME("등록되지 않은 닉네임입니다."),
    INVALID_AT_RED_DAY("%d월 %02d일 %s은 등교일이 아닙니다.\n"),
    INVALID_OPERATING_HOURS("캠퍼스 운영 시간에만 출석이 가능합니다."),
    DUPLICATE_ATTENDANCE("이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해주세요."),
    INVALID_FILE_PATH("파일 경로에 오류가 있습니다");

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
