package christmas.domain.gift;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GiftTest {

    @DisplayName("총 구매 가격에 따라 증정품을 제공할 수 있다")
    @ParameterizedTest
    @CsvSource({"0,NOTHING", "119999,NOTHING", "120000,EVENT_GIFT"})
    void fromTest(int totalPrice, Gift expected) {

        Gift actual = Gift.from(totalPrice);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("증정품의 혜택 가격을 제공할 수 있다")
    @ParameterizedTest
    @CsvSource({"NOTHING, 0", "EVENT_GIFT, 25000"})
    void fromTest(Gift gift, int expected) {

        int actual = gift.calculateBenefitPrice();

        assertThat(actual).isEqualTo(expected);
    }
}
