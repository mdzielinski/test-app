package app.closer.testapp.flow.service;

import app.closer.testapp.data.Expression;
import app.closer.testapp.data.Result;

public interface ICalculator {
  Result calculate(Expression equation);
}
