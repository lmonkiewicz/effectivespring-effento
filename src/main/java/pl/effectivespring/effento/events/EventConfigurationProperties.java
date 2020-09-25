package pl.effectivespring.effento.events;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix="pl.effento.event")
public class EventConfigurationProperties {

    private OwnedConfig owned = new OwnedConfig();
    private SubscriptionConfig subscription = new SubscriptionConfig();

    @Data
    public static class OwnedConfig {
        private Integer max = 150;
    }

    @Data
    public static class SubscriptionConfig {
        private Integer max = 150;
    }
}
