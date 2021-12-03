package app.closer.testapp.data.symbol;

import static app.closer.testapp.util.RegexHelper.doPatternMatch;

import lombok.Getter;

@Getter
public final class Operator extends Symbol {
  public static final String ALLOWED_OPERATOR_SYMBOLS = "+\\-\\*/";
  private static final String priority_operators = "\\*/";
  private static final int PRIORITY_CHANGE_PER_LEVEL = 2;
  private final char body;
  private int priority;
  private int order;

  public void changePriorityBy(int levels) {
    priority += levels * PRIORITY_CHANGE_PER_LEVEL;
  }

  public static Operator form(String symbol, int order) {
    return new Operator(symbol.charAt(0), order);
  }

  private Operator(Character operator, int order) {
    this.body = operator;
    this.order = order;
    priority = isPriorityOperator(operator) ? 1 : 0;
  }

  private boolean isPriorityOperator(Character body) {
    return doPatternMatch(priority_operators, body.toString());
  }

  @Override
  public String toString() {
    return body + "<P:" + priority + "O:" + order + ">";
  }
}
