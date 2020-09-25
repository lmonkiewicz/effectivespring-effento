package pl.effectivespring.effento.app.rest.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.effectivespring.effento.app.rest.converter.EventIdFromStringConverter;
import pl.effectivespring.effento.app.rest.converter.EventIdToStringConverter;
import pl.effectivespring.effento.app.rest.converter.UserIdFromStringConverter;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new EventIdFromStringConverter());
        registry.addConverter(new EventIdToStringConverter());

        registry.addConverter(new UserIdFromStringConverter());
    }

}
