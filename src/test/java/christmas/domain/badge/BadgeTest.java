package christmas.domain.badge;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BadgeTest {

    @ParameterizedTest(name = "총 혜택 금액 : {0}, 배지 : {1}")
    @CsvSource({"4999, NOTHING", "5000, STAR", "10001, TREE"})
    @DisplayName("총 혜택 금액에 따라 알맞은 배지를 반환한다")
    void fromTest(int benefitAmount, Badge excepted) {

        Badge actual = Badge.from(benefitAmount);

        assertThat(actual).isEqualTo(excepted);
    }
}
