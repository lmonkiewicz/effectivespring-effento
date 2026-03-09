package pl.effectivespring.effento.events.model;

import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;

import java.time.ZonedDateTime;

@Builder(builderClassName = "Builder", toBuilder = true)
@JsonDeserialize(builder = Event.Builder.class)
public record Event(
     UserId owner,
     String name,
     String description,
     ZonedDateTime date,
     String url,
     String imgUrl) {

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {

    }
}
