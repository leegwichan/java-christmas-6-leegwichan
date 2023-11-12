package christmas.view;

import christmas.exception.DateInputException;
import christmas.exception.OnlyDrinkMenuException;
import christmas.exception.OrderInputException;
import christmas.exception.TotalMenuCountException;
import java.util.Map;

class ErrorMessageView {

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    private static final Map<Class<? extends IllegalArgumentException>, String> EXCEPTION_TO_ERROR_MESSAGE
            = Map.of(DateInputException.class, "유효하지 않은 날짜입니다. 다시 입력해 주세요.",
            OrderInputException.class, "유효하지 않은 주문입니다. 다시 입력해 주세요.",
            OnlyDrinkMenuException.class, "주문은 음료로만 구성될 수 없습니다",
            TotalMenuCountException.class, "총 메뉴 개수는 20개를 초과할 수 없습니다");
    private static final String DEFAULT_ERROR_MESSAGE = "예상치 못한 에러가 발생했습니다. 관리자에게 문의하세요.";

    private ErrorMessageView() {
    }

    public static String constructMessage(IllegalArgumentException exception) {
        return ERROR_MESSAGE_PREFIX.concat(findErrorMessage(exception));
    }

    private static String findErrorMessage(IllegalArgumentException exception) {
        return EXCEPTION_TO_ERROR_MESSAGE.getOrDefault(exception.getClass(), DEFAULT_ERROR_MESSAGE);
    }
}
