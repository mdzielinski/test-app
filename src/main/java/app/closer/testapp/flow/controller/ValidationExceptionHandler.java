package app.closer.testapp.flow.controller;

import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
class ValidationExceptionHandler {

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
    ResponseEntity<String> response = ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body("validation error");
    log.warn("Handled exception: ", e);
    log.warn("Returning response: {}", response);
    return response;
  }
}
