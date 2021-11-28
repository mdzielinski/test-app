package app.closer.testapp.flow.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import app.closer.testapp.data.Expression;
import app.closer.testapp.data.Result;
import app.closer.testapp.flow.service.ICalculator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class EquationControllerTest {

  private ICalculator calculator;
  private EquationController controller;
  private MockMvc mockMvc;

  public EquationControllerTest() {
    calculator = Mockito.mock(ICalculator.class);
    controller = new EquationController(calculator);
  }

  @BeforeEach
  public void setUp() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  public void should_pass_expression_in_path_and_return_result() throws Exception {
    var expression = "0+1-2*3/(45,6789)";
    var result = Result.of(0.86864832559);

    ArgumentCaptor<Expression> expressionArgumentCaptor = ArgumentCaptor.forClass(Expression.class);
    when(calculator.calculate(expressionArgumentCaptor.capture())).thenReturn(result);

    var mvcResult =
        mockMvc
            .perform(get("/evaluate/" + expression))
            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
            .andReturn();

    assertEquals(mvcResult.getResponse().getContentAsString(), result.toString());
    assertEquals(expressionArgumentCaptor.getValue().getBody(), expression);
  }

  @Test
  public void should_pass_expression_in_body_and_return_result() throws Exception {
    var expression = "0+1-2*3/(45,6789)";
    var result = Result.of(0.86864832559);

    ArgumentCaptor<Expression> expressionArgumentCaptor = ArgumentCaptor.forClass(Expression.class);
    when(calculator.calculate(expressionArgumentCaptor.capture())).thenReturn(result);

    var mvcResult =
        mockMvc
            .perform(
                post("/evaluate")
                    .content(new ObjectMapper().writeValueAsString(expression))
                    .contentType(APPLICATION_JSON_VALUE))
            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
            .andReturn();

    assertEquals(mvcResult.getResponse().getContentAsString(), result.toString());
    assertEquals(expressionArgumentCaptor.getValue().getBody(), expression);
  }
}
