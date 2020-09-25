package pl.effectivespring.effento.app.mongo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class EventDocument {

    @Id
    private String id;
    private String name;
    private String description;
    private ZonedDateTime date;
    private String url;
    private String imgUrl;

    private Set<String> subscribedUsers = new HashSet<>();

    private String ownerUserId;
}
