package christmas.domain.discount.policy;

import christmas.domain.plan.Plan;

public abstract class DiscountPolicy {
    private static final int ZERO = 0;

    public final int calculateDiscountPrice(Plan plan) {
        if (!isSatisfyPrecondition(plan)) {
            return ZERO;
        }
        return calculateDiscountAmount(plan);
    }

    abstract boolean isSatisfyPrecondition(Plan plan);

    abstract int calculateDiscountAmount(Plan plan);
}
