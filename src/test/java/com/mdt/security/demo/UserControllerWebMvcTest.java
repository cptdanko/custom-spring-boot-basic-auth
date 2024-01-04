package com.mdt.security.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Ping user")
    @Test
    public void testPingUser() throws Exception {
        this.mockMvc.perform(get("/api/user/ping").with(httpBasic("bhuman", "password")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @DisplayName("Create user without csrf token")
    @Test
    public void testCreateUserWithoutCsrfToken() throws Exception {
        String url = "/api/user/create";
        String postBody = "";
        this.mockMvc.perform(post(url).content(postBody)
                        .with(httpBasic("bhuman", "password")))
                .andDo(print()).andExpect(status().isForbidden());
    }
    @DisplayName("Create user with csrf token")
    @Test
    public void testCreateUserWithCsrfToken() throws Exception {
        String url = "/api/user/create";
        String postBody = "";
        this.mockMvc.perform(post(url).content(postBody)
                        .with(csrf())
                        .with(httpBasic("bhuman", "password")))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    @DisplayName("Create user with valid body")
    @Test
    public void testCreateUserWithoutBody() throws Exception {
        String url = "/api/user/create";
        CreateUserRequest request = CreateUserRequest.builder()
                .username("bhuman")
                .password("password")
                .roles(new String[] {"admin", "user"})
                .build();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String postRequestJson = ow.writeValueAsString(request);
        String postBody = "";
        this.mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postRequestJson)
                        .with(csrf())
                        .with(httpBasic("bhuman", "password")))
                .andDo(print()).andExpect(status().isCreated());
    }

}
