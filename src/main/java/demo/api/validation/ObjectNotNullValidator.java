package demo.api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.Getter;

@Getter
public class ObjectNotNullValidator extends GenericNotNullValidator
    implements ConstraintValidator<ObjectNotNull, Object> {

  @Override
  public void initialize(ObjectNotNull constraintAnnotation) {
    this.message = constraintAnnotation.message();
    this.require = true;
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    return validRequire(value);
  }
}
