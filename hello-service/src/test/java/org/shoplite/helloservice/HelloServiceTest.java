package org.shoplite.helloservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.shoplite.helloservice.hello.HelloService;
import org.shoplite.helloservice.hello.HelloServiceImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloServiceTest {

    @Test
    void sayHello_returnsHello(){
        HelloService helloService = new HelloServiceImpl();
        assertEquals("Hello from hello-service!", helloService.sayHello());
    }

}
