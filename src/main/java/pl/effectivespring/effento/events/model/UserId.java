package pl.effectivespring.effento.events.model;

public record UserId(String value) {

    public static UserId of(String value) {
        return new UserId(value);
    }

    public String asString() {
        return value;
    }
}
