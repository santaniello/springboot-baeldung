package com.baeldung.springboot.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {
    private int status;
    private String message;
    private String developerMessage;

    @Override
    public final String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ApiError [status=")
            .append(status)
            .append(", message=")
            .append(message)
            .append(", developerMessage=")
            .append(developerMessage)
            .append("]");
        return builder.toString();
    }
}