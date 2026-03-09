package pl.effectivespring.effento;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
class EffentoApplicationTests {

    @Autowired
    private RestTestClient restTestClient;

    @Test
    void contextLoads() {
    }

    @Test
    @Disabled
    void shouldAccessVersionedEvents() {
        restTestClient.get()
                .uri("/events/TODAY")
                .header("effento-user-id", "test-user")
                .header("X-API-Version", "1.0")
                .exchange()
                .expectStatus().isOk();
    }

}
