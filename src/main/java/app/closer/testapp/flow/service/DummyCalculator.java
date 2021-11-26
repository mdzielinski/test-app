package app.closer.testapp.flow.service;

import app.closer.testapp.data.Equation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile("dummy")
public class DummyCalculator implements ICalculator {

  public Equation calculate(Equation equation) {
    String result;
    result = switch (equation.getExpression().toString()) {
      case "(2+2)*2" -> "8";
      case "2+2*2" -> "6";
      default -> "0";
    };
    equation.setResult(result);
    log.debug("calculated result: " + equation.getResult());
    return equation;
  }
}
