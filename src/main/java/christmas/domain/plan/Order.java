package christmas.domain.plan;

import christmas.exception.DateInputException;
import christmas.exception.OnlyDrinkMenuException;
import christmas.exception.TotalMenuCountException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

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
        validateNotOnlyDrink(menuToCount);
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

    private static void validateNotOnlyDrink(Map<Menu, Integer> menuToCount) {
        if (isOnlyDrinkMenu(menuToCount)) {
            throw new OnlyDrinkMenuException();
        }
    }

    private static boolean isOnlyDrinkMenu(Map<Menu, Integer> menuToCount) {
        return menuToCount.keySet().stream()
                .allMatch(Menu::isDrink);
    }

    public static Order from(Map<Menu, Integer> menuToCount) {
        return new Order(menuToCount);
    }

    public int countMainMenu() {
        return countMenu(Menu::isMain);
    }

    public int countDessertMenu() {
        return countMenu(Menu::isDessert);
    }

    private int countMenu(Predicate<Menu> condition) {
        return menuToCount.keySet().stream()
                .filter(condition)
                .mapToInt(menuToCount::get)
                .sum();
    }

    public int calculateTotalPrice() {
        return menuToCount.entrySet().stream()
                .mapToInt(this::calculateSubPrice)
                .sum();
    }

    private int calculateSubPrice(Entry<Menu, Integer> menuCountPair) {
        int menuPrice = menuCountPair.getKey().getPrice();
        int count = menuCountPair.getValue();
        return menuPrice * count;
    }

    public boolean isTotalPriceEqualOrMoreThan(int comparedPrice) {
        return calculateTotalPrice() >= comparedPrice;
    }
}
