package cn.jho.jdk8.lambda.annotations;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * 定义可重复注解
 *
 * @author JHO xu-jihong@qq.com
 * @date 2021-11-08 7:27
 */
@Repeatable(MyAnnotations.class)        // 指定容器类
// TYPE_PARAMETER 泛型注解
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    String value() default "myAnnotation";

}
