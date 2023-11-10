package christmas.domain.plan;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.DateInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
}
