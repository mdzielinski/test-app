package app.closer.testapp.data;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;

@Validated
public final class Expression {

  private final String body;
  private final UUID uuid;

  public static Expression from(String formula) {
    return new Expression(formula);
  }

  private Expression(String body) {
    this.uuid = UUID.nameUUIDFromBytes(body.getBytes(StandardCharsets.UTF_8));
    this.body = sanitize(body);
  }

  private static String sanitize(String body) {
    return removeURLWhitespaceCodes(body);
  }

  private static String removeURLWhitespaceCodes(String s) {
    return s.replaceAll("%20", "");
  }

  @Override
  public String toString() {
    return "\"" + body + "\"";
  }

  public String getBody() {
    return body;
  }

  public UUID getUuid() {
    return uuid;
  }
}
