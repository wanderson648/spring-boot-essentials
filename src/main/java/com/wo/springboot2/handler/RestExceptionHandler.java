package com.wo.springboot2.handler;

import com.wo.springboot2.exception.BadRequestException;
import com.wo.springboot2.exception.BadRequestExceptionDetails;
import com.wo.springboot2.exception.ValidationExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException bre) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .title("Bad Request Exception - Check the Documentation")
                        .details(bre.getMessage())
                        .developerMessage(bre.getClass().getName())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .build(), HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .title("Bad Request Exception - Check the Documentation")
                        .details("Check de field(s) error")
                        .developerMessage(ex.getClass().getName())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .fields(fields)
                        .fieldsMessage(fieldsMessage)
                        .build(), HttpStatus.BAD_REQUEST);
    }
}
