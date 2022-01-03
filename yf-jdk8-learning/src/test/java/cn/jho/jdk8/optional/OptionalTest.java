package cn.jho.jdk8.optional;

import cn.jho.jdk8.lambda.model.Employee;
import org.junit.Test;

import java.util.Optional;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-11-05 7:22
 */
public class OptionalTest {

    @Test
    public void testOf() {
        Optional<Employee> opt = Optional.of(new Employee());
        Employee employee = opt.get();
        System.out.println(employee);

        // Optional<Employee> opt = Optional.of(null);      会报空指针异常，能够快速定位空指针异常的位置
    }

    @Test
    public void testEmpty() {
        Optional<Object> opt = Optional.empty();
        opt.ifPresent(System.out::println);
    }

    @Test
    public void testOfNullable() {
        Employee employee = null;
        Optional<Employee> opt = Optional.ofNullable(employee);
        opt.ifPresent(System.out::println);

        Employee emp = opt.orElse(new Employee(1, "乙方", 18, 10000));
        System.out.println(emp);

        Employee emp1 = opt.orElseGet(() -> new Employee(1, "乙方", 18, 10000));
        System.out.println(emp1);
    }

    @Test
    public void testMap() {
        Optional<Employee> opt = Optional.of(new Employee(1, "乙方", 18, 10000));
        Optional<String> name = opt.map(Employee::getName);
        name.ifPresent(System.out::println);

        Optional<String> stringOptional = opt.flatMap(e -> Optional.of(e.getName()));
        stringOptional.ifPresent(System.out::println);
    }


}
