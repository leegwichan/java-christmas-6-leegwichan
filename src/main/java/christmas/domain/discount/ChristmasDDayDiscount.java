package christmas.domain.discount;

import christmas.domain.plan.EventSchedule;
import christmas.domain.plan.Plan;
import java.util.function.IntUnaryOperator;

public class ChristmasDDayDiscount extends DiscountPolicy {

    private static final int START_DATE = 1;
    private static final int END_DATE = 25;
    private static final EventSchedule EVENT_SCHEDULE = EventSchedule.of(START_DATE, END_DATE);

    @Override
    boolean isSatisfyPrecondition(Plan plan) {
        return plan.isContainedBy(EVENT_SCHEDULE);
    }

    @Override
    int calculateDiscountAmount(Plan plan) {
        return 1000;
    }
}
