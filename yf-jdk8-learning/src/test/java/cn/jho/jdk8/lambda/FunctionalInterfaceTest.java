package cn.jho.jdk8.lambda;

import org.junit.Test;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-09-13 23:23
 */
public class FunctionalInterfaceTest {

    /**
     * 对一个数进行运算
     */
    @Test
    public void test1() {
        Integer result = operate(10, x -> x * x);
        System.out.println(result);

        System.out.println(operate(10, num -> num * num + num));
    }

    /**
     * 对两个数进行运算
     */
    @Test
    public void test2() {
        Long ret = operate(10, 20, (n1, n2) -> (long) n1 * n2);
        System.out.println(ret);
        System.out.println(operate(100, 111, (n1, n2) -> (long) n1 * n2 * 1000));
    }

    public Integer operate(Integer num, Operation operation) {
        return operation.operate(num);
    }

    public Long operate(int n1, int n2, Operation2<Integer, Long> operation2) {
        return operation2.operate(n1, n2);
    }


}
