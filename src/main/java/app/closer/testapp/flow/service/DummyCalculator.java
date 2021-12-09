package app.closer.testapp.flow.service;

import app.closer.testapp.data.Expression;
import app.closer.testapp.data.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile("dummy")
public class DummyCalculator implements ICalculator {

  public Result evaluate(Expression expression) {
    Result result;
    result = switch (expression.getBody()) {
      case "(2+2)*2" -> Result.of(8);
      case "2+2*2" -> Result.of(6);
      default -> Result.of(0);
    };
    log.debug("calculated result: " + result);
    return result;
  }
}
