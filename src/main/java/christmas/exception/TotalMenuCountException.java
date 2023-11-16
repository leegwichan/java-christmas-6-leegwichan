package christmas.exception;

public class TotalMenuCountException extends IllegalArgumentException {

    private static final String EXCEPTION_MESSAGE_FORMAT = "총 주문 개수는 %d개 이하 이어야 합니다";

    public TotalMenuCountException(int maxCount) {
        super(EXCEPTION_MESSAGE_FORMAT.formatted(maxCount));
    }
}
