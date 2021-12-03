package app.closer.testapp.data;

import app.closer.testapp.dataflow.Expression;
import org.junit.jupiter.api.Test;

class ExpressionTest {

  @Test
  void name() {
    Formula formula = Formula.from("(1+1)+(1*1)*5");
    System.out.println(Expression.from(formula.getBody())); // todo
  }
}
