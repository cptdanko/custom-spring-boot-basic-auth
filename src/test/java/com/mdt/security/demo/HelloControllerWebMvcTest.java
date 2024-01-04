package com.mdt.security.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloController.class)
public class HelloControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private HelloServiceImpl helloService;

    @DisplayName("Say hello with right Credentials")
    @Test
    void testSayHello() throws Exception {
        when(helloService.greeting()).thenReturn("Hello world from Service");
        this.mockMvc.perform(get("/say/hello/service").with(httpBasic("bhuman", "password")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello world from Service")));
    }
    @DisplayName("Say hello with wrong Credentials")
    @Test
    void testSayHelloWithIncorrectCredentials() throws Exception {
        when(helloService.greeting()).thenReturn("Hello world from Service");
        this.mockMvc.perform(get("/say/hello/service").with(httpBasic("user", "123")))
                .andDo(print()).andExpect(status().isUnauthorized());
    }
    @DisplayName("Say hello with no Credentials")
    @Test
    void testSayHelloWithNoCredentials() throws Exception {
        when(helloService.greeting()).thenReturn("Hello world from Service");
        this.mockMvc.perform(get("/say/hello/service"))
                .andDo(print()).andExpect(status().isUnauthorized());
    }
}
