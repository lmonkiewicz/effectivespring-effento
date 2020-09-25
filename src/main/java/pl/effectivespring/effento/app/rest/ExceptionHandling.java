package pl.effectivespring.effento.app.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.effectivespring.effento.events.exception.EventNotFoundException;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<String> eventNotFoundExceptionHandler(EventNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

}
