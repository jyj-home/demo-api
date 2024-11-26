package demo.api.validation;

import demo.api.ApplicationContextProvider;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.text.ParseException;
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

  private MessageSource messageSource = ApplicationContextProvider.getBean(MessageSource.class);

  @Override
  public void initialize(DateString constraintAnnotation) {

    this.message = constraintAnnotation.message();
    this.require = constraintAnnotation.require();
    this.format = constraintAnnotation.format();
    this.past = constraintAnnotation.past();
    this.future = constraintAnnotation.future();
    this.now = constraintAnnotation.now();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    context.disableDefaultConstraintViolation();

    if (this.require && value == null) {
      return setMessage(context, "E001");
    } else if (value == null) {
      return true;
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat(this.format);
    try {
      dateFormat.parse(value);
    } catch (ParseException e) {
      return setMessage(context, "E101", this.format);
    }

    Date date = new Date();
    if (past && value.compareTo(dateFormat.format(date)) >= 0) {
      return setMessage(context, "E102");
    } else if (future && value.compareTo(dateFormat.format(date)) <= 0) {
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
