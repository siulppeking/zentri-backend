package com.example.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {

    private final String message;
    private final int status;
    private final String path;
    private final LocalDateTime timestamp;

    public ErrorResponse(
            String message,
            int status,
            String path
    ) {
        this.message = message;
        this.status = status;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }
}

