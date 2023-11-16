package christmas.exception;

public class OnlyDrinkMenuException extends IllegalArgumentException {

    private static final String EXCEPTION_MESSAGE = "주문은 음료로만 구성될 수 없습니다";

    public OnlyDrinkMenuException() {
        super(EXCEPTION_MESSAGE);
    }
}
