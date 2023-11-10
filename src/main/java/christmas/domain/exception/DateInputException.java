package christmas.domain.exception;

public class DateInputException extends IllegalArgumentException {

    public DateInputException(String message) {
        super(message);
    }
}
