package org.shoplite.callerservice.caller;

import reactor.core.publisher.Mono;

public interface CallerService {
    public Mono<String> callHello();
}
