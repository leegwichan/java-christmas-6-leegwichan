package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.discount.policy.DiscountPolicy;
import christmas.domain.discount.policy.MockDiscountPolicy;
import christmas.domain.plan.MockPlan;
import christmas.domain.plan.Plan;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TotalDiscountEventTest {

    @DisplayName("총 주문 금액이 10,000 미만일 경우, 빈 할인 내역을 반환한다")
    @ParameterizedTest(name = "총 주문 금액 : {0}")
    @CsvSource({"0", "5000", "9999"})
    void makeDiscountDetailsTest_whenTotalPriceUnderLimit(int totalPrice) {
        List<DiscountPolicy> discountPolicies = List.of(new MockDiscountPolicy(true, 1000));
        TotalDiscountEvent totalDiscountEvent = new TotalDiscountEvent(discountPolicies);
        Plan plan = MockPlan.builder().totalPrice(totalPrice).build();

        DiscountDetails actual = totalDiscountEvent.makeDiscountDetails(plan);

        assertThat(actual).isEqualTo(DiscountDetails.empty());
    }

    @DisplayName("총 주문 금액이 10,000 이상일 경우, 최종 할인 내역을 반환한다")
    @Test
    void makeDiscountDetailsTest_whenTotalPriceOverLimit() {
        List<DiscountPolicy> discountPolicies = List.of(new MockDiscountPolicy(true, 1_000));
        TotalDiscountEvent totalDiscountEvent = new TotalDiscountEvent(discountPolicies);
        Plan plan = MockPlan.builder().totalPrice(10_000).build();
        DiscountDetails expected = DiscountDetails.from(Map.of(MockDiscountPolicy.class, 1_000));

        DiscountDetails actual = totalDiscountEvent.makeDiscountDetails(plan);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("할인이 해당되지 않는 경우, 해당 내역은 반환하지 않는다.")
    @Test
    void makeDiscountDetailsTest_when() {
        List<DiscountPolicy> discountPolicies = List.of(new MockDiscountPolicy(false, 1_000));
        TotalDiscountEvent totalDiscountEvent = new TotalDiscountEvent(discountPolicies);
        Plan plan = MockPlan.builder().totalPrice(10_000).build();

        DiscountDetails actual = totalDiscountEvent.makeDiscountDetails(plan);

        assertThat(actual).isEqualTo(DiscountDetails.empty());
    }
}
