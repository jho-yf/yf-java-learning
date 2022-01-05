package cn.jho.interview.design.singleton;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-05 22:42
 */
public class TestSingletonDemo {

    public static void main(String[] args) {
        // 饿汉式不存在线程安全问题
        SingletonDemo1 instance1 = SingletonDemo1.INSTANCE;
        System.out.println(instance1);

        SingletonDemo2 instance2 = SingletonDemo2.INSTANCE;
        System.out.println(instance2);

        SingletonDemo3 instance3 = SingletonDemo3.INSTANCE;
        System.out.println(instance3);
    }

}
