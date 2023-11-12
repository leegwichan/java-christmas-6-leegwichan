package christmas.domain.discount;

import christmas.domain.discount.policy.DiscountPolicy;
import java.util.Map;
import java.util.Objects;

public class DiscountDetails {

    private final Map<Class<? extends DiscountPolicy>, Integer> detailsToDiscountPrice;

    private DiscountDetails(Map<Class<? extends DiscountPolicy>, Integer> detailsToDiscountPrice) {
        this.detailsToDiscountPrice = Objects.requireNonNull(detailsToDiscountPrice);
    }

    public static DiscountDetails from(Map<Class<? extends DiscountPolicy>, Integer> detailsToDiscountPrice) {
        return new DiscountDetails(detailsToDiscountPrice);
    }

    public static DiscountDetails empty() {
        return new DiscountDetails(Map.of());
    }

    public int calculateTotalDiscountPrice() {
        return detailsToDiscountPrice.values().stream()
                .mapToInt(Integer::intValue).sum();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        DiscountDetails that = (DiscountDetails) object;
        return Objects.equals(detailsToDiscountPrice, that.detailsToDiscountPrice);
    }

    @Override
    public int hashCode() {
        if (detailsToDiscountPrice == null) {
            return 0;
        }
        return detailsToDiscountPrice.hashCode();
    }

    public Map<Class<? extends DiscountPolicy>, Integer> getClassToDiscountPrice() {
        return detailsToDiscountPrice;
    }
}
