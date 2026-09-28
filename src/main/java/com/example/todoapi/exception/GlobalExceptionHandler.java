package com.example.todoapi.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiError> handleResourceNotFound(
      ResourceNotFoundException ex, HttpServletRequest request) {
    return buildError(HttpStatus.NOT_FOUND, "Not Found", ex.getMessage(), request.getRequestURI());
  }

  @ExceptionHandler(BusinessRuleException.class)
  public ResponseEntity<ApiError> handleBusinessRule(
      BusinessRuleException ex, HttpServletRequest request) {
    return buildError(
        HttpStatus.BAD_REQUEST, "Bad Request", ex.getMessage(), request.getRequestURI());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiError> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex, HttpServletRequest request) {
    String message =
        ex.getBindingResult().getFieldErrors().stream()
            .findFirst()
            .map(error -> error.getDefaultMessage())
            .orElse("Validation failed");
    return buildError(
        HttpStatus.BAD_REQUEST, "Validation Failed", message, request.getRequestURI());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ApiError> handleConstraintViolation(
      ConstraintViolationException ex, HttpServletRequest request) {
    String message =
        ex.getConstraintViolations().stream()
            .findFirst()
            .map(violation -> violation.getMessage())
            .orElse("Constraint validation failed");
    return buildError(
        HttpStatus.BAD_REQUEST, "Validation Failed", message, request.getRequestURI());
  }

  private ResponseEntity<ApiError> buildError(
      HttpStatus status, String error, String message, String path) {
    ApiError apiError = new ApiError(LocalDateTime.now(), status.value(), error, message, path);
    return ResponseEntity.status(status).body(apiError);
  }
}
