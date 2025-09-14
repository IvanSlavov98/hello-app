package org.shoplite.helloservice.hello;

import org.shoplite.helloservice.hello.dto.HelloServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("hello")
    public ResponseEntity<HelloServiceResponse> sayHello() {
        return new ResponseEntity<>(
                new HelloServiceResponse(this.helloService.sayHello()),
                HttpStatus.OK);
    }

}
