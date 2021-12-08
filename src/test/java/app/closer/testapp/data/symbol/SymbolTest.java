package app.closer.testapp.data.symbol;

import static app.closer.testapp.data.symbol.SymbolFactory.symbolFrom;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import app.closer.testapp.dataflow.ExpressionParsingException;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SymbolTest {
  @Nested
  class parsing {
    static Stream<Arguments> operators() {
      return Stream.of(arguments("+"), arguments("-"), arguments("*"), arguments("/"));
    }

    @ParameterizedTest
    @MethodSource("operators")
    void operator(String symbol) {
      assertEquals(symbol, symbolFrom(symbol, 0, 0).toString());
    }
  }

  @Nested
  class not_parsing {
    static Stream<Arguments> neitherNumbersNorOperators() {
      return Stream.of(
          arguments("["),
          arguments("&"),
          arguments("="),
          arguments("*&"),
          arguments("&*"),
          arguments("%"),
          arguments("#"),
          arguments("@"),
          arguments("!"),
          arguments(" "),
          arguments("^"));
    }

    @ParameterizedTest
    @MethodSource("neitherNumbersNorOperators")
    void should_throw_when_not_parsed(String symbol) {
      assertThrows(ExpressionParsingException.class, () -> symbolFrom(symbol, 0, 0));
    }
  }
}
