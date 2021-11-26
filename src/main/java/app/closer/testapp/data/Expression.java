package app.closer.testapp.data;

import java.util.UUID;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class Expression {

  private final String body;
  private final UUID expressionUUID;

  public Expression(String body) {
    this.expressionUUID = UUID.randomUUID();
    this.body = body;
  }

  @Override
  public String toString() {
    return "\"" + body + "\"";
  }
}
