package demo.api.validation;

import demo.api.ApplicationContextProvider;
import demo.api.utils.CheckUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

/** */
public class DateStringValidator implements ConstraintValidator<DateString, String> {

  private static final String MSG_SEPARATOR = "：";

  private String message;

  private boolean require;

  private String format;

  private boolean past;

  private boolean future;

  private boolean now;

  private boolean inclusive;

  private MessageSource messageSource = ApplicationContextProvider.getBean(MessageSource.class);

  @Override
  public void initialize(DateString constraintAnnotation) {

    this.message = constraintAnnotation.message();
    this.require = constraintAnnotation.require();
    this.format = constraintAnnotation.format();
    this.past = constraintAnnotation.past();
    this.future = constraintAnnotation.future();
    this.now = constraintAnnotation.now();
    this.inclusive = constraintAnnotation.inclusive();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    context.disableDefaultConstraintViolation();

    if (this.require && value == null) {
      return setMessage(context, "E001");
    } else if (value == null) {
      return true;
    }

    if (!CheckUtils.isDate(value, this.format)) {
      return setMessage(context, "E101");
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat(this.format);
    Date date = new Date();
    if (past && ((inclusive && value.compareTo(dateFormat.format(date)) > 0)
	|| (!inclusive && value.compareTo(dateFormat.format(date)) >= 0))) {
      return setMessage(context, "E102");
    } else if (future && ((inclusive && value.compareTo(dateFormat.format(date)) < 0)
	|| (!inclusive && value.compareTo(dateFormat.format(date)) <= 0))) {
      return setMessage(context, "E103");
    } else if (now && value.compareTo(dateFormat.format(date)) != 0) {
      return setMessage(context, "E104");
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
