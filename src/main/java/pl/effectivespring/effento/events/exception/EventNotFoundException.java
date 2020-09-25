package pl.effectivespring.effento.events.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.effectivespring.effento.events.model.EventId;

import static java.lang.String.format;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Event was not found")
public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException(EventId eventId) {
        super(format("Event '%s' was not found", eventId));
    }
}
