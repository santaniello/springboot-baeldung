package com.baeldung.springboot;

import com.baeldung.springboot.dtos.ValidationErrorDTO;
import com.baeldung.springboot.exceptions.ApiError;
import com.baeldung.springboot.exceptions.MyConflictException;
import com.baeldung.springboot.exceptions.MyEntityNotFoundException;
import com.baeldung.springboot.exceptions.MyResourceNotFoundException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.InvalidMimeTypeException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class RestResponseEntityExceptionHandler  extends ResponseEntityExceptionHandler {

    // 400

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.info("Bad Request: ", ex);
        logger.debug("Bad Request: ", ex);
        final ApiError apiError = new ApiError(BAD_REQUEST.value(), ex);
        return handleExceptionInternal(ex, apiError, headers, BAD_REQUEST, request);
    }

    @Override
    protected final ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info("Bad Request: ", ex);
        logger.debug("Bad Request: ", ex);
        final BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        final ValidationErrorDTO dto = processFieldErrors(fieldErrors);
        return handleExceptionInternal(ex, dto, headers, BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { ConstraintViolationException.class, DataIntegrityViolationException.class })
    protected final ResponseEntity<Object> handleBadRequest(final RuntimeException ex, final WebRequest request) {
        logger.info("Bad Request: ", ex);
        logger.debug("Bad Request: ", ex);
        final ApiError apiError = new ApiError(BAD_REQUEST.value(), ex);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), BAD_REQUEST, request);
    }

    // 404

    @ExceptionHandler({MyEntityNotFoundException.class, MyResourceNotFoundException.class })
    protected ResponseEntity<Object> handleNotFound(final RuntimeException ex, final WebRequest request) {
        logger.warn("Not Found: ", ex);
        final ApiError apiError = new ApiError(NOT_FOUND.value(), ex);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), NOT_FOUND, request);
    }

    // 409

    @ExceptionHandler({ InvalidDataAccessApiUsageException.class, DataAccessException.class, MyConflictException.class })
    protected ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
        logger.warn("Conflict: ", ex);
        final ApiError apiError = new ApiError(CONFLICT.value(), ex);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), CONFLICT, request);
    }

    // 415

    @ExceptionHandler({ InvalidMimeTypeException.class, InvalidMediaTypeException.class })
    protected ResponseEntity<Object> handleInvalidMimeTypeException(final IllegalArgumentException ex, final WebRequest request) {
        logger.warn("Unsupported Media Type: ", ex);
        final ApiError apiError = new ApiError(UNSUPPORTED_MEDIA_TYPE.value(), ex);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), UNSUPPORTED_MEDIA_TYPE, request);
    }

    // 500

    @ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handle500s(final RuntimeException ex, final WebRequest request) {
        logger.error("500 Status Code", ex);
        final ApiError apiError =  new ApiError(INTERNAL_SERVER_ERROR.value(), ex);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), INTERNAL_SERVER_ERROR, request);
    }

    // UTIL

    private ValidationErrorDTO processFieldErrors(final List<FieldError> fieldErrors) {
        final ValidationErrorDTO dto = new ValidationErrorDTO();
        for (final FieldError fieldError : fieldErrors) {
            final String localizedErrorMessage = fieldError.getDefaultMessage();
            dto.addFieldError(fieldError.getField(), localizedErrorMessage);
        }
        return dto;
    }
}
