package app.closer.testapp.flow.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import app.closer.testapp.data.Formula;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SimpleCalculatorTest {

  private SimpleCalculator calculator;

  @BeforeEach
  void setUp() {
    calculator = new SimpleCalculator();
  }

  @Nested
  class basic_adding {
    static Stream<Arguments> positiveValuesInAdding() {
      return Stream.of(
          arguments("0+0", "0.0"),
          arguments("1+0", "1.0"),
          arguments("1+1", "2.0"),
          arguments("1+1+1", "3.0"),
          arguments("1+5+1", "7.0"),
          arguments("0+1+1+2+3+5+8+13", "33.0"),
          arguments("1+1+2+3+5+8+13", "33.0"),
          arguments("13+1+2+3+5+8+1", "33.0"));
    }

    @ParameterizedTest
    @MethodSource("positiveValuesInAdding")
    void should_add(String formula, String result) {
      assertEquals(calculator.evaluate(Formula.from(formula)).toString(), result);
    }
  }

  @Nested
  class advanced_adding {
    static Stream<Arguments> negativeValuesInAdding() {
      return Stream.of(
          arguments("-1+1", "0.0"),
          arguments("1+-1", "0.0"),
          arguments("-1-1", "-2.0"),
          arguments("-1+-1", "-2.0"),
          arguments("0-1+1-2+-3+-5+8+13", "11.0"));
    }

    @ParameterizedTest
    @MethodSource("negativeValuesInAdding")
    void should_add(String formula, String result) {

      assertEquals(result, calculator.evaluate(Formula.from(formula)).toString());
    }
  }

  @Nested
  class basic_multiplication {
    static Stream<Arguments> basicMultiplication() {
      return Stream.of(
          arguments("0*0", "0.0"),
          arguments("1*0", "0.0"),
          arguments("-1*1", "-1.0"),
          arguments("-1*-1", "1.0"),
          arguments("1*-1", "-1.0"));
    }

    @ParameterizedTest
    @MethodSource("basicMultiplication")
    void should_multiply(String formula, String result) {
      assertEquals(result, calculator.evaluate(Formula.from(formula)).toString());
    }
  }

  @Nested
  class basic_brackets {
    static Stream<Arguments> simpleBracketsUsage() {
      return Stream.of(
          arguments("(2+2)*2", "8.0"),
          arguments("2*(2+2)", "8.0"),
          arguments("0+(2+2)*2+0", "8.0"),
          arguments("2+2*2", "6.0"),
          arguments("2*2+2", "6.0"),
          arguments("1+2*2+1", "6.0"),
          arguments("5*(5*0)+1", "1.0"),
          arguments("1+(5*0)*5", "1.0"),
          arguments("5*0*5+1", "1.0"),
          arguments("1+0*0*5", "1.0"),
          arguments("5+2*(3-2)", "7.0"));
    }

    @ParameterizedTest
    @MethodSource("simpleBracketsUsage")
    void should_consider_brackets(String formula, String result) {
      assertEquals(result, calculator.evaluate(Formula.from(formula)).toString());
    }
  }
}
