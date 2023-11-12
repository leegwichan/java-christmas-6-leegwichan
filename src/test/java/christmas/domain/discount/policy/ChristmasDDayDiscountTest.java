package christmas.domain.discount.policy;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.plan.MockPlan;
import christmas.domain.plan.Plan;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ChristmasDDayDiscountTest {

    @Test
    @DisplayName("26일 이후 일 경우, 할인 금액은 0원이다")
    void calculateDiscountPriceTest_whenNotSatisfyPrecondition() {
        Plan plan = MockPlan.builder().date(26).build();
        int expected = 0;

        int actual = new ChristmasDDayDiscount().calculateDiscountPrice(plan);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0}일 할인 금액 : {1}원")
    @CsvSource({"1, 1000", "25, 3400", "10, 1900"})
    @DisplayName("1 ~ 25일에 할인 금액은 1,000원 에서 매일 100원씩 증가한다")
    void calculateDiscountPriceTest_whenSatisfyPrecondition(int date, int expected) {
        Plan plan = MockPlan.builder().date(date).build();

        int actual = new ChristmasDDayDiscount().calculateDiscountPrice(plan);

        assertThat(actual).isEqualTo(expected);
    }
}
