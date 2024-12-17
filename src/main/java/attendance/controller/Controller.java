package attendance.controller;

import static attendance.utils.DateUtils.getTodayDay;
import static attendance.utils.DateUtils.getTodayDayOfWeek;
import static attendance.utils.DateUtils.getTodayMonth;

import attendance.view.InputView;
import attendance.view.OutputView;
import java.util.Objects;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {

        while (true) {
            outputView.printTodayAndFunctions(getTodayMonth(), getTodayDay(), getTodayDayOfWeek());
            String function = inputView.inputFunction();
            if (Objects.equals(function, "1")) {
                inputView.inputNickNames();
                inputView.inputAttendanceTime();
            } else if (Objects.equals(function, "2")) {

            } else if (Objects.equals(function, "3")) {

            } else if (Objects.equals(function, "4")) {

            } else if (Objects.equals(function, "Q")) {
                break;
            }
        }
    }
}
