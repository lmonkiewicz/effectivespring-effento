package pl.effectivespring.effento.events.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Builder
@Getter
@ToString
public class EventQuery {
    private UserId subscriberUserId;
    private ZonedDateTime dateFrom;
    private ZonedDateTime dateTo;
}
