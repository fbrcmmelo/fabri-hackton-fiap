package com.fabri.srv.user.infra.api;

import com.fabri.srv.user.infra.exceptions.UserValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * Creates a ProblemDetail object with the given status and exception.
     *
     * @param status the HTTP status
     * @param ex     the exception to include in the ProblemDetail
     * @return a ProblemDetail object with the specified status and exception details
     */
    private static ProblemDetail getProblemDetail(HttpStatus status, Exception ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, status.getReasonPhrase());
        problemDetail.setTitle(status.name());
        problemDetail.setProperty("error", ex.getMessage());
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception ex) {
        return getProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    @ExceptionHandler(UserValidationException.class)
    public ProblemDetail handleUserValidationException(UserValidationException ex) {
        ProblemDetail problemDetail = getProblemDetail(HttpStatus.BAD_REQUEST, ex);
        problemDetail.setInstance(URI.create("users"));
        return problemDetail;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleEntityNotFoundException(EntityNotFoundException ex) {
        return getProblemDetail(HttpStatus.NOT_FOUND, ex);
    }

}
