package app.closer.testapp.data.symbol;

import static java.util.Objects.isNull;

import app.closer.testapp.dataflow.ExpressionParsingException;
import java.util.Optional;
import lombok.Getter;

@Getter
public final class Operator extends Symbol {
  public static final String ALLOWED_OPERATOR_SYMBOLS = "+-*/";

  private final Character operator;

  public static Operator form(String symbol) {
    var character =
        Optional.of(isValidOperator(symbol))
            .orElseThrow(
                () ->
                    new ExpressionParsingException(
                        String.format("Failed " + "to parse operator: %s", symbol)));
    return new Operator(character);
  }

  private static Character isValidOperator(String operator) {
    return (!isNull(operator)
            && operator.length() == 1
            && ALLOWED_OPERATOR_SYMBOLS.matches(operator))
        ? operator.charAt(0)
        : null;
  }

  public Operator(Character operator) {
    this.operator = operator;
  }
}
