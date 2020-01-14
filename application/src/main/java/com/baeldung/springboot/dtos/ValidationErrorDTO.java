package com.baeldung.springboot.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ValidationErrorDTO {

    @Getter
    private final List<FieldErrorDTO> fieldErrors = new ArrayList<>();

    public final void addFieldError(final String path, final String message) {
        final FieldErrorDTO error = new FieldErrorDTO(path, message);
        fieldErrors.add(error);
    }

    @Override
    public final String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ValidationErrorDTO [fieldErrors=")
            .append(fieldErrors)
            .append("]");
        return builder.toString();
    }
}