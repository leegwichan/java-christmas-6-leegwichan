package christmas.domain.plan;

import java.util.Map;
import java.util.function.IntUnaryOperator;

public class MockPlan extends Plan {

    private static final EventDate DEFAULT_DATE = EventDate.from(1);
    private static final Order DEFAULT_ORDER = Order.from(Map.of(Menu.T_BONE_STEAK, 1));

    private final boolean isWeekDay;
    private final int date;
    private final int countOfMainMenu;
    private final int countOfDessertMenu;
    private final int totalPrice;

    private MockPlan(boolean isWeekDay, int date1, int countOfMainMenu,
                     int countOfDessertMenu, int totalPrice) {
        super(DEFAULT_DATE, DEFAULT_ORDER);
        this.isWeekDay = isWeekDay;
        this.date = date1;
        this.countOfMainMenu = countOfMainMenu;
        this.countOfDessertMenu = countOfDessertMenu;
        this.totalPrice = totalPrice;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean isWeekday() {
        return isWeekDay;
    }

    @Override
    public boolean isWeekend() {
        return !isWeekDay;
    }

    @Override
    public int calculateByDate(IntUnaryOperator function) {
        return function.applyAsInt(date);
    }

    @Override
    public boolean isContainedBy(EventSchedule schedule) {
        return schedule.contains(EventDate.from(date));
    }

    @Override
    public int countMainMenu() {
        return countOfMainMenu;
    }

    @Override
    public int countDessertMenu() {
        return countOfDessertMenu;
    }

    @Override
    public int calculateTotalPrice() {
        return totalPrice;
    }

    @Override
    public boolean isTotalPriceEqualOrMoreThan(int comparedPrice) {
        return totalPrice >= comparedPrice;
    }

    public static class Builder {
        private boolean isWeekDay;
        private int date;
        private int countOfMainMenu;
        private int countOfDessertMenu;
        private int totalPrice = 20_000;

        public Builder isWeekDay() {
            this.isWeekDay = true;
            return this;
        }

        public Builder isWeekEnd() {
            this.isWeekDay = false;
            return this;
        }

        public Builder date(int date) {
            this.date = date;
            return this;
        }

        public Builder countOfMainMenu(int countOfMainMenu) {
            this.countOfMainMenu = countOfMainMenu;
            return this;
        }

        public Builder countOfDessertMenu(int countOfDessertMenu) {
            this.countOfDessertMenu = countOfDessertMenu;
            return this;
        }

        public Builder totalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public MockPlan build() {
            return new MockPlan(isWeekDay, date, countOfMainMenu, countOfDessertMenu, totalPrice);
        }
    }
}
