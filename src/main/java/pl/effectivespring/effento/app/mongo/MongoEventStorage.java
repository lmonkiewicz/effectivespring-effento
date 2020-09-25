package pl.effectivespring.effento.app.mongo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.effectivespring.effento.app.mongo.model.EventDocument;
import pl.effectivespring.effento.events.EventStorage;
import pl.effectivespring.effento.events.exception.EventNotFoundException;
import pl.effectivespring.effento.events.model.Event;
import pl.effectivespring.effento.events.model.EventId;
import pl.effectivespring.effento.events.model.EventQuery;
import pl.effectivespring.effento.events.model.UserId;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class MongoEventStorage implements EventStorage {

    private final EventDocumentRepository eventDocumentRepository;
    private final EventDocumentMapper eventDocumentMapper = new EventDocumentMapper();

    @Override
    public EventId create(UserId userId, Event event) {
        final EventDocument eventDocument = eventDocumentMapper.toEventDocument(userId, event);
        final EventDocument document = eventDocumentRepository.save(eventDocument);

        return EventId.of(document.getId());
    }

    @Override
    public void update(EventId eventId, Event event) {
        final EventDocument eventDocument = eventDocumentRepository.findById(eventId.asString())
                .orElseThrow(() -> new EventNotFoundException(eventId));

        eventDocumentMapper.updateEventDocument(event, eventDocument);

        eventDocumentRepository.save(eventDocument);
    }

    @Override
    public void delete(EventId eventId) {
        eventDocumentRepository.deleteById(eventId.asString());
    }

    @Override
    public Optional<Event> find(EventId eventId) {
        return eventDocumentRepository.findById(eventId.asString())
                .map(eventDocumentMapper::fromEventDocument);
    }

    @Override
    public void subscribe(EventId eventId, UserId userId) {
        final EventDocument eventDocument = eventDocumentRepository.findById(eventId.asString()).orElseThrow(() -> new EventNotFoundException(eventId));

        eventDocument.getSubscribedUsers().add(userId.asString());
        eventDocumentRepository.save(eventDocument);
    }

    @Override
    public List<Event> findAll(EventQuery query) {
        return queryDocuments(query).stream()
                .map(eventDocumentMapper::fromEventDocument)
                .collect(Collectors.toList());
    }

    private List<EventDocument> queryDocuments(EventQuery query) {
        log.info("Performing query: {}", query);
        final ZonedDateTime from = query.getDateFrom();
        final ZonedDateTime to = query.getDateTo();

        if (query.getSubscriberUserId() != null) {
            final String subscribedUserId = query.getSubscriberUserId().asString();
            return eventDocumentRepository.findBySubscribedUsersContains(subscribedUserId);
        } else if (from != null && to != null){
            return eventDocumentRepository.findByDateBetween(from, to);
        } else {
            log.info("Empty query, returning empty list");
            return Collections.emptyList();
        }
    }
}
