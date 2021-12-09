package app.closer.testapp.data.symbol;

import java.util.UUID;
import javax.validation.constraints.NotNull;

public abstract class Symbol {
  public static final String OPERATOR_SYMBOLS = "+\\-\\*/";
  public static final String PRIORITY_OPERATOR_SYMBOLS = "\\*/";
  public static final String BRACKET_SYMBOLS = "()";
  public static final String OPENING_BRACKETS_SYMBOLS = "(";
  public static final String NUMBER_SYMBOLS = "0123456789\\.";

  @NotNull private final UUID uuid;

  public Symbol() {
    this.uuid = UUID.randomUUID();
  }

  @Override
  public int hashCode() {
    return uuid.hashCode();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Symbol symbol)) {
      return false;
    }
    return uuid.equals(symbol.uuid);
  }
}
