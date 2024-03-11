package com.security.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveUser {
    @Size(min = 4)
    private String name;
    private String username;
    @Size(min = 8)
    private String password;
    @Size(min = 8)
    private String repeatedPassword;
}
