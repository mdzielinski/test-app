package app.closer.testapp.flow.service;

import static java.util.Objects.isNull;

import app.closer.testapp.data.Formula;
import app.closer.testapp.data.Result;
import java.math.BigDecimal;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    return Result.of(new Expression(formula.getBody()).evaluate());
  }

  private enum Operation {
    ADD(BigDecimal::add, '+'),
    SUB(BigDecimal::subtract, '-'),
    MUL(BigDecimal::multiply, '*'),
    DIV(BigDecimal::divide, '/'),
    UNKNOWN_OPERATION(null, '?');

    final BiFunction<BigDecimal, BigDecimal, BigDecimal> operation;
    final char symbol;

    public static Operation from(char symbol){
      return switch (symbol) {
        case '+' -> ADD;
        case '-' -> SUB;
        case '*' -> MUL;
        case '/'  -> DIV;
        default  -> UNKNOWN_OPERATION;
      };
    }

    Operation(BiFunction<BigDecimal, BigDecimal, BigDecimal> operation, char symbol) {
      this.operation = operation;
      this.symbol = symbol;
    }

    @Override
    public String toString() {
      return String.valueOf(symbol);
    }



  }

  private static class Expression {
    private final Expression lhs;
    private final Operation operation;
    private final Expression rhs;
    private BigDecimal value;


    Expression(String expression) {
      log.debug("parsing expression: {}", expression);
      if (expressionIsASingleValue(expression)) {
        lhs = rhs = null;
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
      Pattern pattern = Pattern.compile(SYMBOLS_REGEX);
      Matcher matcher = pattern.matcher(expression);
        return matcher.find() ? matcher.start() : -1;
      }

    private String lhsExpressionFrom(String expression, int operatorIndex) {
      return expression.substring(operatorIndex + 1);
    }

    private String rhsExpressionFrom(String expression, int operatorIndex) {
      return expression.substring(0, operatorIndex);
    }

    static long countOperators(String expression) {
      Pattern pattern = Pattern.compile(SYMBOLS_REGEX);
      Matcher matcher = pattern.matcher(expression);
      var group = matcher.results();
      return group.count();
    }

    BigDecimal evaluate(){
      if(isNull(value)){
        evaluateExpression();
      }
      return value;
    }

    boolean operatorAndOperandsAreNotNull() {
      return !isNull(operation) && !isNull(lhs) && !isNull(rhs);
    }

    void evaluateExpression() throws ArithmeticException {
      try {
        value =  operation.operation.apply(lhs.evaluate(), rhs.evaluate());

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
      return !isNull(operation) ? lhs.toString() + " " + operation + " " + rhs.toString()
          : value.toString();
    }
  }
}
