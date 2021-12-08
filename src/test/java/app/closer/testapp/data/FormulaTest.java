package app.closer.testapp.data;

import app.closer.testapp.dataflow.Formula;
import org.junit.jupiter.api.Test;

class FormulaTest {

  @Test
  void name() {
    Expression expression = Expression.from("(1+1)+(1*1)*5");
    System.out.println(Formula.from(expression.getBody())); // todo
  }
}
