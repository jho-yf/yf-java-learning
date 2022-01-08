package cn.jho.interview.base.oop;

/**
 * 类初始化和实例初始化示例
 *
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-06 7:09
 */
public class ClassInitDemo {

    public static void main(String[] args) {
        Son son1 = new Son();
        System.out.println();
        Son son2 = new Son();
    }

}

class Father {

    private int i = method();

    private static int j = staticMethod();

    static {
        System.out.println("(1)父类初始化静态代码块");
    }

    public Father() {
        System.out.println("(2)父类初始化构造函数");
    }

    {
        System.out.println("(3)父类初始化普通代码块");
    }

    public int method() {
        System.out.println("(4)父类初始化类变量");
        return 0;
    }

    public static int staticMethod() {
        System.out.println("(5)父类初始化类静态变量");
        return 0;
    }

}

class Son extends Father {

    private int i = method();

    private static int j = staticMethod();

    static {
        System.out.println("(6)子类初始化静态代码块");
    }

    public Son() {
        System.out.println("(7)子类初始化构造函数");
    }

    {
        System.out.println("(8)子类初始化普通代码块");
    }

    @Override
    public int method() {
        System.out.println("(9)子类初始化类变量");
        return 0;
    }

    public static int staticMethod() {
        System.out.println("(10)子类初始化类静态变量");
        return 0;
    }

}
