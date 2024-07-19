package com.example.msjira.controller;

import com.example.msjira.exceptions.CantModifyException;
import com.example.msjira.exceptions.NotFoundException;
import com.example.msjira.model.exceptions.ExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidations(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach((err) ->
            errors.put(err.getField(), err.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handleNotFound(NotFoundException exception) {
        log.error(exception.getLogMessage());
        return new ExceptionDto(exception.getMessage());
    }

    @ExceptionHandler(CantModifyException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionDto handleModify(CantModifyException exception) {
        log.error(exception.getLogMessage());
        return new ExceptionDto(exception.getMessage());
    }


}
