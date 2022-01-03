package cn.jho.jdk8.ref;

import cn.jho.jdk8.lambda.model.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-09-16 7:17
 */
public class MethodRefTest {

    /**
     * 对象::实例方法名
     */
    @Test
    public void test1() {
        // 一般方法
        Consumer<String> consumer1 = str -> System.out.println(str);

        // 分布方法引用
        PrintStream printStream = System.out;
        Consumer<String> consumer2 = printStream::println;

        // 方法引用
        Consumer<String> consumer3 = System.out::println;

        consumer1.accept("Hello");
        consumer2.accept("Lambda");
        consumer3.accept("!");

        Employee employee = new Employee(1, "张三", 18, 8888);
        Supplier<String> supplier1 = () -> employee.getName();
        String name = supplier1.get();
        System.out.println(name);

        Supplier<Integer> supplier2 = employee::getAge;
        System.out.println(supplier2.get());
    }

    /**
     * 类::静态方法名
     */
    @Test
    public void test2() {
        // 使用Lambda体
        Comparator<Integer> comparator1 = (x, y) -> Integer.compare(x, y);

        // 类::静态方法名
        Comparator<Integer> comparator2 = Integer::compare;
    }


    /**
     * 类::实例方法名;
     *
     * Lambda表达式的第一个参数是实例方法的调用者，第二个参数是实例方法的参数，可以使用
     */
    @Test
    public void test3() {
        // 一般方法
        BiPredicate<String, String> predicate1 = (str1, str2) -> str1.equals(str2);
        // Lambda
        BiPredicate<String, String> predicate2 = String::equals;
    }


}
