package demo.api.validation;

import demo.api.ApplicationContextProvider;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.regex.Pattern;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

/** */
public class DigitStringValidator implements ConstraintValidator<DigitString, String> {

  private static final String REGEX_INTEGER = "^%s\\d{%s}$";
  private static final String REGEX_FLOAT = "^%s%s\\d{%s}(\\.\\d{%s})?$";
  private static final String REGEX_POSITIVE = "[+]?";
  private static final String REGEX_NEGATIVE = "[-]";
  private static final String MSG_POSITIVE = "正";
  private static final String MSG_NEGATIVE = "負";
  private static final String MSG_SEPARATOR = "：";

  private String message;

  private boolean require;

  private boolean negative;

  private int integer;

  private int fraction;

  private BigDecimal max;

  private BigDecimal min;

  private MessageSource messageSource = ApplicationContextProvider.getBean(MessageSource.class);

  @Override
  public void initialize(DigitString constraintAnnotation) {

    this.message = constraintAnnotation.message();
    this.require = constraintAnnotation.require();
    this.negative = constraintAnnotation.require();
    this.integer = Math.abs(constraintAnnotation.integer());
    this.fraction = Math.abs(constraintAnnotation.fraction());
    this.max = constraintAnnotation.max() == null || "".equals(constraintAnnotation.max()) ? null
	: new BigDecimal(constraintAnnotation.max());
    this.min = constraintAnnotation.min() == null || "".equals(constraintAnnotation.min()) ? null
	: new BigDecimal(constraintAnnotation.min());
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    context.disableDefaultConstraintViolation();

    if (this.require && value == null) {
      return false;
    } else if (value == null) {
      return true;
    }

    if (!validDigit(value, context)) {
      return false;
    }

    if (!validMaxMin(value, context)) {
      return false;
    }

    return true;
  }

  /**
   * @param value
   * @param context
   * @return
   */
  private boolean validDigit(String value, ConstraintValidatorContext context) {

    if (this.fraction == 0 && !this.negative
	&& !Pattern.matches(REGEX_INTEGER.formatted(REGEX_POSITIVE, this.integer), value)) {
      return setMessage(context, "E002", this.integer, MSG_POSITIVE);
    } else if (this.fraction == 0 && this.negative
	&& !Pattern.matches(REGEX_INTEGER.formatted(REGEX_NEGATIVE, this.integer), value)) {
      return setMessage(context, "E002", this.integer, MSG_NEGATIVE);
    } else if (this.fraction > 0 && !this.negative
	&& !Pattern.matches(REGEX_FLOAT.formatted(REGEX_POSITIVE, this.integer, this.fraction), value)) {
      return setMessage(context, "E003", this.integer, this.fraction, MSG_POSITIVE);
    } else if (this.fraction > 0 && this.negative
	&& !Pattern.matches(REGEX_FLOAT.formatted(REGEX_NEGATIVE, this.integer, this.fraction), value)) {
      return setMessage(context, "E003", this.integer, this.fraction, MSG_NEGATIVE);
    }

    return true;
  }

  /**
   * @param value
   * @param context
   * @return
   */
  private boolean validMaxMin(String value, ConstraintValidatorContext context) {
    BigDecimal bigDecimal = new BigDecimal(value);
    if (this.max != null && this.min != null && (bigDecimal.compareTo(max) > 0 || bigDecimal.compareTo(this.min) < 0)) {
      return setMessage(context, "E004", this.min, this.max);
    } else if (this.min != null && bigDecimal.compareTo(min) < 0) {
      return setMessage(context, "E005", this.min);
    } else if (this.max != null && bigDecimal.compareTo(max) > 0) {
      return setMessage(context, "E006", this.max);
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
