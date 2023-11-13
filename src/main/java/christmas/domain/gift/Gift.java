package christmas.domain.gift;

import christmas.domain.plan.Menu;
import java.util.Map;
import java.util.Objects;

public enum Gift {
    EVENT_GIFT(Map.of(Menu.CHAMPAGNE, 1)),
    NOTHING(Map.of());

    private static final int MIN_GIFT_PRICE = 120_000;

    private final Map<Menu, Integer> menuToCount;

    Gift(Map<Menu, Integer> menuToCount) {
        this.menuToCount = Objects.requireNonNull(menuToCount);
    }

    public static Gift from(int totalPrice) {
        if (isOverThanStandardPrice(totalPrice)) {
            return EVENT_GIFT;
        }
        return NOTHING;
    }

    private static boolean isOverThanStandardPrice(int totalPrice) {
        return totalPrice >= MIN_GIFT_PRICE;
    }

    public int calculateBenefitPrice() {
        return menuToCount.keySet().stream()
                .mapToInt(menu -> calculatePartOfPrice(menu, menuToCount.get(menu)))
                .sum();
    }

    public int calculatePartOfPrice(Menu menu, int count) {
        return menu.getPrice() * count;
    }

    public Map<Menu, Integer> getMenuToCount() {
        return menuToCount;
    }
}
