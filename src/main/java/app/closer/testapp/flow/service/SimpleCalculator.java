package app.closer.testapp.flow.service;

import app.closer.testapp.data.Expression;
import app.closer.testapp.data.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile("!dummy")
public class SimpleCalculator implements ICalculator {

  public Result calculate(Expression equation) {
    String result = null;
    log.debug("calculated result: " + result);
    return evaluate(equation);
  }

  private Result evaluate(Expression equation) {
    return null;
  }
}
