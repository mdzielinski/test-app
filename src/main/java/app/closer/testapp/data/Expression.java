package app.closer.testapp.data;

import app.closer.testapp.data.validation.BasicExpression;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Expression {

  @BasicExpression String body;

  @Override
  public String toString() {
    return "\"" + body + "\"";
  }
}
