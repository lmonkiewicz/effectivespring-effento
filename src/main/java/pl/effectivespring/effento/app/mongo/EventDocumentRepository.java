package pl.effectivespring.effento.app.mongo;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import pl.effectivespring.effento.app.mongo.model.EventDocument;

import java.time.ZonedDateTime;
import java.util.List;

@Document(collection = "Events")
public interface EventDocumentRepository extends MongoRepository<EventDocument, String> {

    List<EventDocument> findByDateBetween(ZonedDateTime from, ZonedDateTime to);

    List<EventDocument> findBySubscribedUsersContains(String subsriberUserId);

}
