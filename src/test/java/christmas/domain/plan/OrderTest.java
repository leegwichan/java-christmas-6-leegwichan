package christmas.domain.plan;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.DateInputException;
import christmas.exception.OnlyDrinkMenuException;
import christmas.exception.TotalMenuCountException;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OrderTest {

    @Nested
    @DisplayName("주문 검증 테스트")
    class ValidationTest {

        static final Menu APPETIZER_EXAMPLE = Menu.TAPAS;
        static final Menu MAIN_EXAMPLE = Menu.T_BONE_STEAK;

        @ParameterizedTest(name = "메인 메뉴가 {0}개일 때")
        @CsvSource({"0", "-1"})
        @DisplayName("메뉴당 주문 개수가 1 미만인 경우, 예외를 던진다")
        void countPerMenuValidationTest(int count) {
            Map<Menu, Integer> menuToCount = Map.of(MAIN_EXAMPLE, count, APPETIZER_EXAMPLE, 2);

            assertThatThrownBy(() -> Order.from(menuToCount))
                    .isInstanceOf(DateInputException.class)
                    .hasMessageContaining("quantity isn't less than 1");
        }

        @Test
        @DisplayName("총 주문 개수가 20개 초과인 경우, 예외를 던진다")
        void totalCountMenuValidationTest() {
            Map<Menu, Integer> menuToCount = Map.of(MAIN_EXAMPLE, 10, APPETIZER_EXAMPLE, 11);

            assertThatThrownBy(() -> Order.from(menuToCount))
                    .isInstanceOf(TotalMenuCountException.class)
                    .hasMessageContaining("총 주문 개수는 20개 이하 이어야 합니다");
        }

        @Test
        @DisplayName("주문이 음료로만 구성되어 있을 경우, 예외를 던진다")
        void validateNotOnlyDrinkTest() {
            Map<Menu, Integer> menuToCount = Map.of(Menu.RED_WINE, 2, Menu.ZERO_COKE, 1);

            assertThatThrownBy(() -> Order.from(menuToCount))
                    .isInstanceOf(OnlyDrinkMenuException.class)
                    .hasMessageContaining("주문은 음료로만 구성될 수 없습니다");
        }

        @Test
        @DisplayName("주문 조건을 모두 만족한 경우, 정상적으로 객체를 생성한다")
        void validateTest_whenSatisfyAllCondition() {
            Map<Menu, Integer> menuToCount = Map.of(MAIN_EXAMPLE, 3, APPETIZER_EXAMPLE, 5, Menu.ZERO_COKE, 2);

            Order order = Order.from(menuToCount);

            assertThat(order).isNotNull();
        }
    }
}
