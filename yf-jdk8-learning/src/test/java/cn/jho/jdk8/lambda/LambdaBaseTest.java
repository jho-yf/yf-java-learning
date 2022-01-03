package cn.jho.jdk8.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-09-11 15:29
 */
public class LambdaBaseTest {

    /**
     * 无参数列表，无返回值
     */
    @Test
    public void test1() {
        // final int i = 0;
        // 在jdk1.7之前，必须加上final，在jkd1.8之后，自动加final
        int i = 0;

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("普通匿名内部类" + i);
            }
        };
        r1.run();

        System.out.println("######################################");

        // Runnable r2 = () -> System.out.println("Lambda表达式" + i++);   编译异常，因为i是final
        Runnable r2 = () -> System.out.println("Lambda表达式" + i);
        r2.run();
    }

    /**
     * 有一个参数，无返回值
     * 若只有一个参数，小括号可以省略不写
     */
    @Test
    public void test2() {
        Consumer<String> c1 = (x) -> System.out.println(x);
        c1.accept("Hello Lambda");

        Consumer<Integer> c2 = x -> System.out.println(x);
        c2.accept(666);
    }

    /**
     * 多个参数，有返回值，Lambda体中有多条语句
     */
    @Test
    public void test3() {
        Comparator<Integer> com = (x, y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x, y);
        };
    }

    /**
     * 类型推断
     * Lambda表达式的参数列表的数据类型可以省略不写，因为JVM编译器可以通过上下文进行类型推断
     */
    @Test
    public void test4() {
        // Comparator<Integer> com = (Integer x, Integer y) -> Integer.compare(x, y);
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
    }

    @Test
    public void test5() {
        Comparator<Integer> com = Integer::compare;
    }

}
