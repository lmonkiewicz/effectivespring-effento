package pl.effectivespring.effento.events.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class EventId {

    private final String value;

    public static EventId of(String value) {
        return new EventId(value);
    }

    public String asString() {
        return value;
    }
}
