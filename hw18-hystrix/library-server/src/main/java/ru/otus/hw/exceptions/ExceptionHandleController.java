package ru.otus.hw.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.otus.hw.exceptions.dto.ErrorDto;

@ControllerAdvice
public class ExceptionHandleController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(NotFoundException.class)
    public ErrorDto handleNotFoundException(NotFoundException e) {
        return new ErrorDto(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleException(Exception e) {
        var message = e.getMessage();
        logger.error(message, e);
    }
}
