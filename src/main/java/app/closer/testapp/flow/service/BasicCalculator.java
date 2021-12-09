package app.closer.testapp.flow.service;

import static java.util.Comparator.comparingInt;

import app.closer.testapp.data.Expression;
import app.closer.testapp.data.Result;
import app.closer.testapp.data.symbol.Number;
import app.closer.testapp.data.symbol.Operator;
import app.closer.testapp.data.symbol.Symbol;
import app.closer.testapp.dataflow.Formula;
import java.util.LinkedList;
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
        .forEachOrdered(operator -> evaluate(operator, formula.getBody()));
    return Result.of(((Number) formula.getBody().get(0)).getBody());
  }

  public void evaluate(Operator operator, LinkedList<Symbol> formula) {
    var operationIndex = formula.indexOf(operator);
    var leftOperand = formula.get(operationIndex - 1);
    var rightOperand = formula.get(operationIndex + 1);
    log.debug("current formula: {}", formula);
    var evaluate = evaluate(Operation.from(operator.getBody()), leftOperand, rightOperand);
    formula.set(operationIndex, evaluate);
    formula.remove(operationIndex + 1);
    formula.remove(operationIndex - 1);
  }

  private Symbol evaluate(Operation operation, Symbol leftOperand, Symbol rightOperand) {
    log.debug("evaluating expression: {} {} {}", leftOperand, operation, rightOperand);
    var result =
        operation.resolve(((Number) leftOperand).getBody(), ((Number) rightOperand).getBody());
    return Number.from(result);
  }
}
