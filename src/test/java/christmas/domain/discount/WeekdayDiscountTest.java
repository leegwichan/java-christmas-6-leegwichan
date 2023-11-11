package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.plan.MockPlan;
import christmas.domain.plan.Plan;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekdayDiscountTest {

    @Test
    @DisplayName("평일이 아닐 경우, 할인 금액 0원을 반환한다")
    void calculateDiscountPriceTest_whenNotSatisfyPrecondition() {
        Plan plan = MockPlan.builder()
                .isWeekEnd().countOfDessertMenu(3).build();
        int expected = 0;

        int actual = new WeekdayDiscount().calculateDiscountPrice(plan);

        assertThat(actual).isEqualTo(expected);
    }
}
