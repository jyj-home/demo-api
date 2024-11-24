package demo.api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/** */
public class IntegerStringValidator implements ConstraintValidator<IntegerString, String> {

  private String code;

  @Override
  public void initialize(IntegerString constraintAnnotation) {
    // 这里目前不需要初始化额外的参数，如果有自定义属性在注解中，可以在这里获取并初始化
    //    this.code = constraintAnnotation.code();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true; // 如果值为null，视为验证通过，可根据实际需求调整
    }
    try {
      Integer.parseInt(value);
      return true;
    } catch (NumberFormatException e) {
      //      context.disableDefaultConstraintViolation();
      //
      // context.buildConstraintViolationWithTemplate("输入的字符串不是合法的整数格式，错误码：").addConstraintViolation();
      return false;
    }
  }
}
