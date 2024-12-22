package attendance.config;

import static attendance.constants.Messages.INVALID_FILE_PATH;

import attendance.controller.Controller;
import attendance.domain.RecordBook;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.io.FileNotFoundException;

public class AppConfig {
    private final static String FILE_PATH = "src/main/resources/attendances.csv";

    private final InputView inputView;
    private final OutputView outputView;
    private final RecordBook recordBook;

    public AppConfig() {
        this.inputView = inputView();
        this.outputView = outputView();
        this.recordBook = recordBook();
    }

    public Controller controller() {
        return new Controller(inputView, outputView, recordBook);
    }

    public InputView inputView() {
        return new InputView();
    }

    public OutputView outputView() {
        return new OutputView();
    }

    public RecordBook recordBook() {
        try {
            return RecordBook.from(FILE_PATH);
        } catch (FileNotFoundException e) {
            System.out.println(INVALID_FILE_PATH.getErrorMessage());
        }
        return null;
    }
}
