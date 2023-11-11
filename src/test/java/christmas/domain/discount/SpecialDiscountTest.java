package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.plan.MockPlan;
import christmas.domain.plan.Plan;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SpecialDiscountTest {

    @ParameterizedTest(name = "{0}일")
    @CsvSource({"2", "4", "18", "26"})
    @DisplayName("특별 할인 기간이 아닌 경우, 0원을 반환한다")
    void calculateDiscountPriceTest_whenNotStarDate(int date) {
        Plan plan = MockPlan.builder()
                .date(date).build();
        int expected = 0;

        int actual = new SpecialDiscount().calculateDiscountPrice(plan);

        assertThat(actual).isEqualTo(expected);
    }
}
