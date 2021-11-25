package app.closer.testapp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class Controller {

  private ICalculator calculator;

  @GetMapping("/evaluate/{equation}")
  public String calculate(@PathVariable String equation) {
    log.debug("requested equation: " + equation);
    return calculator.calculate(equation);
  }
}
