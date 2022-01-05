package cn.jho.interview.design.singleton;

/**
 * 饿汉式：直接实例化
 *      1. 构造器私有化
 *      2. 自行创建，使用静态变量保存
 *      3. 向外提供实例
 *      4. 使用final修饰，强调这个一个单例
 *
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-05 22:32
 */
public class SingletonDemo1 {

    public static final SingletonDemo1 INSTANCE = new SingletonDemo1();

    private SingletonDemo1() {

    }

}
