package demo.api.validation;

import demo.api.utils.TypeConvUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/** */
public class NumericShortValidator extends GenericNumericValidator implements ConstraintValidator<NumericShort, Short> {

  @Override
  public void initialize(NumericShort constraintAnnotation) {

    this.require = constraintAnnotation.require();
    this.positive = constraintAnnotation.positive();
    this.negative = constraintAnnotation.negative();
    this.integer = Math.abs(constraintAnnotation.integer());
    this.fraction = 0;
    this.max = TypeConvUtils.createBigDecimal(constraintAnnotation.max());
    this.min = TypeConvUtils.createBigDecimal(constraintAnnotation.min());
  }

  @Override
  public boolean isValid(Short value, ConstraintValidatorContext context) {
    return super.valid(value == null ? null : value.toString());
  }
}
