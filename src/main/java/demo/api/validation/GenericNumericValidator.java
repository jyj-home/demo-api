package demo.api.validation;

import demo.api.utils.CheckUtils;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class GenericNumericValidator extends GenericNotNullValidator {

  protected static final String MSG_SEPARATOR = "：";

  protected String message;

  protected boolean positive = false;

  protected boolean negative = false;

  protected int integer = 9;

  protected int fraction = 0;

  protected BigDecimal max;

  protected BigDecimal min;

  protected boolean valid(String value) {

    if (!super.validRequire(value)) {
      this.message = super.message;
      return false;
    }

    if (value == null) {
      return true;
    }

    return this.validNumeric(value) && this.validLength(new BigDecimal(value))
	&& this.validMaxMin(new BigDecimal(value));
  }

  /**
   * @param value
   * @param context
   * @return
   */
  protected boolean validNumeric(String value) {

    if (this.positive ^ this.negative) {
      if (this.positive && this.fraction == 0 && !CheckUtils.isIntegerPositive(value)) {
	return setMessage("E901");
      } else if (this.negative && this.fraction == 0 && !CheckUtils.isIntegerNegative(value)) {
	return setMessage("E902");
      } else if (this.positive && this.fraction > 0 && !CheckUtils.isNumericPositive(value)) {
	return setMessage("E903");
      } else if (this.negative && this.fraction > 0 && !CheckUtils.isNumericNegative(value)) {
	return setMessage("E904");
      }
    } else if (!CheckUtils.isNumericNegative(value)) {
      return setMessage("E905");
    }

    return true;
  }

  /**
   * @param value
   * @param context
   * @return
   */
  protected boolean validLength(BigDecimal value) {

    if (this.fraction == 0) {
      if (!CheckUtils.checkLengthInteger(value, this.integer)) {
	return setMessage("E905");
      }
    } else {
      if (!(CheckUtils.checkLengthInteger(value, this.integer)
	  && CheckUtils.checkLengthFraction(value, this.fraction))) {
	return setMessage("E906");
      }
    }

    return true;
  }

  /**
   * @param value
   * @param context
   * @return
   */
  protected boolean validMaxMin(BigDecimal value) {
    if (this.max != null && this.min != null && (value.compareTo(max) > 0 || value.compareTo(this.min) < 0)) {
      return setMessage("E004", this.min, this.max);
    } else if (this.min != null && value.compareTo(min) < 0) {
      return setMessage("E005", this.min);
    } else if (this.max != null && value.compareTo(max) > 0) {
      return setMessage("E006", this.max);
    }

    return true;
  }

  protected boolean setMessage(String messageId, Object... args) {
    this.message = messageId;
    return false;
  }

  protected boolean setMessage(ConstraintValidatorContext context, String messageId) {

    String msg = "";
    context.disableDefaultConstraintViolation();
    context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
    return false;
  }
}
