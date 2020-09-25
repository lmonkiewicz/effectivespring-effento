package pl.effectivespring.effento.events.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class UserId {

    private final String value;

    public static UserId of(String value) {
        return new UserId(value);
    }

    public String asString() {
        return value;
    }
}
