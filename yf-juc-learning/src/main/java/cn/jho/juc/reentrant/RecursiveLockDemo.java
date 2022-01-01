package cn.jho.juc.reentrant;

/**
 * 可重入锁，递归演示
 *
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-01 16:07
 */
public class RecursiveLockDemo {

    public synchronized void add() {
        add();
    }

    public static void main(String[] args) {
        new RecursiveLockDemo().add();
    }

}
