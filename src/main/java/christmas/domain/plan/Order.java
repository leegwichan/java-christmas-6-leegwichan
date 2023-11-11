package christmas.domain.plan;

import christmas.exception.DateInputException;
import java.util.Map;

public class Order {

    private final Map<Menu, Integer> menuToCount;

    private Order(Map<Menu, Integer> menuToCount) {
        validate(menuToCount);
        this.menuToCount = Map.copyOf(menuToCount);
    }

    private static void validate(Map<Menu, Integer> menuToCount) {
        validateCountPerMenu(menuToCount);
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

    public static Order from(Map<Menu, Integer> menuToCount) {
        return new Order(menuToCount);
    }
}
