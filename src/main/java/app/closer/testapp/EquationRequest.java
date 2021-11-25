package app.closer.testapp;

import java.util.UUID;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class EquationRequest {
  private UUID uuid;
  private String equation;
  private String result;

  public String pretty() {
    return "\n" + "Request Id (internal): " + uuid + "\n" + equation + " = " + result + "\n";
  }
}
