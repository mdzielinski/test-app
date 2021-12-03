package app.closer.testapp.flow.service;

import static java.util.Comparator.comparingInt;

import app.closer.testapp.data.Formula;
import app.closer.testapp.data.Result;
import app.closer.testapp.data.symbol.Operator;
import app.closer.testapp.dataflow.Expression;
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
    return resolve(Expression.from(formula.getBody()));
  }

  private Result resolve(Expression expression) {
    expression.stream()
        .filter(symbol -> symbol instanceof Operator)
        .map(symbol -> (Operator) symbol)
        .sorted(comparingInt(Operator::getPriority).thenComparingInt(Operator::getOrder))
        .forEachOrdered(System.out::println);
    return Result.of(0);
  }
}
