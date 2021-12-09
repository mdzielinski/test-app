package app.closer.testapp.flow.controller;

import app.closer.testapp.dataflow.ExpressionParsingException;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
class ValidationExceptionHandler {

  @ExceptionHandler({
    ConstraintViolationException.class,
    MethodArgumentNotValidException.class,
    ExpressionParsingException.class,
    NumberFormatException.class
  })
  public ResponseEntity<String> handleConstraintViolationException(Exception e) {

    ResponseEntity<String> response =
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body("validation error");
    log.warn("Handled exception: ", e);
    return response;
  }
}
