package cn.jho.jdk8.stream;

import cn.jho.jdk8.lambda.model.Employee;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-09-16 23:04
 */
public class StreamAPITest {

    /*
        Stream的三个操作步骤：
            1. 创建Stream
            2. 中间操作
            3. 终止操作（终端操作）
     */

    private List<Employee> employeeList;

    @Before
    public void init() {
        employeeList = Arrays.asList(
                new Employee(1, "张三", 18, 8888),
                new Employee(1, "李四", 38, 3333),
                new Employee(1, "王五", 28, 5555),
                new Employee(1, "赵六", 28, 4444),
                new Employee(1, "孙七", 60, 6666),
                new Employee(1, "孙八", 50, 6666),
                new Employee(1, "孙九", 18, 6666)
        );
    }

    /**
     * 创建Stream
     */
    @Test
    public void testCreate() {
        // 通过Collection系列集合提供的方法：Stream()   parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        // 通过Arrays中的静态方法：stream()
        Employee[] employees = new Employee[10];
        Stream<Employee> stream1 = Arrays.stream(employees);
        int[] arr = new int[10];
        IntStream stream2 = Arrays.stream(arr);
        String[] strings = new String[10];
        Stream<String> stream3 = Arrays.stream(strings);

        // 通过Stream类中的静态方法：of()
        Stream<String> stream4 = Stream.of("a", "b", "c");
        Stream<Employee> stream5 = Stream.of(new Employee(), new Employee(), new Employee());
        Stream<Integer> stream6 = Stream.of(1, 2, 3, 4, 5);

        // 创建无限流 - 迭代
        Stream<Integer> stream7 = Stream.iterate(0, x -> x + 10);
        // stream7.forEach(System.out::println);
        stream7.limit(10).forEach(System.out::println);

        // 创建无限流 - 生成
        Stream<Double> stream8 = Stream.generate(Math::random);
        // stream8.forEach(System.out::println);
        stream8.limit(5).forEach(System.out::println);
    }

    /*
     * 筛选与切片
     *          filter      接收Lambda，从流中排除某些元素
     *          limit       截断流，使其元素不超过给定数量
     *          ship(n)     跳过元素，返回一个舍弃前n个元素的流，若元素个数不足n个，则返回一个空流。与limit(n)互补
     *          distinct    筛选，通过流所生成元素的hashCode()和equals()去除重复元素
     */

    /**
     * filter
     */
    @Test
    public void testFilter() {
        Stream<Employee> stream = employeeList.stream();
        // 中间操作：不会执行任何操作
        stream.filter(e -> {
            System.out.println("中间操作");
            return e.getSalary() > 5000;
        });
        // 终止操作：一次性执行全部内容，“惰性求值”
        // 内部迭代：迭代操作由Stream API完成
        stream.forEach(System.out::println);
    }

