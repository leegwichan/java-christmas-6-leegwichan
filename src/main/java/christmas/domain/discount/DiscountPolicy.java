package christmas.domain.discount;

import christmas.domain.plan.Plan;

abstract class DiscountPolicy {

    private static final int MIN_TOTAL_PRICE = 10_000;
    private static final int ZERO = 0;

    public final int calculateDiscountPrice(Plan plan) {
        if (!isSatisfyCommonPrecondition(plan) || !isSatisfyPrecondition(plan)) {
            return ZERO;
        }
        return calculateDiscountAmount(plan);
    }

    private boolean isSatisfyCommonPrecondition(Plan plan) {
        return plan.isTotalPriceEqualOrMoreThan(MIN_TOTAL_PRICE);
    }

    abstract boolean isSatisfyPrecondition(Plan plan);
    abstract int calculateDiscountAmount(Plan plan);
}
