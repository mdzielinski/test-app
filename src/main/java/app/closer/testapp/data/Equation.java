package app.closer.testapp.data;

import app.closer.testapp.flow.service.ICalculator;
import app.closer.testapp.util.BeanUtil;
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

  public Equation resolve() {
    ICalculator calculator = BeanUtil.getBean(ICalculator.class);
    this.result = calculator.evaluate(formula);
    return this;
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
