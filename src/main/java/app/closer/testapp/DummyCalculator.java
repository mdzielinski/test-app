package app.closer.testapp;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dummy")
public class DummyCalculator implements ICalculator {

  public String calculate(String equation) {
    String result;
    switch (equation) {
      case "(2+2)*2":
        result = "8";
      case "2+2*2":
        result = "6";
      case "(2+2*2)":
        result = "6";
      default:
        result = "validation error";
    }
    return result;
  }
}
