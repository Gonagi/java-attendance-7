package attendance.controller;

import static attendance.utils.DateUtils.getLocalDateTime;
import static attendance.utils.DateUtils.getTodayDay;
import static attendance.utils.DateUtils.getTodayDayOfWeek;
import static attendance.utils.DateUtils.getTodayMonth;

import attendance.domain.AttendanceStatus;
import attendance.domain.Record;
import attendance.domain.RecordBook;
import attendance.domain.RedDay;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final RecordBook recordBook;

    public Controller(final InputView inputView, final OutputView outputView, final RecordBook recordBook) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.recordBook = recordBook;
    }

    public void run() {
        while (true) {
            outputView.printTodayAndFunctions(getTodayMonth(), getTodayDay(), getTodayDayOfWeek());
            String function = inputView.inputFunction();
            if (Objects.equals(function, "1")) {
                RedDay.isRedDay(getTodayDay());
                String nickName = inputView.inputNickNames();
                Record record = recordBook.findRecordByNickNameAtRecordBook(nickName);
                LocalTime time = inputView.inputAttendanceTime();
                LocalDateTime localDateTime = getLocalDateTime(getTodayDay(), time);
                record.registerAttendance(localDateTime);
                outputView.printConfirmAttendance(record.getAttendanceStatusByDay(getTodayDay()));
            } else if (Objects.equals(function, "2")) {
                String nickName = inputView.inputNickNames();
                Record record = recordBook.findRecordByNickNameAtRecordBook(nickName);
                int day = inputView.inputDay();
                AttendanceStatus oldAttendanceStatus = record.getAttendanceStatusByDay(day);
                LocalTime time = inputView.inputChangeTime();
                AttendanceStatus newAttendanceStatus = record.changeAttendanceStatusWithDayAndTime(day, time);
                outputView.printChangeAttendance(oldAttendanceStatus, newAttendanceStatus);
            } else if (Objects.equals(function, "3")) {
                String nickName = inputView.inputNickNames();
                Record record = recordBook.findRecordByNickNameAtRecordBook(nickName);
                outputView.printAttendanceStatusByNickName(record);
            } else if (Objects.equals(function, "4")) {
                List<Record> riskAtExpulsionPeople = recordBook.findRiskAtExpulsionPeople();
                outputView.printExpelledPeople(riskAtExpulsionPeople);
            } else if (Objects.equals(function, "Q")) {
                break;
            }
        }
    }
}
