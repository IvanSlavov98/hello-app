package org.shoplite.helloservice;

import org.junit.jupiter.api.Test;
import org.shoplite.helloservice.hello.HelloController;
import org.shoplite.helloservice.hello.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    HelloService helloService;

    @Test
    void hello_returnsDto() throws Exception {
        when(this.helloService.sayHello()).thenReturn("Hello from hello-service!");

        mockMvc.perform(get("/api/v1/hello"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello from hello-service!"));
    }

}