    /**
     * 外部迭代
     */
    @Test
    public void testIter() {
        Iterator<Employee> iterator = employeeList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * limit
     */
    @Test
    public void testLimit() {
        employeeList.stream()
                .filter(e -> e.getSalary() > 5000)
                .limit(2)
                .forEach(System.out::println);
    }

    /**
     * skip
     */
    @Test
    public void testSkip() {
        employeeList.stream()
                .filter(e -> e.getSalary() > 5000)
                .skip(2)
                .forEach(System.out::println);

    }

    /**
     * distinct
     */
    @Test
    public void testDistinct() {
        employeeList.stream()
                .distinct()
                .forEach(System.out::println);
    }

    /*
     * 映射
     *      map：接收Lambda，将元素转换成其他形式或提取信息。接收函数作为参数，该函数会被应用到每一个元素，并将其映射成一个新的元素
     *      flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */

    /**
     * map()
     */
    @Test
    public void testMap() {
        Stream.of("aa", "bb", "cc", "dd", "ee")
                .map(String::toUpperCase)
                .forEach(System.out::println);

        employeeList.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }

    /**
     * flatMap()
     */
    @Test
    public void testFlatMap() {
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd", "ee");
        Stream<Stream<Character>> stream = list.stream().map(StreamAPITest::filterCharacter);
        stream.forEach((sm) -> sm.forEach(System.out::println));

        System.out.println("######################");

        list.stream().flatMap(StreamAPITest::filterCharacter).forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (char c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    /*
     * 排序
     *      sorted：自然排序（Comparable）
     *      sorted(Comparable)：定制排序（Comparator）
     */
    @Test
    public void testSorted() {
        List<String> list = Arrays.asList("ccc", "bb", "a", "eeee");

        // 自然排序
        list.stream().sorted().forEach(System.out::println);

        // 定制排序
        list.stream().sorted(Comparator.comparingInt(String::length).reversed()).forEach(System.out::println);
    }

    /*
     * 匹配
     *      allMatch：检查是否匹配所有元素
     *      anyMatch：检查是否至少匹配一个元素
     *      noneMatch：检查是否没有匹配所有元素
     */
    @Test
    public void testMatch() {
        boolean match = true;
        match = employeeList.stream()
                .allMatch((e) -> e.getName().equals("张四"));
        assert !match;

        match = employeeList.stream()
                .anyMatch(e -> e.getName().equals("张三"));
        assert match;

        match = employeeList.stream()
                .noneMatch(e -> e.getSalary() < 1000);
        assert match;
    }

    /*
     * 查找
     *      findFirst：返回第一个元素
     *      findAny：返回当前流中的任意元素
     *      count：返回流中元素的总个数
     *      max：返回流中最大值
     *      min：返回流中最小值
     */
    @Test
    public void testFind() {
        Optional<Employee> opt1 = employeeList.stream().findFirst();
        opt1.ifPresent(System.out::println);

        Optional<Employee> opt2 = employeeList.stream()
                .filter(e -> e.getSalary() > 5000)
                .findAny();
        opt2.ifPresent(System.out::println);

        System.out.println(employeeList.stream().filter(e -> e.getSalary() > 5000).count());

        Optional<Employee> max = employeeList.stream().max(Comparator.comparingInt(Employee::getSalary));
        max.ifPresent(System.out::println);

        Optional<Integer> min = employeeList.stream()
                .map(Employee::getSalary)
                .min(Integer::compare);
        min.ifPresent(System.out::println);
    }

    /*
     * 归约：将流中元素反复结合起来操作得到一个值
     *         reduce(T identity, BinaryOperator<T> accumulator)
     *         reduce(BinaryOperator<T> accumulator);
     */
    @Test
    public void testReduce() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream().reduce(0, Integer::sum);
        assert sum == 55;

        Optional<Integer> opt = employeeList.stream()
                .map(Employee::getSalary).reduce(Integer::sum);
        opt.ifPresent(System.out::println);
    }

    /*
     * 收集：将流转换成为另一种形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
     *          collect(Collector<? super T, A, R> collector)：Collector接口的实现决定了如何对流执行收集操作
     *                                                      （如：List、Set、Map）
     *              Collector接口的实现类提供了很多静态方法，可以方便的创建常见的收集器实例
     */
    @Test
    public void testCollect() {
        List<Integer> list = employeeList.stream()
                .map(Employee::getSalary)
                .collect(Collectors.toList());
        System.out.println(list);

        Set<String> set = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        System.out.println(set);

        HashSet<String> hs = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        System.out.println(hs);

        // 总数
        Long count = employeeList.stream()
                .collect(Collectors.counting());
        System.out.println(count);

        // 平均值
        Double avg = employeeList.stream()
                .collect(Collectors.averagingInt(Employee::getSalary));
        System.out.println(avg);

        // 总和
        Integer sum = employeeList.stream()
                .collect(Collectors.summingInt(Employee::getSalary));
        System.out.println(sum);

        // 最大值
        Optional<Employee> max = employeeList.stream()
                .collect(Collectors.maxBy((e1, e2) -> Integer.compare(e1.getSalary(), e2.getSalary())));
        max.ifPresent(System.out::println);

        // 最小值
        Optional<Integer> min = employeeList.stream()
                .map(Employee::getSalary).min(Double::compare);
        min.ifPresent(System.out::println);

        IntSummaryStatistics summaryStatistics = employeeList.stream()
                .collect(Collectors.summarizingInt(Employee::getSalary));
        System.out.println(summaryStatistics.getSum());
        System.out.println(summaryStatistics.getAverage());
        System.out.println(summaryStatistics.getMax());
    }

    /*
     * 分组
     */
    @Test
    public void testGroup() {
        Map<Integer, List<Employee>> map = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getSalary));
        map.forEach((k, v) -> {
            System.out.println("k=" + k + ",v=" + v);
        });

        // 多级分组
        Map<Integer, Map<String, List<Employee>>> collect = employeeList.stream().collect(Collectors.groupingBy(Employee::getSalary,
                Collectors.groupingBy((e) -> {
                    if (e.getAge() <= 30) {
                        return "青年";
                    } else if (e.getAge() <= 55) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));
        System.out.println(collect);
    }

    /*
     * 分区
     */
    @Test
    public void testPartitioning() {
        Map<Boolean, List<Employee>> collect = employeeList.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() > 5000));
        System.out.println(collect);
    }

    /*
     * 连接
     */
    @Test
    public void testJoining() {
        String collect1 = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.joining());
        System.out.println(collect1);

        String collect2 = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(","));
        System.out.println(collect2);

        String collect3 = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(",", "[", "]"));
        System.out.println(collect3);
    }


}
