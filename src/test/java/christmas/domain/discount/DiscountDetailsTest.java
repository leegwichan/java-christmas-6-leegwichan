package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.discount.policy.ChristmasDDayDiscount;
import christmas.domain.discount.policy.DiscountPolicy;
import christmas.domain.discount.policy.MockDiscountPolicy;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountDetailsTest {

    @DisplayName("빈 내역의 경우, 총 할인 금액은 0원이다")
    @Test
    void calculateTotalDiscountPrice_whenDetailIsEmpty() {
        DiscountDetails details = DiscountDetails.empty();

        int actual = details.calculateTotalDiscountPrice();

        assertThat(actual).isZero();
    }

    @DisplayName("할인 내역에 따라 할인 금액을 반환할 수 있다")
    @Test
    void calculateTotalDiscountPrice() {
        Map<Class<? extends DiscountPolicy>, Integer> detailsToDiscountPrice
                = Map.of(MockDiscountPolicy.class, 1_500, ChristmasDDayDiscount.class, 2_000);
        DiscountDetails details = DiscountDetails.from(detailsToDiscountPrice);
        int expected = 1_500 + 2_000;

        int actual = details.calculateTotalDiscountPrice();

        assertThat(actual).isEqualTo(expected);
    }
}
