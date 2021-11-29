package app.closer.testapp.data;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.validation.annotation.Validated;

@Configurable
@Validated
public final class Equation {

  @NotNull private final Formula formula;
  private Result result;

  private Equation(Formula formula) {
    this.formula = formula;
  }

  public UUID getEquationUUID() {
    return formula.getUuid();
  }

  public String pretty() {
    return "Equation UUID: "
        + formula.getUuid()
        + " , formula: "
        + formula
        + " = "
        + (result == null ? "<not obtained yet>" : result.toString());
  }
}
