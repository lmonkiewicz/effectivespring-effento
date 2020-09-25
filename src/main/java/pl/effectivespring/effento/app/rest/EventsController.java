package pl.effectivespring.effento.app.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.effectivespring.effento.app.rest.model.EventDTO;
import pl.effectivespring.effento.events.EventService;
import pl.effectivespring.effento.events.exception.EventNotFoundException;
import pl.effectivespring.effento.events.model.EventId;
import pl.effectivespring.effento.events.model.EventsFilter;
import pl.effectivespring.effento.events.model.UserId;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events")
@Slf4j
@RequiredArgsConstructor
public class EventsController {

    public static final String HEADER_USER_ID = "effento-user-id";

    private final EventService eventService;
    private final EventDTOMapper eventDtoMapper = new EventDTOMapper();

    @GetMapping("/{filter}")
    public List<EventDTO> listEvents(
            @RequestHeader(HEADER_USER_ID) UserId userId,
            @PathVariable("filter") EventsFilter filter) {
        return eventService.list(userId, filter).stream()
                .map(eventDtoMapper::map)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody EventDTO event, @RequestHeader(HEADER_USER_ID) UserId userId) {
        return eventService.create(userId, eventDtoMapper.map(event)).asString();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") EventId eventId, @RequestBody EventDTO event, @RequestHeader(HEADER_USER_ID) UserId userId) {
        eventService.update(userId, eventId, eventDtoMapper.map(event));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") EventId eventId, @RequestHeader(HEADER_USER_ID) UserId userId) {
        eventService.delete(userId, eventId);
    }

    @PostMapping("/{id}/subscribe")
    public void subscribe(@PathVariable("id") EventId eventId, @RequestHeader(HEADER_USER_ID) UserId userId) {
        eventService.subscribe(userId, eventId);
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<String> eventNotFoundExceptionHandler(EventNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

}

