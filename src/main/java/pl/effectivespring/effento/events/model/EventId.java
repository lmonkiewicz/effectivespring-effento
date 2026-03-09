package pl.effectivespring.effento.events.model;

public record EventId(String value) {

    public static EventId of(String value) {
        return new EventId(value);
    }

    public String asString() {
        return value;
    }
}
