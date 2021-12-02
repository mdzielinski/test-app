package app.closer.testapp.flow.service;

import app.closer.testapp.data.Expression;
import app.closer.testapp.data.Formula;
import app.closer.testapp.data.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

@Slf4j
@Service
@RequestScope
@Profile("!dummy")
public class SimpleCalculator implements ICalculator {
  public static final String SYMBOLS_REGEX = "[+\\-*/]";

  public Result evaluate(Formula formula) {
    return calculate(Expression.from(formula));
  }

  private Result calculate(Expression expression) {
    return null;
  }
}
