package pl.effectivespring.effento.events.model;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record EventQuery(UserId subscriberUserId, ZonedDateTime dateFrom, ZonedDateTime dateTo) {}
