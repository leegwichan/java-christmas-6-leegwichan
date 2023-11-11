package christmas.domain.plan;

import christmas.exception.DateInputException;
import christmas.exception.TotalMenuCountException;
import java.util.Map;

public class Order {

    private static final int TOTAL_COUNT_OF_MENU = 20;

    private final Map<Menu, Integer> menuToCount;

    private Order(Map<Menu, Integer> menuToCount) {
        validate(menuToCount);
        this.menuToCount = Map.copyOf(menuToCount);
    }

    private static void validate(Map<Menu, Integer> menuToCount) {
        validateCountPerMenu(menuToCount);
        validateTotalCount(menuToCount);
    }

    private static void validateCountPerMenu(Map<Menu, Integer> menuToCount) {
        if (isExistCountUnderOne(menuToCount)) {
            throw new DateInputException("quantity isn't less than 1");
        }
    }

    private static boolean isExistCountUnderOne(Map<Menu, Integer> menuToCount) {
        return menuToCount.values().stream()
                .anyMatch(count -> count < 1);
    }

    private static void validateTotalCount(Map<Menu, Integer> menuToCount) {
        if (isOverTotalCount(menuToCount)) {
            throw new TotalMenuCountException(TOTAL_COUNT_OF_MENU);
        }
    }

    private static boolean isOverTotalCount(Map<Menu, Integer> menuToCount) {
        return countTotalMenu(menuToCount) > TOTAL_COUNT_OF_MENU;
    }

    private static int countTotalMenu(Map<Menu, Integer> menuToCount) {
        return menuToCount.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static Order from(Map<Menu, Integer> menuToCount) {
        return new Order(menuToCount);
    }
}
