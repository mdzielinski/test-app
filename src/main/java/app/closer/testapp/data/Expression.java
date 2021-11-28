package app.closer.testapp.data;

import java.util.UUID;
import org.springframework.validation.annotation.Validated;

@Validated
public final class Expression {

  private final String body;
  private final UUID expressionUUID;

  private Expression(String body) {
    this.expressionUUID = UUID.randomUUID();
    this.body = body;
  }

  public static Expression from(String expression) {
    return new Expression(expression);
  }

  public String getBody() {
    return body;
  }

  public UUID getExpressionUUID() {
    return expressionUUID;
  }

  @Override
  public String toString() {
    return "\"" + body + "\"";
  }
}
