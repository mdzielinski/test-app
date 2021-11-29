package app.closer.testapp.data;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public final class Equation {

  @NotNull private final Formula formula;
  private Result result;

  public static Equation from(Formula formula) {
    return new Equation(formula);
  }

  public String pretty() {
    return "Equation UUID: "
        + formula.getUuid()
        + " , formula: "
        + formula
        + " = "
        + (result == null ? "<not obtained yet>" : result.toString());
  }

  private Equation(Formula formula) {
    this.formula = formula;
  }

  public UUID getEquationUUID() {
    return formula.getUuid();
  }

  public Result getResult() {
    return this.result;
  }
}
