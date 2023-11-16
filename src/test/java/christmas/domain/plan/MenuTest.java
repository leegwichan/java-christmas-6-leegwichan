package christmas.domain.plan;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MenuTest {

    @ParameterizedTest(name = "메뉴 : {0}, 가격 : {1}")
    @CsvSource({"MUSHROOM_SOUP, 6000", "CHRISTMAS_PASTA, 25000", "ICE_CREAM, 5000", "CHAMPAGNE, 25000"})
    @DisplayName("메뉴에 따라 가격을 알 수 있다")
    void getPriceTest(Menu menu, int expected) {

        int actual = menu.getPrice();

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest(name = "메뉴 : {0}, 메인 : {1}")
    @CsvSource({"T_BONE_STEAK, true", "CHOCOLATE_CAKE, false"})
    @DisplayName("해당 메뉴가 메인 메뉴인지 알 수 있다")
    void isMainTest(Menu menu, boolean expected) {

        boolean actual = menu.isMain();

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest(name = "메뉴 : {0}, 디저트 : {1}")
    @CsvSource({"CHOCOLATE_CAKE, true", "T_BONE_STEAK, false"})
    @DisplayName("해당 메뉴가 디저트 메뉴인지 알 수 있다")
    void isDessertTest(Menu menu, boolean expected) {

        boolean actual = menu.isDessert();

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest(name = "메뉴 : {0}, 디저트 : {1}")
    @CsvSource({"CHAMPAGNE, true", "T_BONE_STEAK, false"})
    @DisplayName("해당 메뉴가 음료 메뉴인지 알 수 있다")
    void isDrinkTest(Menu menu, boolean expected) {

        boolean actual = menu.isDrink();

        assertThat(actual).isEqualTo(expected);
    }
}
