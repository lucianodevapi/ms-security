package com.security.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy/MM/dd hh:mm:ss")
    private LocalDateTime time;
}
