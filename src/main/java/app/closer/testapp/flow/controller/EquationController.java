package app.closer.testapp.flow.controller;

import app.closer.testapp.data.Equation;
import app.closer.testapp.data.Expression;
import app.closer.testapp.flow.service.ICalculator;
import java.util.UUID;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Validated
@Slf4j
public class EquationController {

  private ICalculator calculator;

  @GetMapping("/evaluate/{expression}")
  public String calculate(@PathVariable @Valid Expression expression) {
    UUID requestUUID = UUID.randomUUID();
    log.info("UUID: {}, expression: {}", requestUUID, expression);
    return calculator.calculate(new Equation(expression, requestUUID)).getResult();
  }
}
