package ru.otus.hw.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.otus.hw.exceptions.dto.ErrorDto;

@RestControllerAdvice
public class ExceptionHandleController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleNotFoundException(NotFoundException e) {
        return new ErrorDto(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
        var message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        logger.error(message, e);
        return message;
    }
}
