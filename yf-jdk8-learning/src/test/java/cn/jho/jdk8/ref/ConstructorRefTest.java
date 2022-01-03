package cn.jho.jdk8.ref;

import cn.jho.jdk8.lambda.model.Employee;
import org.junit.Test;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-09-16 7:47
 */
public class ConstructorRefTest {

    /**
     * 无参构造引用
     */
    @Test
    public void test1() {
        // 一般方法
        Supplier<Employee> sup1 = () -> new Employee();
        System.out.println(sup1.get());

        Supplier<Employee> sup2 = Employee::new;
        Employee employee = sup2.get();
        System.out.println(employee);
    }

    /**
     * 有参构造引用
     */
    @Test
    public void test2() {
        Function<Integer, Employee> func1 = (id) -> new Employee(id);
        System.out.println(func1.apply(10));

        Function<Integer, Employee> func2 = Employee::new;
        System.out.println(func2.apply(100));
    }

    /**
     * 数组引用
     */
    @Test
    public void test3() {
        // 一般方法
        Function<Integer, String[]> func = (x) -> new String[x];
        String[] apply = func.apply(10);
        System.out.println(apply.length);

        // 数组引用
        Function<Integer, String[]> func2 = String[]::new;
        System.out.println(func2.apply(20).length);
    }

}
