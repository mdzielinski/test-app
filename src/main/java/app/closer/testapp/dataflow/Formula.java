package app.closer.testapp.dataflow;

import static app.closer.testapp.data.symbol.Symbol.BRACKET_SYMBOLS;
import static app.closer.testapp.data.symbol.Symbol.OPERATOR_SYMBOLS;
import static app.closer.testapp.data.symbol.SymbolFactory.symbolFrom;
import static java.util.Objects.isNull;

import app.closer.testapp.data.symbol.Bracket;
import app.closer.testapp.data.symbol.Number;
import app.closer.testapp.data.symbol.Operator;
import app.closer.testapp.data.symbol.Symbol;
import app.closer.testapp.flow.service.Operation;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public final class Formula {

  LinkedList<Symbol> body;

  public static Formula from(String expression) {
    return new Formula(parse(expression));
  }

  // todo refactor
  public void evaluate(Operator operator) {
    System.out.println(body);
    var index = body.indexOf(operator);
    body.set(
        index - 1,
        evaluate(Operation.from(operator.getBody()), body.get(index - 1), body.get(index + 1)));
    body.remove(index);
    body.remove(index);
  }

  private Symbol evaluate(Operation operation, Symbol leftOperand, Symbol rightOperand) {
    return Number.from(
        operation.resolve(((Number) leftOperand).getBody(), ((Number) rightOperand).getBody()));
  }

  private static LinkedList<Symbol> parse(String expression) {
    var symbols = new LinkedList<Symbol>();
    var tokenizer = getTokenizerFor(expression);

    int currentPriority = 0;
    int symbolOrdinalNumber = 0;

    Symbol last = null;
    while (tokenizer.hasMoreTokens()) {
      var current = symbolFrom(tokenizer.nextToken(), symbolOrdinalNumber++, currentPriority);
      if (negativeNumber(last, current)) {

      }

      if (current instanceof Bracket) {
        currentPriority = updateAndValidate(currentPriority, current);
      } else {
        symbols.add(current);
      }
      last = current;
    }
    validateOverallBrackets(currentPriority);
    System.out.println(symbols);
    return symbols;
  }

  private static boolean negativeNumber(Symbol previous, Symbol current) {
    return (isNull(previous) || isMinusOperator(previous)) && isMinusOperator(current);
  }

  private static boolean isMinusOperator(Symbol symbol) {
    return symbol instanceof Operator && symbol.equals("-");
  }

  private static int updateAndValidate(int currentPriority, Symbol bracket) {
    currentPriority += ((Bracket) bracket).isOpening() ? 1 : -1;
    validateCurrentPriority(currentPriority);
    return currentPriority;
  }

  private static void validateOverallBrackets(int currentPriority) {
    if (currentPriority != 0) throw new ExpressionParsingException("Brackets malformed!");
  }

  private static StringTokenizer getTokenizerFor(String expression) {
    return new StringTokenizer(expression, deliminatorsOfBracketsAndOperators(), true);
  }

  private static String deliminatorsOfBracketsAndOperators() {
    return BRACKET_SYMBOLS + OPERATOR_SYMBOLS;
  }

  private static void validateCurrentPriority(int currentPriority) {
    if (currentPriority < 0) throw new ExpressionParsingException("Brackets malformed!");
  }

  public Stream<Symbol> stream() {
    return body.stream();
  }

  private Formula(LinkedList<Symbol> expression) {
    this.body = expression;
  }
}
