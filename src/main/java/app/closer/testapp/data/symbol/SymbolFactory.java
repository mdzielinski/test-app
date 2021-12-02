package app.closer.testapp.data.symbol;

public class SymbolFactory {

  public static Symbol symbolFrom(String symbol) {
    if (symbol.length() == 1) {
      if (Operator.ALLOWED_OPERATOR_SYMBOLS.contains(symbol)) {
        return Operator.form(symbol);
      } else {
        return Bracket.from(symbol);
      }
    } else return Number.from(symbol);
  }
}
