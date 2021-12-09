package app.closer.testapp.data.symbol;

import static app.closer.testapp.data.symbol.SymbolFactory.symbolFrom;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SymbolTest {
  @Nested
  class parsing_brackets {
    static Stream<Arguments> brackets() {
      return Stream.of(arguments("("), arguments(")"));
    }

    @ParameterizedTest
    @MethodSource("brackets")
    void operator(String symbol) {
      assertTrue(symbolFrom(symbol, 0, 0) instanceof Bracket);
    }
  }

  @Nested
  class parsing_operators {
    static Stream<Arguments> operators() {
      return Stream.of(arguments("+"), arguments("-"), arguments("*"), arguments("/"));
    }

    @ParameterizedTest
    @MethodSource("operators")
    void operator(String symbol) {
      assertTrue(symbolFrom(symbol, 0, 0) instanceof Operator);
    }
  }

  @Nested
  class parsing_numbers {
    static Stream<Arguments> operators() {
      return Stream.of(
          arguments("0.0"),
          arguments("123456.7890"),
          arguments("1234567890.1234567890"),
          arguments(".12345678901234567890"),
          arguments(".0"));
    }

    @ParameterizedTest
    @MethodSource("operators")
    void operator(String symbol) {
      assertTrue(symbolFrom(symbol, 0, 0) instanceof Number);
    }
  }

  @Nested
  class throwing_exception_while_parsing_numbers {
    static Stream<Arguments> operators() {
      return Stream.of(
          arguments("00.0."),
          arguments(".0.0"),
          arguments(".00."),
          arguments("0..0"),
          arguments("1234567890." + ".1234567890"));
    }

    @ParameterizedTest
    @MethodSource("operators")
    void operator(String symbol) {
      assertThrows(NumberFormatException.class, () -> symbolFrom(symbol, 0, 0));
    }
  }
}
