package app.closer.testapp.data.symbol;

import java.util.regex.Pattern;
import lombok.Getter;

@Getter
public final class Operator extends Symbol {
  public static final String ALLOWED_OPERATOR_SYMBOLS = "+\\-\\*/";
  private static final String priority_operators = "\\*/";

  private final Character body;
  private int priority;

  public static Operator form(String symbol) {
    return new Operator(symbol.charAt(0));
  }

  private Operator(Character body) {
    this.body = body;
    priority = doesBodyMatchPattern(body, priority_operators) ? 1 : 0;
  }

  private boolean doesBodyMatchPattern(Character body, String pattern) {
    return Pattern.compile(pattern).matcher(body.toString()).matches();
  }

  @Override
  public String toString() {
    return body.toString();
  }
}
