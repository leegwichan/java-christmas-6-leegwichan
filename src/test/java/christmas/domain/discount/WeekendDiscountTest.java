package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.plan.MockPlan;
import christmas.domain.plan.Plan;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekendDiscountTest {

    @Test
    @DisplayName("주말이 아닌 경우, 0원을 반환한다")
    void calculateDiscountPriceTest_whenWeekday() {
        Plan plan = MockPlan.builder()
                .isWeekDay().countOfMainMenu(3).build();
        int expected = 0;

        int actual = new WeekendDiscount().calculateDiscountPrice(plan);

        assertThat(actual).isEqualTo(expected);
    }
}
