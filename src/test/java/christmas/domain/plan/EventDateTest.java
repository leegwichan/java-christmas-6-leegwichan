package christmas.domain.plan;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.DateInputException;
import java.util.function.IntUnaryOperator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EventDateTest {

    @Nested
    @DisplayName("날짜 검증 테스트")
    class ValidationTest {

        @ParameterizedTest(name = "날짜 : {0}")
        @CsvSource({"-1", "0", "32"})
        @DisplayName("입력받은 날짜가 1 미만 또는 31 초과라면 예외를 던진다")
        void rangeTest_whenOutOfRange(int date) {

            assertThatThrownBy(() -> EventDate.from(date))
                    .isInstanceOf(DateInputException.class)
                    .hasMessageContaining("Date is Out of Range : " + date);
        }

        @ParameterizedTest(name = "날짜 : {0}")
        @CsvSource({"1", "31"})
        @DisplayName("입력받은 날짜가 1 이상 31 이하라면 정상적으로 객체를 생성한다")
        void rangeTest_whenInRange(int date) {

            EventDate eventDate = EventDate.from(date);

            assertThat(eventDate).isNotNull();
        }
    }

    @Nested
    @DisplayName("평일, 주말 구분 테스트")
    class WeekOfEventDateTest {

        @ParameterizedTest(name = "{0}일, 주말 : {1}")
        @CsvSource({"8,true", "9,true", "7,false", "10,false"})
        @DisplayName("해당 날짜가 주말(금,토)인지 알 수 있다")
        void isWeekendTest(int date, boolean expected) {
            EventDate eventDate = EventDate.from(date);

            boolean actual = eventDate.isWeekend();

            assertThat(actual).isEqualTo(expected);
        }

        @ParameterizedTest(name = "{0}일, 평일 : {1}")
        @CsvSource({"7,true", "10,true", "8,false", "9,false"})
        @DisplayName("해당 날짜가 평일(일~목)인지 알 수 있다")
        void isWeekdayTest(int date, boolean expected) {
            EventDate eventDate = EventDate.from(date);

            boolean actual = eventDate.isWeekDay();

            assertThat(actual).isEqualTo(expected);
        }
    }

    @Test
    @DisplayName("주어진 공식과 날짜에 따라 값을 반환할 수 있다")
    void calculateByDateTest() {
        EventDate date = EventDate.from(13);
        IntUnaryOperator function = value -> 100 * value + 39;
        int expected = 100 * 13 + 39;

        int actual = date.calculateByDate(function);

        assertThat(actual).isEqualTo(expected);
    }

    @Nested
    @DisplayName("동치성 테스트")
    class EqualityTest {

        @Test
        @DisplayName("날짜가 같을 때, 두 날짜의 동치성을 파악할 수 있다")
        void equalsTest_whenSameDate() {
            EventDate date = EventDate.from(5);
            EventDate sameDate = EventDate.from(5);

            assertThat(date).isEqualTo(date)
                    .isEqualTo(sameDate)
                    .hasSameHashCodeAs(sameDate);
        }

        @Test
        @DisplayName("날짜가 다를 때, 두 날짜의 동치성을 파악할 수 있다")
        void equalsTest_whenDifferentDate() {
            EventDate date = EventDate.from(5);
            EventDate differentDate = EventDate.from(6);

            assertThat(date).isNotEqualTo(differentDate);
        }
    }
}
