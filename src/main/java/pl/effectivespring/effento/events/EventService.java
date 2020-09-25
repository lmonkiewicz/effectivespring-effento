package pl.effectivespring.effento.events;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.effectivespring.effento.events.exception.EventNotFoundException;
import pl.effectivespring.effento.events.exception.InsufficientPrivilegesForEventOperationException;
import pl.effectivespring.effento.events.model.Event;
import pl.effectivespring.effento.events.model.EventId;
import pl.effectivespring.effento.events.model.EventQuery;
import pl.effectivespring.effento.events.model.EventsFilter;
import pl.effectivespring.effento.events.model.UserId;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService {

    private final EventStorage eventStorage;
    private final EventConfigurationProperties eventConfigurationProperties;

    public EventId create(UserId userId, Event event) {
        log.info("Creating event {} with user {}", event, userId);
        return eventStorage.create(userId, event);
    }

    public void update(UserId userId, EventId eventId, Event event) {
        log.info("Updating event {} as user {}", eventId, userId);
        if (isOwner(userId, eventId)) {
            eventStorage.update(eventId, event);
        } else {
            throw new InsufficientPrivilegesForEventOperationException(userId, eventId);
        }
    }

    public void delete(UserId userId, EventId eventId) {
        log.info("Deleting event {} as user {}", eventId, userId);
        if (isOwner(userId, eventId)) {
            eventStorage.delete(eventId);
        } else {
            throw new InsufficientPrivilegesForEventOperationException(userId, eventId);
        }
    }

    private boolean isOwner(UserId userId, EventId eventId) {
        return eventStorage.find(eventId)
                .map(event -> Objects.equals(event.getOwner(), userId))
                .orElseThrow(() -> new EventNotFoundException(eventId));
    }

    public void subscribe(UserId userId, EventId eventId) {
        eventStorage.subscribe(eventId, userId);
    }

    public List<Event> list(UserId userId, EventsFilter filter) {
        log.info("Listing events for user {}, using filter {}", userId, filter);
        switch (filter) {
            case MY:
                return listUserSubscribedEvents(userId);
            case TODAY:
                return listEventsInFollowingDays(1);
            case WEEK:
            default:
                return listEventsInFollowingDays(7);
        }
    }

    private List<Event> listEventsInFollowingDays(int days) {
        final ZonedDateTime from = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);
        final ZonedDateTime to = from.plusDays(days);
        final EventQuery query = EventQuery.builder()
                .dateFrom(from).dateTo(to)
                .build();
        return eventStorage.findAll(query);
    }

    private List<Event> listUserSubscribedEvents(UserId userId) {
        final EventQuery query = EventQuery.builder().subscriberUserId(userId).build();
        return eventStorage.findAll(query);
    }
}
