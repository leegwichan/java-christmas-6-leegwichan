package christmas.exception;

public class OrderInputException extends IllegalArgumentException {

    public OrderInputException(String message) {
        super(message);
    }

    public OrderInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
