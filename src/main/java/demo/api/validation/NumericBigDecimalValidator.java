package demo.api.validation;

import demo.api.utils.TypeConvUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

/** */
public class NumericBigDecimalValidator extends GenericNumericValidator
    implements ConstraintValidator<NumericBigDecimal, BigDecimal> {

  @Override
  public void initialize(NumericBigDecimal constraintAnnotation) {

    this.require = constraintAnnotation.require();
    this.positive = constraintAnnotation.positive();
    this.negative = constraintAnnotation.negative();
    this.integer = Math.abs(constraintAnnotation.integer());
    this.fraction = Math.abs(constraintAnnotation.fraction());
    this.max = TypeConvUtils.createBigDecimal(constraintAnnotation.max());
    this.min = TypeConvUtils.createBigDecimal(constraintAnnotation.min());
  }

  @Override
  public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
    return super.valid(value == null ? null : value.toString());
  }
}
