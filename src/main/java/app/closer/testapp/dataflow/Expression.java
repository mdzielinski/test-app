package app.closer.testapp.dataflow;

import static app.closer.testapp.data.symbol.Bracket.ALLOWED_BRACKET_SYMBOLS;
import static app.closer.testapp.data.symbol.Operator.ALLOWED_OPERATOR_SYMBOLS;
import static app.closer.testapp.data.symbol.SymbolFactory.symbolFrom;

import app.closer.testapp.data.symbol.Bracket;
import app.closer.testapp.data.symbol.Operator;
import app.closer.testapp.data.symbol.Symbol;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public final class Expression {

  LinkedList<Symbol> body;

  public static Expression from(String expression) {
    return new Expression(parse(expression));
  }

  public Stream<Symbol> stream() {
    return body.stream();
  }

  private Expression(LinkedList<Symbol> expression) {
    this.body = expression;
  }

  private static LinkedList<Symbol> parse(String expression) {
    LinkedList<Symbol> symbols = new LinkedList<>();
    var tokenizer = new StringTokenizer(expression, deliminatorsOfBracketsAndOperators(), true);

    int currentPriority = 0;
    int currentOrder = 0;
    while (tokenizer.hasMoreTokens()) {
      var symbol = symbolFrom(tokenizer.nextToken(), ++currentOrder);

      if (symbol instanceof Bracket) {
        currentPriority += obtainFrom((Bracket) (symbol));
      }

      if (symbol instanceof Operator) {
        updateTo(symbol, currentPriority);
      }

      if (!(symbol instanceof Bracket)) {
        symbols.add(symbol);
      }
    }
    ifBracketsMalformedThrow(currentPriority);
    System.out.println(symbols);
    return symbols;
  }

  private static String deliminatorsOfBracketsAndOperators() {
    return ALLOWED_BRACKET_SYMBOLS + ALLOWED_OPERATOR_SYMBOLS;
  }

  private static int obtainFrom(Bracket bracket) {
    return bracket.isOpening() ? 1 : (-1);
  }

  private static void updateTo(Symbol symbol, int currentPriority) {
    if (currentPriority < 0) throwException("Brackets malformed!");
    ((Operator) symbol).changePriorityBy(currentPriority);
  }

  private static void ifBracketsMalformedThrow(int currentPriority) {
    if (currentPriority != 0) throwException("Brackets malformed!");
  }

  private static void throwException(String message) {
    throw new ExpressionParsingException(message);
  }
}
