package app.closer.testapp.data;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public final class Equation {

  @NotNull private final Expression expression;
  private Result result;

  private Equation(Expression expression) {
    this.expression = expression;
  }

  public UUID getEquationUUID() {
    return expression.getExpressionUUID();
  }

  public String pretty() {
    return "Equation UUID: "
        + expression.getExpressionUUID()
        + " , expression: "
        + expression
        + " = "
        + (result == null ? "<not obtained yet>" : result.toString());
  }
}
