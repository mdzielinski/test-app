package app.closer.testapp.data;

import java.util.UUID;
import lombok.Data;

@Data
public class Equation {

  private UUID equationUUID;

  private MathExpression expression;
  private String result;

  public Equation(MathExpression expression, UUID equationUUID) {
    this.equationUUID = equationUUID;
    this.expression = expression;
  }

  public String pretty() {
    return "Equation UUID: " + equationUUID + expression + " = " + result;
  }
}
