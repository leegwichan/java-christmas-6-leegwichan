package christmas.domain.plan;

import christmas.exception.DateInputException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

public class EventDate {

    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;

    private static final Set<DayOfWeek> WEEKEND = Set.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);

    private final LocalDate date;

    private EventDate(int date) {
        validate(date);
        this.date = LocalDate.of(YEAR, MONTH, date);
    }

    private static void validate(int date) {
        if (isOutOfRange(date)) {
            throw new DateInputException("Date is Out of Range : " + date);
        }
    }

    private static boolean isOutOfRange(int date) {
        return date < MIN_DATE || date > MAX_DATE;
    }

    public static EventDate from(int date) {
        return new EventDate(date);
    }

    public boolean isWeekend() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return WEEKEND.contains(dayOfWeek);
    }

    public boolean isWeekDay() {
        return !isWeekend();
    }
}
