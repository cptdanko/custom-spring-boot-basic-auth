package com.mdt.security.demo;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class CreateUserRequest {
    private String username;
    private String password;
    private String[] roles;
}
