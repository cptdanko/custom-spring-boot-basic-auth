package com.mdt.security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/say")
public class HelloController {
    @Autowired
    private HelloServiceImpl helloService;

    @GetMapping(value = "/hello")
    public String sayHello() {
        return "Hello World";
    }
    @GetMapping(value = "/helloNoAuth")
    public String helloNoAuth() {
        return "Hello World with no auth";
    }
    @GetMapping(value = "/hello/service")
    public ResponseEntity<String> helloFromService() {
        return new ResponseEntity<>(helloService.greeting(), HttpStatus.OK);
    }
}
