package christmas.domain.plan;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class EventScheduleTest {

    @Nested
    @DisplayName("스케줄 검증 테스트")
    class ValidationTest {

        @Test
        @DisplayName("null을 인자로 받을 경우, 예외를 던진다")
        void validateNotNullTest() {

            assertThatThrownBy(() -> new EventSchedule(null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        @DisplayName("스케줄에 해당하는 일자가 없을 경우, 예외를 던진다")
        void validateNotEmptyTest() {
            Set<EventDate> dates = Set.of();

            assertThatThrownBy(() -> new EventSchedule(dates))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("schedule must have one or more date");
        }
    }
}
