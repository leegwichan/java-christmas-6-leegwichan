package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.plan.Menu;
import christmas.exception.DateInputException;
import java.util.Map;

public class InputView {

    private static final String REQUEST_INPUT_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";

    public int readDate() {
        println(REQUEST_INPUT_DATE);
        return readInt();
    }

    private int readInt() {
        return toInteger(read());
    }

    private int toInteger(String string) {
        try {
            return Integer.parseInt(string.trim());
        } catch (IllegalArgumentException exception) {
            throw new DateInputException("date format must be integer", exception);
        }
    }

    public Map<Menu, Integer> readOrder() {
        throw new UnsupportedOperationException();
    }

    private void println(String message) {
        System.out.println(message);
    }

    private String read() {
        return Console.readLine();
    }
}
