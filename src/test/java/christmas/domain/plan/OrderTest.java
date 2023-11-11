package christmas.domain.plan;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.DateInputException;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OrderTest {

    @Nested
    @DisplayName("주문 검증 테스트")
    class ValidationTest {

        @ParameterizedTest(name = "메인 메뉴가 {0}개일 때")
        @CsvSource({"0", "-1"})
        @DisplayName("메뉴당 주문 개수가 1 미만인 경우, 예외를 던진다")
        void countPerMenuValidationTest(int count) {
            Map<Menu, Integer> menuToCount = Map.of(Menu.T_BONE_STEAK, count, Menu.TAPAS, 2);

            assertThatThrownBy(() -> Order.from(menuToCount))
                    .isInstanceOf(DateInputException.class)
                    .hasMessageContaining("quantity isn't less than 1");
        }
    }
}
