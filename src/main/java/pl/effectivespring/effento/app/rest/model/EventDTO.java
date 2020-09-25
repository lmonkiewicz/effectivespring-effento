package pl.effectivespring.effento.app.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import pl.effectivespring.effento.events.model.EventId;

import java.time.ZonedDateTime;


@Builder(builderClassName = "Builder", toBuilder = true)
@Getter
@EqualsAndHashCode
@ToString
@JsonDeserialize(builder = EventDTO.Builder.class)
public class EventDTO {

    private EventId id;
    private String name;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private ZonedDateTime date;
    private String url;
    private String imgUrl;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {

    }
}

