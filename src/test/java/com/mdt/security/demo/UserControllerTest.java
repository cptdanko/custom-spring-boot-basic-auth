package com.mdt.security.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

/*    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @DisplayName("Ping user with no credentials")
    @Test
    public void testHelloNoAuth() throws Exception {
        ResponseEntity<Object> response = restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/api/user/ping").toString(), Object.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatusCode.valueOf(200));
        LinkedHashMap<String, String> responseMap = (LinkedHashMap<String, String>) response.getBody();
        assert responseMap != null;
        String username = responseMap.get("username");

        Assertions.assertNotNull(username);
        Assertions.assertEquals(username, "bsoni");
    }
    @DisplayName("Create user with no credentials")
    @Test
    public void testCreateUserWithNoCreds() throws Exception {
        URL url = new URL("http://localhost:" + port + "/api/user/create");
        CreateUserRequest cur = null;
        restTemplate.postForEntity(url.toURI(), null, Object.class);
        ResponseEntity<Object> response = restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/api/user/create").toString(), Object.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatusCode.valueOf(401));
    }*/
}

