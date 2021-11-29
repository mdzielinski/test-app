package app.closer.testapp.flow.service;

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

  public Result evaluate(Formula formula) {
    var i = countOperators(formula);
    return Result.of(0);
  }

  private int countOperators(Formula formula) {
    return formula.getBody().replaceAll("[^+\\-*/]", "").length();
  }
}
