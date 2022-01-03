package cn.jho.jdk8.lambda;

import cn.jho.jdk8.lambda.filter.AgeFilter;
import cn.jho.jdk8.lambda.filter.MyPredicate;
import cn.jho.jdk8.lambda.filter.SalaryFilter;
import cn.jho.jdk8.lambda.model.Employee;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-09-11 14:29
 */
public class LambdaTest {

    private List<Employee> employeeList;

    @Before
    public void init() {
        employeeList = Arrays.asList(
                new Employee(1, "张三", 18, 8888),
                new Employee(1, "李四", 38, 3333),
                new Employee(1, "王五", 28, 5555),
                new Employee(1, "赵六", 28, 4444),
                new Employee(1, "孙七", 58, 6666)
        );
    }

    /**
     * 一般匿名内部类
     */
    @Test
    public void testGeneral() {
        Comparator<Integer> comparator = new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        int compare = comparator.compare(1, 2);
        assert compare < 0;
    }

    /**
     * lambda表达式
     */
    @Test
    public void testLambda() {
        Comparator<Integer> comparator = Integer::compare;
        assert comparator.compare(1, 2) < 0;
    }

    /**
     * 普通方法过滤
     */
    @Test
    public void testFilter() {
        System.out.println(filterByAge(employeeList));
        System.out.println(filterBySalary(employeeList));
    }

    /**
     * 策略设计模式过滤
     */
    @Test
    public void testFilterOnStrategy() {
        System.out.println(filterByPredicate(employeeList, new AgeFilter()));
        System.out.println(filterByPredicate(employeeList, new SalaryFilter()));
    }

    /**
     * 使用匿名内部类过滤
     */
    @Test
    public void testFilterOnGeneral() {
        List<Employee> result = filterByPredicate(employeeList, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() > 6000;
            }
        });
        System.out.println(result);
    }

    /**
     * 使用Lambda表达式过滤
     */
    @Test
    public void testFilterOnLambda() {
        filterByPredicate(employeeList, (employee) -> employee.getSalary() < 4000).forEach(System.out::println);
    }

    /**
     * 使用Stream API过滤
     */
    @Test
    public void testFilterOnStream() {
        employeeList.stream()
                .filter((e) -> e.getSalary() >= 4000)       // 过滤
                .limit(2)                                   // 限制条数
                .forEach(System.out::println);
        System.out.println("###########################################");
        employeeList.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }

    /**
     * 排序一组{@link Employee}
     * 先按年龄比，年龄相同按工资比较
     */
    @Test
    public void testSort() {
        Collections.sort(employeeList, (o1, o2) -> {
            int compare = Integer.compare(o1.getAge(), o2.getAge());
            if (compare == 0) {
                compare = Integer.compare(o1.getSalary(), o2.getSalary());
            }
            return compare;
        });
        // Collections.sort(employeeList, Comparator.comparingInt(Employee::getAge).thenComparingInt(Employee::getSalary));
        System.out.println(employeeList);
    }

    public List<Employee> filterByAge(List<Employee> list) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : list) {
            if (employee.getAge() > 30) {
                result.add(employee);
            }
        }
        return result;
    }

    public List<Employee> filterBySalary(List<Employee> list) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : list) {
            if (employee.getSalary() > 5000) {
                result.add(employee);
            }
        }
        return result;
    }

    public List<Employee> filterByPredicate(List<Employee> list, MyPredicate<Employee> predicate) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : list) {
            if (predicate.test(employee)) {
                result.add(employee);
            }
        }
        return result;
    }

}
