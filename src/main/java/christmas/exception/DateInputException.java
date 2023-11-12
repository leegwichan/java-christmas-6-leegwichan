package christmas.exception;

public class DateInputException extends IllegalArgumentException {

    public DateInputException(String message) {
        super(message);
    }

    public DateInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
