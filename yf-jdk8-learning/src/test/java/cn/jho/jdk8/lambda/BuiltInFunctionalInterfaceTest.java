package cn.jho.jdk8.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-09-15 22:51
 */
public class BuiltInFunctionalInterfaceTest {

    /**
     * 消费型接口 Consumer<T> consumer
     */
    @Test
    public void testConsumer() {
        consumer(100, x -> System.out.println("consume " + x));
    }

    public void consumer(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    /**
     * 供给型接口 Supplier<T> supplier
     */
    @Test
    public void testSupplier() {
        List<Integer> list = supplier(5, () -> (int) (Math.random() * 100));
        System.out.println(list);
    }

    public List<Integer> supplier(int count, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(supplier.get());
        }
        return list;
    }

    /**
     * 函数型接口 Function<T, R> function
     */
    @Test
    public void testFunction() {
        Integer result = function("1", str -> Integer.parseInt(str) + 1);
        System.out.println(result);
    }

    public Integer function(String str, Function<String, Integer> func) {
        return func.apply(str);
    }

    @Test
    public void testPredicate() {
        boolean b = predicate("hello world", str -> str.length() < 10);
        System.out.println(b);
    }

    public boolean predicate(String str, Predicate<String> predicate) {
        return predicate.test(str);
    }

    @Test
    public void testFilterString() {
        List<String> list = Arrays.asList("Hello", "Lambda", "I", "am", "java");
        List<String> result = filterString(list, str -> str.length() < 3);
        System.out.println(result);
    }

    public List<String> filterString(List<String> list, Predicate<String> predicate) {
        List<String> result = new ArrayList<>();
        for (String s : list) {
            if (predicate.test(s)) {
                result.add(s);
            }
        }
        return result;
    }

}
