package pl.effectivespring.effento.events;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix="pl.effento.event")
public record EventConfigurationProperties(
    @DefaultValue OwnedConfig owned,
    @DefaultValue SubscriptionConfig subscription
) {
    public record OwnedConfig(@DefaultValue("150") Integer max) {}
    public record SubscriptionConfig(@DefaultValue("150") Integer max) {}
}
