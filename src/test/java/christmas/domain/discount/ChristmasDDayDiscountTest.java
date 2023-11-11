package christmas.domain.discount;

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
}
