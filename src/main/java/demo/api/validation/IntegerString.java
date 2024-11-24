package demo.api.validation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/** */
@Documented
@Constraint(validatedBy = IntegerStringValidator.class)
@Target({FIELD})
@Retention(RUNTIME)
public @interface IntegerString {
  String message() default "validation.IntegerString：{validation.IntegerString}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  //  String code() default "validation.IntegerStringxxxxxx"; // 添加错误码属性
}
