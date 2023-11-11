package christmas.domain.discount;

import christmas.domain.plan.EventSchedule;
import christmas.domain.plan.Plan;
import java.util.Set;

public class SpecialDiscount extends DiscountPolicy {

    private static final EventSchedule EVENT_SCHEDULE = EventSchedule.from(Set.of(3, 10, 17, 24, 25, 31));
    private static final int DISCOUNT_AMOUNT = 1_000;

    @Override
    boolean isSatisfyPrecondition(Plan plan) {
        return plan.isContainedBy(EVENT_SCHEDULE);
    }

    @Override
    int calculateDiscountAmount(Plan plan) {
        return DISCOUNT_AMOUNT;
    }
}
