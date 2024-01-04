package com.mdt.security.demo;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl {

    public String greeting() {
        return "Hello world from Service";
    }
}
