package pl.effectivespring.effento;

import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.effectivespring.effento.events.EventConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        EventConfigurationProperties.class
})
public class EffentoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EffentoApplication.class, args);
    }

}
