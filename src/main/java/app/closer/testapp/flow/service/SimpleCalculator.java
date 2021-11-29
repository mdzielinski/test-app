package app.closer.testapp.flow.service;

import app.closer.testapp.data.Formula;
import app.closer.testapp.data.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile("!dummy")
public class SimpleCalculator implements ICalculator {

  public static final char ADD = '+';
  public static final char SUB = '-';
  public static final char MUL = '*';
  public static final char DIV = '/';

  public Result evaluate(Formula formula) {
    var i = countOperators(formula);
    return Result.of(0);
  }

  private int countOperators(Formula formula) {
    return formula.getBody().replaceAll("[^+\\-*/]", "").length();
  }
}
