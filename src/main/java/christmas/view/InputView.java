package christmas.view;

import static java.util.stream.Collectors.toMap;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.plan.Menu;
import christmas.exception.DateInputException;
import christmas.exception.OrderInputException;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

public class InputView {

    private static final String REQUEST_INPUT_DATE
            = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String REQUEST_INPUT_ORDER
            = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String ORDER_DELIMITER = ",";
    private static final String MENU_COUNT_DELIMITER = "-";
    private static final int LIMIT = -1;

    public int readDate() {
        println(REQUEST_INPUT_DATE);
        return readDateValue();
    }

    private int readDateValue() {
        try {
            return tryParsingToInt(read().trim());
        } catch (IllegalArgumentException exception) {
            throw new DateInputException("date format must be integer", exception);
        }
    }

    public Map<Menu, Integer> readOrder() {
        println(REQUEST_INPUT_ORDER);
        return readOrderValue();
    }

    private Map<Menu, Integer> readOrderValue() {
        try {
            return tryParsingToOrderForm(read().trim());
        } catch (IllegalArgumentException | IllegalStateException exception) {
            throw new OrderInputException("input order format is not matched", exception);
        }
    }

    private Map<Menu, Integer> tryParsingToOrderForm(String inputMessage) {
        return Arrays.stream(inputMessage.split(ORDER_DELIMITER, LIMIT))
                .map(this::tryParsingToOrderDetail)
                .collect(toMap(Entry::getKey, Entry::getValue));
    }

    private Entry<Menu, Integer> tryParsingToOrderDetail(String orderDetail) {
        String[] menuAndCount = splitMenuAndCount(orderDetail);
        Menu menu = MenuView.findMenu(menuAndCount[0]);
        int count = tryParsingToInt(menuAndCount[1]);
        return Map.entry(menu, count);
    }

    private String[] splitMenuAndCount(String orderDetail) {
        String[] menuAndCount = orderDetail.split(MENU_COUNT_DELIMITER, LIMIT);
        validateSplit(menuAndCount);
        return menuAndCount;
    }

    private void validateSplit(String[] menuAndCount) {
        if (menuAndCount.length != 2) {
            throw new OrderInputException("input order format is not matched");
        }
    }

    private int tryParsingToInt(String inputMessage) {
        return Integer.parseInt(inputMessage);
    }

    private void println(String message) {
        System.out.println(message);
    }

    private String read() {
        return Console.readLine();
    }
}
