package app.closer.testapp.dataflow;

import static app.closer.testapp.data.symbol.Symbol.BRACKET_SYMBOLS;
import static app.closer.testapp.data.symbol.Symbol.OPERATOR_SYMBOLS;
import static app.closer.testapp.data.symbol.SymbolFactory.symbolFrom;
import static java.util.Objects.isNull;

import app.closer.testapp.data.symbol.Bracket;
import app.closer.testapp.data.symbol.Number;
import app.closer.testapp.data.symbol.Operator;
import app.closer.testapp.data.symbol.Symbol;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public final class Formula {

  private final LinkedList<Symbol> body;

  public static Formula from(String expression) {
    return new Formula(parse(expression));
  }

  private static LinkedList<Symbol> parse(String expression) {
    var symbols = new LinkedList<Symbol>();
    var tokenizer = getTokenizerFor(expression);

    int currentPriority = 0;
    int symbolOrdinalNumber = 0;

    Symbol last = null;
    while (tokenizer.hasMoreTokens()) {

      var current = symbolFrom(tokenizer.nextToken(), ++symbolOrdinalNumber, currentPriority);

      if (nextIsNegativeNumber(last, current)) {
        current = symbolFrom("-" + tokenizer.nextToken(), ++symbolOrdinalNumber, currentPriority);
        if (!(current instanceof Number))
          throw new ExpressionParsingException("unexpected " + "token:" + current);
      }

      if (current instanceof Bracket) {
        currentPriority += ((Bracket) current).isOpening() ? 1 : (-1);
        validateCurrentPriority(currentPriority);

      } else {

        symbols.add(current);
      }
      last = current;
    }
    validateOverallBrackets(currentPriority);
    return symbols;
  }

  private static StringTokenizer getTokenizerFor(String expression) {
    return new StringTokenizer(expression, deliminatorsOfBracketsAndOperators(), true);
  }

  private static boolean nextIsNegativeNumber(Symbol previous, Symbol current) {
    return isNull(previous) && isMinusOperator(current)
        || previous instanceof Operator && !isMinusOperator(previous) && isMinusOperator(current)
        || previous instanceof Bracket
            && ((Bracket) previous).isOpening()
            && isMinusOperator(current);
  }

  private static void validateCurrentPriority(int currentPriority) {
    if (currentPriority < 0) throw new ExpressionParsingException("Brackets malformed!");
  }

  private static void validateOverallBrackets(int currentPriority) {
    if (currentPriority != 0) throw new ExpressionParsingException("Brackets malformed!");
  }

  private static String deliminatorsOfBracketsAndOperators() {
    return BRACKET_SYMBOLS + OPERATOR_SYMBOLS;
  }

  private static boolean isMinusOperator(Symbol symbol) {
    return symbol instanceof Operator && String.valueOf(((Operator) symbol).getBody()).equals("-");
  }

  public Stream<Symbol> stream() {
    return body.stream();
  }

  private Formula(LinkedList<Symbol> expression) {
    this.body = expression;
  }
}
