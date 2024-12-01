package demo.api.validation;

import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

@Getter
public class GenericNotNullValidator {

  protected String message;

  protected boolean require = false;

  protected boolean validRequire(Object value) {
    if (this.require && ObjectUtils.isEmpty(value)) {
      return setMessage("E001");
    }

    return true;
  }

  private boolean setMessage(String messageId, Object... args) {

    message = messageId;
    return false;
  }
}
