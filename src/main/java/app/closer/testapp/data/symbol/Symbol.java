package app.closer.testapp.data.symbol;

public interface Symbol {

  String ALLOWED_OPERATOR_SYMBOLS = "+\\-\\*/";
  String PRIORITY_OPERATOR_SYMBOLS = "\\*/";
  String ALLOWED_BRACKET_SYMBOLS = "()";
  String opening_brackets = "(";
  String ALLOWED_NUMBER_SYMBOLS = "0123456789";

  Double obtain();
}
