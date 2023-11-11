package christmas.domain.plan;

import java.util.Objects;
import java.util.function.IntUnaryOperator;

public class Plan {

    private final EventDate date;
    private final Order order;

    private Plan(EventDate date, Order order) {
        this.date = Objects.requireNonNull(date);
        this.order = Objects.requireNonNull(order);
    }

    public static Plan of(EventDate date, Order order) {
        return new Plan(date, order);
    }

    public boolean isWeekday() {
        return date.isWeekDay();
    }

    public boolean isWeekend() {
        return date.isWeekend();
    }

    public int calculateByDate(IntUnaryOperator function) {
        return date.calculateByDate(function);
    }

    public boolean isContainedBy(EventSchedule schedule) {
        return schedule.contains(date);
    }

    public int countMainMenu() {
        return order.countMainMenu();
    }

    public int countDessertMenu() {
        return order.countDessertMenu();
    }

    public int calculateTotalPrice() {
        return order.calculateTotalPrice();
    }

    public boolean isTotalPriceEqualOrMoreThan(int comparedPrice) {
        return order.isTotalPriceEqualOrMoreThan(comparedPrice);
    }
}
