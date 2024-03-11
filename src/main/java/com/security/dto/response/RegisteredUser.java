package com.security.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredUser {
    private Long id;
    private String username;
    private String name;
    private String role;
    private String jwt;
}
