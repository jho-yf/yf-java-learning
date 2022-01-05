package cn.jho.interview.design.singleton;

/**
 * 懒汉式：静态内部类
 * 静态内部类不会随着外部类的加载和初始化而初始化，他需要单独去加载和初始化
 * 由于实例是在内部类加载和初始化时创建的，因此式线程安全的
 *
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-06 0:03
 */
public class SingletonDemo6 {

    private SingletonDemo6() {

    }

    private static class Inner {
        private static final SingletonDemo6 INSTANCE = new SingletonDemo6();
    }

    public static SingletonDemo6 getInstance() {
        return Inner.INSTANCE;
    }

}
