package christmas.domain.plan;

import static java.util.stream.Collectors.toSet;

import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;

public class EventSchedule {

    private final Set<EventDate> dates;

    EventSchedule(Set<EventDate> dates) {
        validate(dates);
        this.dates = dates;
    }

    private static void validate(Set<EventDate> dates) {
        validateNotNull(dates);
        validateNotEmpty(dates);
    }

    private static void validateNotNull(Set<EventDate> dates) {
        Objects.requireNonNull(dates);
    }

    private static void validateNotEmpty(Set<EventDate> dates) {
        if (dates.isEmpty()) {
            throw new IllegalArgumentException("schedule must have one or more date");
        }
    }

    public static EventSchedule from(Set<Integer> dates) {
        Set<EventDate> eventDates = dates.stream()
                .map(EventDate::from).collect(toSet());

        return new EventSchedule(eventDates);
    }

    public static EventSchedule of(int startDate, int endDate) {
        Set<EventDate> eventDates = IntStream.rangeClosed(startDate, endDate)
                .mapToObj(EventDate::from).collect(toSet());

        return new EventSchedule(eventDates);
    }

    public boolean contains(EventDate date) {
        return dates.contains(date);
    }
}
