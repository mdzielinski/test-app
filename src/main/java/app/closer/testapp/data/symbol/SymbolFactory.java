package app.closer.testapp.data.symbol;

import static app.closer.testapp.data.symbol.Bracket.ALLOWED_BRACKET_SYMBOLS;
import static app.closer.testapp.data.symbol.Operator.ALLOWED_OPERATOR_SYMBOLS;

import java.util.regex.Pattern;

public class SymbolFactory {

  public static Symbol symbolFrom(String symbol) {
    var bracket = Pattern.compile(wordRegex(ALLOWED_BRACKET_SYMBOLS)).matcher(symbol);
    var operator = Pattern.compile(wordRegex(ALLOWED_OPERATOR_SYMBOLS)).matcher(symbol);

    if (operator.matches()) return Operator.form(symbol);
    if (bracket.matches()) return Bracket.from(symbol);
    return Number.from(symbol);
  }

  private static String wordRegex(String word) {
    return "^[" + word + "]$";
  }
}
