package com.fabri.srvappointment.infra.config;

import com.fabri.srvappointment.infra.exception.DomainException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.time.Instant;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final TokenHolder tokenHolder;

    /**
     * Creates a ProblemDetail object with the given status and exception.
     *
     * @param status the HTTP status
     * @param ex     the exception to include in the ProblemDetail
     * @return a ProblemDetail object with the specified status and exception details
     */
    private static ProblemDetail getProblemDetail(HttpStatus status, Exception ex) {
        log.error(ex.getMessage(), ex);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, status.getReasonPhrase());
        problemDetail.setTitle(status.name());
        problemDetail.setProperty("error", ex.getMessage());
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception ex) {
        ProblemDetail problemDetail = getProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex);
        problemDetail.setProperty("error", "An unexpected error occurred");

        return problemDetail;
    }

    @ExceptionHandler(DomainException.class)
    public ProblemDetail handleDomainException(DomainException ex) {
        ProblemDetail problemDetail = getProblemDetail(HttpStatus.BAD_REQUEST, ex);
        problemDetail.setProperty("error", "Domain error occurred: " + ex.getMessage());

        return problemDetail;
    }

    @ExceptionHandler({SQLException.class, JDBCException.class, HibernateException.class})
    public ProblemDetail handleJDBCException(Exception ex) {
        ProblemDetail problemDetail = getProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex);
        problemDetail.setProperty("error", "Database error occurred");
        return problemDetail;
    }

    @ExceptionHandler(FeignException.class)
    public ProblemDetail handleFeignException(FeignException ex) {
        ProblemDetail problemDetail = getProblemDetail(HttpStatus.valueOf(ex.status()), ex);
        problemDetail.setProperty("error", "Feign client error: " + ex.getMessage());
        tokenHolder.clear();
        return problemDetail;
    }

}
