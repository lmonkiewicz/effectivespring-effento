package pl.effectivespring.effento.events.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Getter
@ToString
@EqualsAndHashCode
@Builder(builderClassName = "Builder", toBuilder = true)
@JsonDeserialize(builder = Event.Builder.class)
public class Event {
    private UserId owner;
    private String name;
    private String description;
    private ZonedDateTime date;
    private String url;
    private String imgUrl;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {

    }
}
