package app.closer.testapp.data.symbol;

import static app.closer.testapp.util.RegexHelper.patternMatches;

import lombok.Getter;

@Getter
public final class Operator extends Symbol {

  private static final int priority_change_per_bracket = 10;
  private final char body;
  private final int order;
  private final int priority;

  public static Operator form(String symbol, int order, int priority) {
    return new Operator(symbol.charAt(0), order, priority);
  }

  private Operator(Character operator, int order, int priorityByBrackets) {
    this.body = operator;
    this.order = order;
    var priorityByOperationType = hasPriority(operator) ? 1 : 0;
    this.priority = priorityByOperationType + (priorityByBrackets * priority_change_per_bracket);
  }

  private boolean hasPriority(Character body) {
    return patternMatches(PRIORITY_OPERATOR_SYMBOLS, body.toString());
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public String toString() {
    return body + " p:" + priority + " o" + order;
  }
}
