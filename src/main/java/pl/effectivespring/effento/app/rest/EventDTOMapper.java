package pl.effectivespring.effento.app.rest;

import pl.effectivespring.effento.app.rest.model.EventDTO;
import pl.effectivespring.effento.events.model.Event;

public class EventDTOMapper {

    public EventDTO map(Event event) {
        return EventDTO.builder()
                .name(event.name())
                .description(event.description())
                .imgUrl(event.imgUrl())
                .url(event.url())
                .date(event.date())
                .build();
    }

    public Event map(EventDTO event) {
        return Event.builder()
                .name(event.getName())
                .description(event.getDescription())
                .imgUrl(event.getImgUrl())
                .url(event.getUrl())
                .date(event.getDate())
                .build();
    }
}
