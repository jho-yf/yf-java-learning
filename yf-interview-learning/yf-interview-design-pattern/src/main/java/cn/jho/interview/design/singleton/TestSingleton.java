package cn.jho.interview.design.singleton;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-05 22:42
 */
public class TestSingleton {

    public static void main(String[] args) {
        // 饿汉式不存在线程安全问题
        SingletonDemo1 instance1 = SingletonDemo1.INSTANCE;
        System.out.println(instance1);

        SingletonDemo2 instance2 = SingletonDemo2.INSTANCE;
        System.out.println(instance2);

        SingletonDemo3 instance3 = SingletonDemo3.INSTANCE;
        System.out.println(instance3);

        // 懒汉式 线程不安全
        SingletonDemo4 instance4 = SingletonDemo4.getInstance();
        System.out.println(instance4);
        System.out.println(instance4 == SingletonDemo4.getInstance());

        // 懒汉式 线程安全
        SingletonDemo5 instance5 = SingletonDemo5.getInstance();
        System.out.println(instance5 == SingletonDemo5.getInstance());

        // 懒汉式 线程安全
        SingletonDemo6 instance6 = SingletonDemo6.getInstance();
        System.out.println(instance6);
    }

}
