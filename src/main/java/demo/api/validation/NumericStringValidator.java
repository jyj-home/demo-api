package demo.api.validation;

import demo.api.utils.TypeConvUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/** */
public class NumericStringValidator extends GenericNumericValidator implements ConstraintValidator<NumericString, String> {

  @Override
  public void initialize(NumericString constraintAnnotation) {

    this.require = constraintAnnotation.require();
    this.positive = constraintAnnotation.positive();
    this.negative = constraintAnnotation.negative();
    this.integer = Math.abs(constraintAnnotation.integer());
    this.fraction = Math.abs(constraintAnnotation.fraction());
    this.max = TypeConvUtils.createBigDecimal(constraintAnnotation.max());
    this.min = TypeConvUtils.createBigDecimal(constraintAnnotation.min());
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return super.valid(value);
  }
}
