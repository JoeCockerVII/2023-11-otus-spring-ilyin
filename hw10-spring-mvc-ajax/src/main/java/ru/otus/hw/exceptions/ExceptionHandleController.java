package ru.otus.hw.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestControllerAdvice
public class ExceptionHandleController {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(NotFoundException e) {
        return "notFound";
    }

    @ExceptionHandler(Exception.class)
    public void handleException(Exception e) {
        e.printStackTrace();
    }
}
