package app.closer.testapp.data;

import app.closer.testapp.data.validation.BasicMathExpression;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MathExpression {

  @BasicMathExpression String body;

  @Override
  public String toString() {
    return "\"" + body + "\"";
  }
}
