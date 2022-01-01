package cn.jho.juc.reentrant;

/**
 * 可重入锁
 *
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-01 16:01
 */
public class SyncLockDemo {

    public static void main(String[] args) {
        Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + ":: 外层");
                synchronized (o) {
                    System.out.println(Thread.currentThread().getName() + ":: 中层");
                    synchronized (o) {
                        System.out.println(Thread.currentThread().getName() + ":: 内层");
                    }
                }
            }
        }, "t1").start();
    }

}
