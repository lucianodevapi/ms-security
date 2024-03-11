package com.security.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private String backedMessage;
    private String message;
    private int httpCode;
    private LocalDateTime time;
}
