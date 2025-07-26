package com.fabri.srv.api.gateway.config;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import java.security.SignatureException;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Creates a ProblemDetail object with the given status and exception.
     *
     * @param ex the exception to include in the ProblemDetail
     * @return a ProblemDetail object with the specified status and exception details
     */
    private static ProblemDetail getProblemDetail(Exception ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.getReasonPhrase());
        problemDetail.setTitle(HttpStatus.UNAUTHORIZED.name());
        problemDetail.setProperty("error", ex.getMessage());
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ProblemDetail handleExpiredJwtException(ExpiredJwtException ex) {
        return getProblemDetail(ex);
    }

    @ExceptionHandler(SignatureException.class)
    public ProblemDetail handleExpiredJwtException(SignatureException ex) {
        return getProblemDetail(ex);
    }
}
