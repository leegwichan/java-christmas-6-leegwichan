package christmas.domain.discount.policy;

import christmas.domain.plan.Plan;

public class WeekdayDiscount extends DiscountPolicy {

    private static final int DISCOUNT_AMOUNT_PER_MENU = 2_023;

    @Override
    boolean isSatisfyPrecondition(Plan plan) {
        return plan.isWeekday();
    }

    @Override
    int calculateDiscountAmount(Plan plan) {
        return DISCOUNT_AMOUNT_PER_MENU * plan.countDessertMenu();
    }
}
