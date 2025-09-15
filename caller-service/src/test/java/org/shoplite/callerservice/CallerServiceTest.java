package org.shoplite.callerservice;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.shoplite.callerservice.caller.CallerService;
import org.shoplite.callerservice.caller.CallerServiceImpl;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

public class CallerServiceTest {

    static MockWebServer mockWebServer;

    @BeforeAll
    static void start() throws Exception {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void stop() throws Exception {
        mockWebServer.shutdown();
    }

    @Test
    void callHello_extractsMessageAndAppendsSuffix() {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .addHeader("Content-Type", "application/json")
                .setBody("{\"message\":\"Hello from hello-service!\"}"));

        String base = mockWebServer.url("/api/v1").toString();
        WebClient client = WebClient.builder().build();

        CallerService callerService = new CallerServiceImpl(client, base);

        StepVerifier.create(callerService.callHello())
                .expectNext("Hello from hello-service! - proxied by caller-service")
                .verifyComplete();
    }

    @Test
    void callHello_propagatesServerError() {
        mockWebServer.enqueue(new MockResponse().setResponseCode(500));

        String base = mockWebServer.url("/api/v1").toString();
        WebClient client = WebClient.builder().build();
        CallerService callerService = new CallerServiceImpl(client, base);

        StepVerifier.create(callerService.callHello())
                .expectError()
                .verify();
    }

}
