package christmas.badge;

import java.util.List;

public enum Badge {

    SANTA(20_000),
    TREE(10_000),
    STAR(5_000),
    NOTHING(0);

    private static final List<Badge> ORDER_BY_BENEFIT = List.of(SANTA, TREE, STAR, NOTHING);

    private final int minBenefitAmount;

    Badge(int minBenefitAmount) {
        this.minBenefitAmount = minBenefitAmount;
    }

    public static Badge from(int benefitAmount) {
        return ORDER_BY_BENEFIT.stream()
                .filter(badge -> badge.isReach(benefitAmount))
                .findFirst().orElseThrow(Badge::createIllegalBenefitAmountException);
    }

    private static IllegalArgumentException createIllegalBenefitAmountException() {
        return new IllegalArgumentException("benefit amount must not be negative");
    }

    private boolean isReach(int benefitAmount) {
        return benefitAmount >= this.minBenefitAmount;
    }
}
