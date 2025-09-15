package org.shoplite.callerservice;

import org.junit.jupiter.api.Test;
import org.shoplite.callerservice.caller.CallerController;
import org.shoplite.callerservice.caller.CallerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@WebFluxTest(controllers = CallerController.class)
public class CallerControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private CallerService callerService;

    @Test
    void hello_returnsDto() {
        when(callerService.callHello())
                .thenReturn(Mono.just("Hello from hello-service! - proxied by caller-service"));

        webTestClient.get().uri("/api/v1/caller/call-hello")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.message").isEqualTo("Hello from hello-service! - proxied by caller-service");
    }
}
