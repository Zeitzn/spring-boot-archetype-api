package com.archetype.api.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDetails<String>> handleNotFoundException(NotFoundException exception, WebRequest request
    ){
        ErrorDetails<String> errorDetails = new ErrorDetails<>(exception.getCode(), new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDetails<String>> handleBadRequestException(BadRequestException exception, WebRequest request
    ){
        ErrorDetails<String> errorDetails = new ErrorDetails<>(exception.getCode(), new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorDetails<String>> handleAlreadyExistsException(AlreadyExistsException exception, WebRequest request
    ){
        ErrorDetails<String> errorDetails = new ErrorDetails<>(exception.getCode(), new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotAvailableException.class)
    public ResponseEntity<ErrorDetails<String>> handleNotAvailableException(NotAvailableException exception, WebRequest request
    ){
        ErrorDetails<String> errorDetails = new ErrorDetails<>(exception.getCode(), new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ServerErrorException.class)
    public ResponseEntity<ErrorDetails<String>> handleServerErrorException(ServerErrorException exception, WebRequest request
    ){
        ErrorDetails<String> errorDetails = new ErrorDetails<>(exception.getCode(), new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDetails<String>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception, WebRequest request){
        ErrorDetails<String> errorDetails = new ErrorDetails<>("400", new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {
        Set<String> errors = new HashSet<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String message = error.getDefaultMessage();
            errors.add(message);
        });
        ErrorDetails<Set<String>> errorDetails = new ErrorDetails<>(String.valueOf(status.value()), new Date(), errors, request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
