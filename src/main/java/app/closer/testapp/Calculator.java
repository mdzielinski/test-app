package app.closer.testapp;

import org.springframework.stereotype.Service;

@Service
public class Calculator implements ICalculator {

  public String calculate(String equation){
    return evaluate(equation);

  }

  private String evaluate(String equation) {
    return null;
  }
}
