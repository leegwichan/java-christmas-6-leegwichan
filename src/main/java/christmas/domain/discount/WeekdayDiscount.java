package christmas.domain.discount;

import christmas.domain.plan.Plan;

public class WeekdayDiscount extends DiscountPolicy {

    @Override
    boolean isSatisfyPrecondition(Plan plan) {
        return plan.isWeekday();
    }

    @Override
    int calculateDiscountAmount(Plan plan) {
        return 1000;
    }
}
