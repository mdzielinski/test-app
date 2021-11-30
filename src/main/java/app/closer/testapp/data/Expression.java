package app.closer.testapp.data;

import static java.util.Objects.isNull;

import app.closer.testapp.flow.service.SimpleCalculator;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Expression {

  private final Expression lhs;
  private final Operation operation;
  private final Expression rhs;
  private BigDecimal value;

  public Expression(String expression) {
    log.debug("parsing expression: {}", expression);
    if (expressionIsASingleValue(expression)) {
      lhs = null;
      rhs = null;
      operation = null;
      value = BigDecimal.valueOf(Double.parseDouble(expression));
    } else {
      int operatorIndex = findOperatorIndex(expression);
      operation = Operation.from(expression.charAt(operatorIndex));

      lhs = new Expression(lhsExpressionFrom(expression, operatorIndex));
      rhs = new Expression(rhsExpressionFrom(expression, operatorIndex));

      log.trace("{} >> {} << {}", lhs, operation, rhs);
      value = null;
    }
  }

  private boolean expressionIsASingleValue(String expression) {
    var operatorsNumber = countOperators(expression);
    return operatorsNumber == 0;
  }

  private int findOperatorIndex(String expression) {
    Pattern pattern = Pattern.compile(SimpleCalculator.SYMBOLS_REGEX);
    Matcher matcher = pattern.matcher(expression);
    return matcher.find() ? matcher.start() : -1;
  }

  private String rhsExpressionFrom(String expression, int operatorIndex) {
    return expression.substring(operatorIndex + 1);
  }

  private String lhsExpressionFrom(String expression, int operatorIndex) {
    return expression.substring(0, operatorIndex);
  }

  static long countOperators(String expression) {
    Pattern pattern = Pattern.compile(SimpleCalculator.SYMBOLS_REGEX);
    Matcher matcher = pattern.matcher(expression);
    var group = matcher.results();
    return group.count();
  }

  public BigDecimal evaluate() {
    if (isNull(value)) {
      evaluateExpression();
    }
    return value;
  }

  void evaluateExpression() throws ArithmeticException {
    try {
      value = operation.function.apply(lhs.evaluate(), rhs.evaluate());

    } catch (ArithmeticException e) {
      log.error(
          "Dividing by zero: operand left: {}, operator: {}, operand right {}",
          lhs,
          operation,
          rhs);
      throw e;
    }
    log.debug("EVALUATED expression: {} = {}", this, value);
  }

  @Override
  public String toString() {
    return !isNull(operation)
        ? lhs.toString() + " " + operation + " " + rhs.toString()
        : value.toString();
  }
}
