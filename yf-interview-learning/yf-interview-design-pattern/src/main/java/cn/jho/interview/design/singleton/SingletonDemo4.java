package cn.jho.interview.design.singleton;

/**
 * 懒汉式
 *
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-05 23:42
 */
public class SingletonDemo4 {

    private static SingletonDemo4 instance;

    private SingletonDemo4() {

    }

    public static SingletonDemo4 getInstance() {
        if (instance == null) {
            instance = new SingletonDemo4();
        }
        return instance;
    }

}
