package cn.jho.jvm.chapter02;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-30 22:05
 */
public class ClassInitTest {

    private static int num = 1;

    static {
        num = 2;
        number = 20;
    }

    // linking prepare：number = 0 --> initial: 20 --> 10
    private static int number = 10;

    public static void main(String[] args) {
        System.out.println(ClassInitTest.num);
        System.out.println(ClassInitTest.number);
    }

}
