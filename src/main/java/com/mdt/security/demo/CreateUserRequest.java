package com.mdt.security.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CreateUserRequest {
    private String username;
    private String password;
    private String[] roles;
}
