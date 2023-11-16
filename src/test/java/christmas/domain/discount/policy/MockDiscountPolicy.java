package christmas.domain.discount.policy;

import christmas.domain.plan.Plan;

public class MockDiscountPolicy extends DiscountPolicy {

    private final boolean isSatisfyPrecondition;
    private final int discountAmount;

    public MockDiscountPolicy(boolean isSatisfyPrecondition, int discountAmount) {
        this.isSatisfyPrecondition = isSatisfyPrecondition;
        this.discountAmount = discountAmount;
    }

    @Override
    boolean isSatisfyPrecondition(Plan plan) {
        return isSatisfyPrecondition;
    }

    @Override
    int calculateDiscountAmount(Plan plan) {
        return discountAmount;
    }
}
