package java.lang;

/**
 * 双亲委派演示
 *
 * @author JHO xu-jihong@qq.com
 * @date 2022-03-01 23:42
 */
public class String {

    static {
        System.out.println("自定义String类静态代码块加载...");
    }

    public static void main(String[] args) {
        System.out.println("hello");
    }

}
