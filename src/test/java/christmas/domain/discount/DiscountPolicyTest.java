package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.plan.MockPlan;
import christmas.domain.plan.Plan;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DiscountPolicyTest {

    @ParameterizedTest(name = "총 금액 : {0}원")
    @CsvSource({"0", "9999", "5000"})
    @DisplayName("총 금액이 10,000 미만일 경우, 0원을 반환한다")
    void calculateDiscountPriceTest_whenTotalPriceIsUnderThanBasicPrice(int totalPrice) {
        Plan plan = MockPlan.builder().totalPrice(totalPrice).build();
        DiscountPolicy discountPolicy = new MockDiscountPolicy(true, 1_000);
        int excepted = 0;

        int actual = discountPolicy.calculateDiscountPrice(plan);

        assertThat(actual).isEqualTo(excepted);
    }

    @ParameterizedTest(name = "총 금액 : {0}원")
    @CsvSource({"10000", "10001"})
    @DisplayName("총 금액이 10,000 이상일 경우, 계산한 금액을 반환한다")
    void calculateDiscountPriceTest_whenTotalPriceIsMoreThanBasicPrice(int totalPrice) {
        Plan plan = MockPlan.builder().totalPrice(totalPrice).build();
        DiscountPolicy discountPolicy = new MockDiscountPolicy(true, 1_000);
        int excepted = 1_000;

        int actual = discountPolicy.calculateDiscountPrice(plan);

        assertThat(actual).isEqualTo(excepted);
    }
}