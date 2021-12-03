package app.closer.testapp.flow.service;

import static java.util.Comparator.comparingInt;

import app.closer.testapp.data.Formula;
import app.closer.testapp.data.Result;
import app.closer.testapp.data.symbol.Number;
import app.closer.testapp.data.symbol.Operator;
import app.closer.testapp.data.symbol.Symbol;
import app.closer.testapp.dataflow.Expression;
import java.util.LinkedList;
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
        .forEachOrdered(operator -> expression.evaluate());
    return Result.of(0);
  }

  private void solve(Operator operator, LinkedList<Symbol> expression) {
    int operationIndex = expression.indexOf(operator);
    Number leftOperand = (Number) expression.get(operationIndex - 1);
    Number rightOperand = (Number) expression.get(operationIndex + 1);
    var result =
        (Symbol) Number.from(operator.resolve(leftOperand.getBody(), rightOperand.getBody()));
    expression.set(operationIndex - 1, result);
    expression.remove(operationIndex);
    expression.remove(operationIndex);
  }
}
