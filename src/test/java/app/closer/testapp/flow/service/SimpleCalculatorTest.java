package app.closer.testapp.flow.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import app.closer.testapp.data.Expression;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SimpleCalculatorTest {

  private SimpleCalculator calculator;

  static Stream<Arguments> PositiveAndNegativeValues() {
    return Stream.of(
        arguments("0+0", "0"),
        arguments("-1+1", "0"),
        arguments("1+-1", "0"),
        arguments("1+1", "2"));
  }

  static Stream<Arguments> parenthesisUsage() {
    return Stream.of(
        arguments("0*0", "0"),
        arguments("-1*1", "-1"),
        arguments("1*-1", "-1"),
        arguments("1+1", "2"));
  }

  @BeforeEach
  void setUp() {
    this.calculator = new SimpleCalculator();
  }

  @ParameterizedTest
  @MethodSource("PositiveAndNegativeValues")
  void should_add_positive_and_negative_values(String expression, String result) {

    assertEquals(calculator.calculate(new Expression(expression)).toString(), result);
  }

  @ParameterizedTest
  @MethodSource("PositiveAndNegativeValues")
  void should_multiply(String expression, String result) {

    assertEquals(calculator.calculate(new Expression(expression)).toString(), result);
  }
}
