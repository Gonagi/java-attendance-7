package attendance.config;

import attendance.controller.Controller;
import attendance.view.InputView;
import attendance.view.OutputView;

public class AppConfig {
    private final InputView inputView;
    private final OutputView outputView;

    public AppConfig() {
        this.inputView = inputView();
        this.outputView = outputView();
    }

    public Controller controller() {
        return new Controller(inputView, outputView);
    }

    public InputView inputView() {
        return new InputView();
    }

    public OutputView outputView() {
        return new OutputView();
    }
}
