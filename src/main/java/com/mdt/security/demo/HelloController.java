package com.mdt.security.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/say")
public class HelloController {

    @GetMapping(value = "/hello")
    public String sayHello() {
        return "Hello World";
    }
}
