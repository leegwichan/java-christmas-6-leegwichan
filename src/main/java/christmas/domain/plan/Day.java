package christmas.domain.plan;

import christmas.domain.exception.DateInputException;

public class Day {

    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;

    private final int date;

    private Day(int date) {
        validate(date);
        this.date = date;
    }

    private static void validate(int date) {
        if (isOutOfRange(date)) {
            throw new DateInputException("Date is Out of Range : " + date);
        }
    }

    private static boolean isOutOfRange(int date) {
        return date < MIN_DATE || date > MAX_DATE;
    }

    public static Day from(int date) {
        return new Day(date);
    }
}
