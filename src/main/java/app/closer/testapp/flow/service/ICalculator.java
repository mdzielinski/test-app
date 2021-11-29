package app.closer.testapp.flow.service;

import app.closer.testapp.data.Formula;
import app.closer.testapp.data.Result;

public interface ICalculator {
  Result evaluate(Formula equation);
}
