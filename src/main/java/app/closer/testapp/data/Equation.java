package app.closer.testapp.data;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;

@Data
@SuperBuilder
@Validated
public class Equation {

  @NotNull private Expression expression;
  private String result;

  public Equation(Expression expression) {
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
        + result;
  }
}
