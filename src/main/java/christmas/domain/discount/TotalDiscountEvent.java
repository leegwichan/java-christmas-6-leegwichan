package christmas.domain.discount;

import static java.util.stream.Collectors.toMap;

import christmas.domain.discount.policy.ChristmasDDayDiscount;
import christmas.domain.discount.policy.DiscountPolicy;
import christmas.domain.discount.policy.SpecialDiscount;
import christmas.domain.discount.policy.WeekdayDiscount;
import christmas.domain.discount.policy.WeekendDiscount;
import christmas.domain.plan.Plan;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TotalDiscountEvent {

    private static final List<DiscountPolicy> DECEMBER_EVENTS = List.of(new ChristmasDDayDiscount(),
            new WeekdayDiscount(), new WeekendDiscount(), new SpecialDiscount());
    private static final int MIN_TOTAL_PRICE = 10_000;

    private final List<DiscountPolicy> discountPolicies;

    TotalDiscountEvent(List<DiscountPolicy> discountPolicies) {
        this.discountPolicies = Objects.requireNonNull(discountPolicies);
    }

    public static TotalDiscountEvent createDecemberEvent() {
        return new TotalDiscountEvent(DECEMBER_EVENTS);
    }

    public DiscountDetails makeDiscountDetails(Plan plan) {
        if (!isSatisfyCommonPrecondition(plan)) {
            return DiscountDetails.empty();
        }
        return makeDiscountDetailsWhenSatisfyPrecondition(plan);
    }

    private boolean isSatisfyCommonPrecondition(Plan plan) {
        return plan.isTotalPriceEqualOrMoreThan(MIN_TOTAL_PRICE);
    }

    private DiscountDetails makeDiscountDetailsWhenSatisfyPrecondition(Plan plan) {
        Map<Class<? extends DiscountPolicy>, Integer> discountDetails = discountPolicies.stream()
                .filter(discountPolicy -> hasDiscountBenefit(discountPolicy, plan))
                .collect(toMap(DiscountPolicy::getClass, discountPolicy -> getDiscountPrice(discountPolicy, plan)));

        return DiscountDetails.from(discountDetails);
    }

    private boolean hasDiscountBenefit(DiscountPolicy discountPolicy, Plan plan) {
        return discountPolicy.calculateDiscountPrice(plan) > 0;
    }

    private int getDiscountPrice(DiscountPolicy discountPolicy, Plan plan) {
        return discountPolicy.calculateDiscountPrice(plan);
    }
}
