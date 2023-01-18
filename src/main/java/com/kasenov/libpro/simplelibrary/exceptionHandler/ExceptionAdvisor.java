package com.kasenov.libpro.simplelibrary.exceptionHandler;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ExceptionAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CannotSaveException.class)
    public ResponseEntity<Object> cannotSaveClient(CannotSaveException exception) {
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("message", exception.getMessage());
        responseBody.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> cannotFindClientById(NotFoundException exception) {
        Map<String,Object> responseBody = new LinkedHashMap<>();
        responseBody.put("message", exception.getMessage());
        responseBody.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        Map<String,Object> responseBody = new LinkedHashMap<>();
        responseBody.put("errors", errors);
        responseBody.put("timestamp", LocalDateTime.now());
        responseBody.put("status",status);
        return new ResponseEntity<>(responseBody,headers,status);
    }
}
