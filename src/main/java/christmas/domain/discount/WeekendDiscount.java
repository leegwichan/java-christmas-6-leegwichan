package christmas.domain.discount;

import christmas.domain.plan.Plan;

public class WeekendDiscount extends DiscountPolicy {
    @Override
    boolean isSatisfyPrecondition(Plan plan) {
        return plan.isWeekend();
    }

    @Override
    int calculateDiscountAmount(Plan plan) {
        return 1000;
    }
}
