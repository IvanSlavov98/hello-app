package org.shoplite.callerservice.caller;

import org.shoplite.callerservice.caller.dto.CallerServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/caller")
public class CallerController {
    private final CallerService callerService;

    public CallerController(CallerService callerService) {
        this.callerService = callerService;
    }

    @GetMapping("call-hello")
    public Mono<ResponseEntity<CallerServiceResponse>> callHello() {
        return this.callerService.callHello()
                .map(CallerServiceResponse::new)
                .map(ResponseEntity::ok)
                .onErrorResume(ex ->
                        Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }
}
