package christmas.domain.plan;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.OnlyDrinkMenuException;
import christmas.exception.OrderInputException;
import christmas.exception.TotalMenuCountException;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class OrderTest {

    static final Menu APPETIZER_EXAMPLE = Menu.TAPAS;
    static final Menu MAIN_EXAMPLE = Menu.T_BONE_STEAK;
    static final Menu DESSERT_EXAMPLE = Menu.CHOCOLATE_CAKE;
    static final Menu DRINK_EXAMPLE = Menu.CHAMPAGNE;

    @Nested
    @DisplayName("주문 검증 테스트")
    class ValidationTest {

        @ParameterizedTest(name = "메인 메뉴가 {0}개일 때")
        @CsvSource({"0", "-1"})
        @DisplayName("메뉴당 주문 개수가 1 미만인 경우, 예외를 던진다")
        void countPerMenuValidationTest(int count) {
            Map<Menu, Integer> menuToCount = Map.of(MAIN_EXAMPLE, count, APPETIZER_EXAMPLE, 2);

            assertThatThrownBy(() -> Order.from(menuToCount))
                    .isInstanceOf(OrderInputException.class)
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

    @Nested
    @DisplayName("조건에 따른 메뉴 카운팅 테스트")
    class CountingMenuTest {

        @ParameterizedTest(name = "{0}; 메인 메뉴 개수 : {1}")
        @MethodSource
        @DisplayName("주문한 총 메인 메뉴 개수를 셀 수 있다")
        void countMainMenuTest(Map<Menu, Integer> menuToCount, int expected) {
            Order order = Order.from(menuToCount);

            int actual = order.countMainMenu();

            assertThat(actual).isEqualTo(expected);
        }

        private static Stream<Arguments> countMainMenuTest() {
            return Stream.of(
                    Arguments.of(Map.of(DRINK_EXAMPLE, 3, DESSERT_EXAMPLE, 4), 0),
                    Arguments.of(Map.of(MAIN_EXAMPLE, 5, APPETIZER_EXAMPLE, 3), 5),
                    Arguments.of(Map.of(Menu.T_BONE_STEAK, 6, Menu.BARBECUE_RIBS, 4), 10)
            );
        }

        @ParameterizedTest(name = "{0}; 디저트 메뉴 개수 : {1}")
        @MethodSource
        @DisplayName("주문한 총 디저트 메뉴 개수를 셀 수 있다")
        void countDessertMenuTest(Map<Menu, Integer> menuToCount, int expected) {
            Order order = Order.from(menuToCount);

            int actual = order.countDessertMenu();

            assertThat(actual).isEqualTo(expected);
        }

        private static Stream<Arguments> countDessertMenuTest() {
            return Stream.of(
                    Arguments.of(Map.of(DRINK_EXAMPLE, 3, DESSERT_EXAMPLE, 4), 4),
                    Arguments.of(Map.of(MAIN_EXAMPLE, 5, APPETIZER_EXAMPLE, 3), 0),
                    Arguments.of(Map.of(Menu.CHOCOLATE_CAKE, 6, Menu.ICE_CREAM, 4), 10)
            );
        }
    }

    @Nested
    @DisplayName("총 가격 계산 기능 테스트")
    class TotalPriceTest {

        static final Menu PRICE_5500 = Menu.TAPAS;
        static final Menu PRICE_55000 = Menu.T_BONE_STEAK;
        static final Menu PRICE_15000 = Menu.CHOCOLATE_CAKE;
        static final Menu PRICE_25000 = Menu.CHAMPAGNE;

        @ParameterizedTest(name = "주문 : {0}, 총 주문 금액 : {1}")
        @MethodSource
        @DisplayName("총 주문 금액을 계산할 수 있다")
        void calculateTotalPriceTest(Map<Menu, Integer> menuToCount, int expected) {
            Order order = Order.from(menuToCount);

            int actual = order.calculateTotalPrice();

            assertThat(actual).isEqualTo(expected);
        }

        private static Stream<Arguments> calculateTotalPriceTest() {
            return Stream.of(
                    Arguments.of(Map.of(PRICE_5500, 1), 5_500),
                    Arguments.of(Map.of(PRICE_55000, 2, PRICE_15000, 3), 2 * 55_000 + 3 * 15_000),
                    Arguments.of(Map.of(PRICE_25000, 1, PRICE_55000, 1), 25_000 + 55_000));
        }

        @ParameterizedTest(name = "비교 금액과 기존 금액의 차이 : {0}, 총 금액이 비교 금액보다 높은가 : {1}")
        @CsvSource({"1, false", "0, true", "-1, true"})
        @DisplayName("비교 금액보다 주문 총액이 더 크거나 같은지 알 수 있다")
        void isTotalPriceEqualOrMoreThanTest(int addend, boolean expected) {
            Order order = Order.from(Map.of(PRICE_5500, 1));
            int comparedPrice = 5_500 + addend;

            boolean actual = order.isTotalPriceEqualOrMoreThan(comparedPrice);

            assertThat(actual).isEqualTo(expected);
        }
    }
}
