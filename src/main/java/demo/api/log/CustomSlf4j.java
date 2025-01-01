package demo.api.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // 只能标注在类上
@Retention(RetentionPolicy.SOURCE) // 只在编译期有效
public @interface CustomSlf4j {
}
