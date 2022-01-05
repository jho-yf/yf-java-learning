package cn.jho.interview.design.singleton;

/**
 * 懒汉式：线程安全
 *
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-05 23:42
 */
public class SingletonDemo5 {

    private static SingletonDemo5 instance;

    private SingletonDemo5() {

    }

    public static SingletonDemo5 getInstance() {
        synchronized (SingletonDemo5.class) {
            if (instance == null) {
                instance = new SingletonDemo5();
            }
        }

        return instance;
    }

}
