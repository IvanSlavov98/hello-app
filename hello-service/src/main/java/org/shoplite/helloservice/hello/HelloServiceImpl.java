package org.shoplite.helloservice.hello;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello() {
        return "Hello from hello-service!";
    }

}
