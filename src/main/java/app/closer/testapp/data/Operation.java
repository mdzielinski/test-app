package app.closer.testapp.data;

import java.math.BigDecimal;
import java.util.function.BiFunction;

public enum Operation {
  ADD(BigDecimal::add, '+'),
  SUB(BigDecimal::subtract, '-'),
  MUL(BigDecimal::multiply, '*'),
  DIV(BigDecimal::divide, '/'),
  UNKNOWN_OPERATION(null, '?');

  public final BiFunction<BigDecimal, BigDecimal, BigDecimal> function;
  final char symbol;

  public static Operation from(char symbol) {
    return switch (symbol) {
      case '+' -> ADD;
      case '-' -> SUB;
      case '*' -> MUL;
      case '/' -> DIV;
      default -> UNKNOWN_OPERATION;
    };
  }

  Operation(BiFunction<BigDecimal, BigDecimal, BigDecimal> function, char symbol) {
    this.function = function;
    this.symbol = symbol;
  }

  @Override
  public String toString() {
    return String.valueOf(symbol);
  }


}
