package demo.api.validation;

import demo.api.ApplicationContextProvider;
import demo.api.utils.CheckUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

/** */
public class PatternStringValidator extends GenericNotNullValidator
    implements ConstraintValidator<PatternString, String> {

  private static final String MSG_SEPARATOR = "：";

  private String message;

  private String regex;

  private MessageSource messageSource = ApplicationContextProvider.getBean(MessageSource.class);

  @Override
  public void initialize(PatternString constraintAnnotation) {

    this.message = constraintAnnotation.message();
    this.require = constraintAnnotation.require();
    this.regex = constraintAnnotation.regex();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    context.disableDefaultConstraintViolation();

    if (super.validRequire(value)) {
      this.message = super.message;
      return false;
    }

    if (value == null) {
      return true;
    }

    if (!CheckUtils.matche(regex, value)) {
      return setMessage(context, "E002");
    }

    return true;
  }

  private boolean setMessage(ConstraintValidatorContext context, String messageId, Object... args) {

    String msg = "";
    try {
      msg = messageSource.getMessage(messageId, args, Locale.getDefault());
    } catch (NoSuchMessageException e) {
      // 処理なし
    }
    if ("".equals(msg)) {
      msg = this.message;
    } else {
      msg = messageId + MSG_SEPARATOR + msg;
    }
    context.buildConstraintViolationWithTemplate(msg);
    return false;
  }
}
