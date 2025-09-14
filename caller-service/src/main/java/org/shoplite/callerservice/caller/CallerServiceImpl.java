package org.shoplite.callerservice.caller;

import org.shoplite.callerservice.caller.dto.HelloServiceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CallerServiceImpl implements CallerService {

    private final WebClient webClient;
    private final String helloUrl;

    public CallerServiceImpl(WebClient webClient, @Value("${caller.helloUrl}") String helloUrl) {
        this.webClient = webClient;
        this.helloUrl = helloUrl;
    }

    public Mono<String> callHello() {
        return webClient.get()
                .uri(helloUrl + "/api/v1/hello")
                .retrieve()
                .bodyToMono(HelloServiceResponse.class)
                .map(response -> response.getMessage() + " - proxied by caller-service");
    }
}
