package demo.api.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/** */
@Documented
@Constraint(validatedBy = PatternStringValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface PatternString {
  /**
   * @return
   */
  String message() default "メッセージが見つかりません。";

  boolean require() default false;

  String regex() default ".*";

  /**
   * @return
   */
  Class<?>[] groups() default {
      // 処理なし
  };

  /**
   * @return
   */
  Class<? extends Payload>[] payload() default {
      // 処理なし
  };
}
