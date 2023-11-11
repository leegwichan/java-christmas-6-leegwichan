package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.plan.MockPlan;
import christmas.domain.plan.Plan;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WeekdayDiscountTest {

    @Test
    @DisplayName("평일이 아닐 경우, 할인 금액 0원을 반환한다")
    void calculateDiscountPriceTest_whenWeekend() {
        Plan plan = MockPlan.builder()
                .isWeekEnd().countOfDessertMenu(3).build();
        int expected = 0;

        int actual = new WeekdayDiscount().calculateDiscountPrice(plan);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest(name = "주문한 디저트 개수 : {0}, 할인 금액 : {1}")
    @CsvSource({"0, 0", "1, 2023", "5, 10115"})
    @DisplayName("평일일 경우, 주문한 디저트 개수 1개당 2,203원을 할인한다")
    void calculateDiscountPriceTest_whenWeekday(int countOfDessert, int expected) {
        Plan plan = MockPlan.builder()
                .isWeekDay().countOfDessertMenu(countOfDessert).build();

        int actual = new WeekdayDiscount().calculateDiscountPrice(plan);

        assertThat(actual).isEqualTo(expected);
    }
}
