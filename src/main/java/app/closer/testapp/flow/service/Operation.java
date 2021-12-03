package app.closer.testapp.flow.service;

import app.closer.testapp.dataflow.ExpressionParsingException;
import java.util.function.BiFunction;

public enum Operation {
  ADD((l, r) -> l + r, '+'),
  SUBTRACT((l, r) -> l - r, '-'),
  MULTIPLY((l, r) -> l * r, '*'),
  DIVIDE((l, r) -> l / r, '/');

  //todo should have single source of truth
  public static final String ALLOWED_OPERATOR_SYMBOLS = "+-*/";
  private final char symbol;
  private final BiFunction<Double, Double, Double> function;

  public static Operation from(char symbol) {
    return switch (symbol) {
      case '+' -> ADD;
      case '-' -> SUBTRACT;
      case '*' -> MULTIPLY;
      case '/' -> DIVIDE;
      default -> throw new ExpressionParsingException("Following symbol is not handled: %s" + symbol);
    };
  }

  public Double resolve(Double lhs, Double rhs){
    return this.function.apply(lhs, rhs);
}

  Operation(BiFunction<Double, Double, Double> function, char symbol) {
    this.symbol = symbol;
    this.function = function;
  }

  @Override
  public String toString() {
    return String.valueOf(symbol);
  }
}
