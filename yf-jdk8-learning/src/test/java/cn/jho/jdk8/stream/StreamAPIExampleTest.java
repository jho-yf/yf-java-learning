package cn.jho.jdk8.stream;

import cn.jho.jdk8.lambda.model.Employee;
import cn.jho.jdk8.lambda.model.Person;
import cn.jho.jdk8.lambda.model.Trader;
import cn.jho.jdk8.lambda.model.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-11-01 23:08
 */
public class StreamAPIExampleTest {

    private List<Employee> employeeList;

    private List<Transaction> transactions;

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

        Trader lisi = new Trader("李四", "深圳");
        Trader zhaoliu = new Trader("赵六", "上海");
        Trader zhangsan = new Trader("张三", "广州");
        Trader wangwu = new Trader("王五", "广州");
        transactions = Arrays.asList(
                new Transaction(zhangsan, 2021, 300),
                new Transaction(lisi, 2022, 1000),
                new Transaction(lisi, 2021, 400),
                new Transaction(wangwu, 2022, 710),
                new Transaction(wangwu, 2022, 700),
                new Transaction(zhaoliu, 2022, 950)
        );
    }

    /*
     * 给定数字列表，返回每个数的平方列表
     * 如：
     *      [1, 2, 3, 4]
     *      [1, 4, 8, 16]
     */
    @Test
    public void test1() {
        Integer[] nums = new Integer[] {1, 2, 3, 4, 5};
        List<Integer> list = Arrays.stream(nums)
                .map(x -> x * x)
                .collect(Collectors.toList());
        System.out.println(list);
    }

    /*
     * 计算Employee个数
     */
    @Test
    public void test2() {
        Optional<Integer> optional = employeeList.stream()
                .map((e) -> 1)
                .reduce(Integer::sum);
        optional.ifPresent(System.out::println);
    }

    /*
     * 找出2021发送的所有交易，并按照交易额排序（从低到高）
     */
    @Test
    public void test3() {
        transactions.stream()
                .filter(t -> t.getYear() == 2021)
                .sorted(Comparator.comparingInt(Transaction::getYear) )
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    /*
     * 交易员都在哪些不同的城市工作
     */
    @Test
    public void test4() {
        transactions.stream()
                .map(t -> t.getTrader().getCity())
                .collect(Collectors.toSet())
                .forEach(System.out::println);

        System.out.println("+++++++++++++++++++++");

        transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);
    }

    /*
     * 查找所有来自广州的交易员，并按姓名排序
     */
    @Test
    public void test5() {
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> "广州".equals(trader.getCity()))
                .sorted(Comparator.comparing(Trader::getName))
                .distinct()
                .forEach(System.out::println);
    }

    /*
     * 返回所有交易员姓名，并按字母顺序排
     */
    @Test
    public void test6() {
        String joining = transactions.stream()
                .map(Transaction::getTrader)
                .sorted(Comparator.comparing(Trader::getName))
                .map(Trader::getName)
                .collect(Collectors.joining());
        System.out.println(joining);

        String reduce = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .sorted()
                .reduce("", String::concat);
        System.out.println(reduce);

        transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .flatMap(StreamAPIExampleTest::filterCharacter)
                .sorted(String::compareToIgnoreCase)
                .forEach(System.out::println);
    }

    public static Stream<String> filterCharacter(String str) {
        List<String> list = new ArrayList<>();

        for (Character ch : str.toCharArray()) {
            list.add(ch.toString());
        }

        return list.stream();
    }

    /*
     * 有没有交易员在上海工作
     */
    @Test
    public void test7() {
        boolean match = transactions
                .stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("上海"));
        assert match;
    }

    /*
     * 打印所有生活在广州的交易员的总交易额
     */
    @Test
    public void test8() {
        Optional<Integer> reduce = transactions.stream()
                .filter(transaction -> "广州".equals(transaction.getTrader().getCity()))
                .map(Transaction::getValue)
                .reduce(Integer::sum);
        reduce.ifPresent(System.out::println);

        int sum = transactions.stream()
                .filter(transaction -> "北京".equals(transaction.getTrader().getCity()))
                .mapToInt(Transaction::getValue)
                .sum();
        System.out.println(sum);
    }

    /*
     * 所有交易中最高的交易额
     */
    @Test
    public void test9() {
        OptionalInt max = transactions.stream()
                .mapToInt(Transaction::getValue)
                .max();
        max.ifPresent(System.out::println);

        Optional<Integer> opt = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compareTo);
        opt.ifPresent(System.out::println);
    }

    /*
     * 所有交易中交易额最低的
     */
    @Test
    public void test10() {
        Optional<Transaction> min = transactions.stream()
                .min(Comparator.comparingInt(Transaction::getValue));
        min.ifPresent(System.out::println);
    }

    @Test
    public void test11() {
        Person person1 = new Person("Tom", "1");
        Person person2 = new Person("Liyy", "2");
        Person person3 = new Person("Chad", "3");
        Person person4 = new Person("jho", "3");
        List<Person> personList = Arrays.asList(person1, person2, person3, person4);

        Map<String,String> map = new HashMap<>();
        map.put("Tom","A");
        map.put("Liyy","B");
        map.put("Chad","C");
        map.put("jho","D");

        Map<String, List<String>> collect = personList.stream()
                .filter(p -> map.containsKey(p.getName()))
                .collect(Collectors.toMap(Person::getAge, p -> new ArrayList<String>() {{
                    add(map.get(p.getName()));
                }}, (v1, v2) -> Stream.of(v1, v2).flatMap(Collection::stream).collect(Collectors.toList())));
        System.out.println(collect);
    }

}
