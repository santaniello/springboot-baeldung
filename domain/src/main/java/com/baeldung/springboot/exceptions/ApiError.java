package com.baeldung.springboot.exceptions;

import lombok.Data;
import org.apache.commons.lang3.exception.ExceptionUtils;

@Data
public class ApiError {
    private int status;
    private String message;
    private String developerMessage;

    public ApiError(final int httpStatus, final Exception ex){
        this.status = httpStatus;
        this.message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage();
        this.developerMessage = ExceptionUtils.getRootCauseMessage(ex);
    }

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