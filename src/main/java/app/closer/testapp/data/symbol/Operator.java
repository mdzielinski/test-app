package app.closer.testapp.data.symbol;

import static app.closer.testapp.util.RegexHelper.doPatternMatch;

import lombok.Getter;

@Getter
public final class Operator extends Symbol {
  public static final String ALLOWED_OPERATOR_SYMBOLS = "+\\-\\*/";
  private static final String priority_operators = "\\*/";

  private final Character body;
  private int priority;

  public void changePriorityBy(int change) {
    priority += change;
  }

  public static Operator form(String symbol) {
    return new Operator(symbol.charAt(0));
  }

  private Operator(Character body) {
    this.body = body;
    priority = isPriorityOperator(body) ? 1 : 0;
  }

  private boolean isPriorityOperator(Character body) {
    return doPatternMatch(priority_operators, body.toString());
  }

  @Override
  public String toString() {
    return body.toString() + ":" + priority;
  }
}
