package app.closer.testapp.data.symbol;

import static app.closer.testapp.data.symbol.SymbolFactory.symbolFrom;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SymbolTest {
  @Nested
  class operator {
    static Stream<Arguments> operators() {
      return Stream.of(arguments("+"), arguments("-"), arguments("*"), arguments("/"));
    }

    @ParameterizedTest
    @MethodSource("operators")
    void should_add(String symbol) {
      assertEquals(symbol, symbolFrom(symbol, 0).toString());
    }
  }

  @Nested
  class bracket {
    static Stream<Arguments> brackets() {
      return Stream.of(arguments("("), arguments(")"));
    }

    @ParameterizedTest
    @MethodSource("brackets")
    void should_add(String symbol) {
      assertEquals(symbol, symbolFrom(symbol, 0).toString());
    }
  }
}
