package app.closer.testapp.flow.service;

import static java.util.Comparator.comparingInt;

import app.closer.testapp.data.Expression;
import app.closer.testapp.data.Result;
import app.closer.testapp.data.symbol.Number;
import app.closer.testapp.data.symbol.Operator;
import app.closer.testapp.dataflow.Formula;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

@Slf4j
@Service
@RequestScope
@Profile("!dummy")
public class BasicCalculator implements ICalculator {

  public Result evaluate(Expression expression) {
    return resolve(Formula.from(expression.getBody()));
  }

  private Result resolve(Formula formula) {
    formula.stream()
        .filter(symbol -> symbol instanceof Operator)
        .map(symbol -> (Operator) symbol)
        .sorted(comparingInt(Operator::getPriority).reversed().thenComparingInt(Operator::getOrder))
        .forEachOrdered(formula::evaluate);
    return Result.of(((Number) formula.getBody().get(0)).getBody());
  }
}
