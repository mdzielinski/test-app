package app.closer.testapp.flow.service;

import app.closer.testapp.dataflow.ExpressionParsingException;
import java.math.BigDecimal;
import java.util.function.BiFunction;

public enum Operation {
  ADD(BigDecimal::add, '+', 0),
  SUBTRACT(BigDecimal::subtract, '-', 0),
  MULTIPLY(BigDecimal::multiply, '*', 1),
  DIVIDE(BigDecimal::divide, '/', 1);

  //todo should have single source of truth
  public static final String ALLOWED_OPERATOR_SYMBOLS = "+-*/";
  private final char symbol;
  private int priority;

  public static Operation from(char symbol) {
    return switch (symbol) {
      case '+' -> ADD;
      case '-' -> SUBTRACT;
      case '*' -> MULTIPLY;
      case '/' -> DIVIDE;
      default -> throw new ExpressionParsingException("Following symbol is not handled: %s" + symbol);
    };
  }

  public void priority(int increment){
    this.priority += increment;
  }

  public int priority() {
    return this.priority;
  }

  Operation(BiFunction<BigDecimal, BigDecimal, BigDecimal> function, char symbol, int priority) {
    this.symbol = symbol;
  }

  @Override
  public String toString() {
    return String.valueOf(symbol);
  }
}
