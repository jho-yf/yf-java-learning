package cn.jho.jdk8.lambda.interfaces;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-11-07 21:48
 */
public interface MyFun {

    // 默认方法

    /**
     * 默认方法
     *
     * @return {@link String}
     */
    default String getName() {
        return "MyFun";
    }

    /**
     * 静态方法
     */
    static void show() {
        System.out.println("static");
    }

}
