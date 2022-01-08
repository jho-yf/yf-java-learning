package cn.jho.interview.base.recursion;

/**
 * 求n簿台阶，一次只能走1步或者2步，一共有几种走法
 *
 * 方法调用自身称为递归，利用变量的原值退出新值称为迭代
 *  递归
 *      优点：大问题转为小问题，可以减少代码量，代码可读性好
 *      缺点：效率低，递归容易浪费空间，若递归太深容易造成堆栈溢出
 *
 *  迭代
 *      优点：代码运行效率高，时间只随着循环次数增加，没有额外的空间开销
 *      缺点：代码不入递归简洁，可读性差
 *
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-08 13:59
 */
public class StepDemo {

    /**
     * 递归求法：n步台阶一共有多少走法
     * f(n) = f(n - 1) + f(n -2)
     *
     * @param n 台阶步数
     * @return 多少走法
     */
    public static int countStepRecursion(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        return countStepRecursion(n - 1) + countStepRecursion(n -2);
    }

    // public static int countStepLoop(int n) {
    //     if (n < 1) {
    //         return 0;
    //     }
    //     if (n == 1 || n == 2) {
    //         return n;
    //     }
    //     // 一级台阶的走法有1种
    //     int stepOne = 1;
    //     // 二级台阶的走法有2种
    //     int stepTwo = 2;
    //     int sum = 0;
    //
    //     for (int i = 3; i < n; i++) {
    //         sum = stepOne + stepTwo;
    //
    //     }
    //
    //     return sum
    // }

    public static void main(String[] args) {
        System.out.println(countStepRecursion(1));
        System.out.println(countStepRecursion(2));
        System.out.println(countStepRecursion(3));
        System.out.println(countStepRecursion(4));
        System.out.println(countStepRecursion(5));
    }


}
