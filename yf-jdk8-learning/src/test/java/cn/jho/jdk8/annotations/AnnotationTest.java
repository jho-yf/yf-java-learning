package cn.jho.jdk8.annotations;

import cn.jho.jdk8.lambda.annotations.MyAnnotation;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-11-08 7:27
 */
public class AnnotationTest {

    @MyAnnotation("Hello")
    @MyAnnotation("World")
    @MyAnnotation("d")
    @MyAnnotation("d")
    public void show(@MyAnnotation("jho") String str) {

    }

    @Test
    public void test() throws NoSuchMethodException {
        Class<AnnotationTest> clazz = AnnotationTest.class;
        Method method = clazz.getMethod("show");

        MyAnnotation[] annotations = method.getAnnotationsByType(MyAnnotation.class);

        for (MyAnnotation annotation : annotations) {
            System.out.println(annotation.value());
        }
    }

}
