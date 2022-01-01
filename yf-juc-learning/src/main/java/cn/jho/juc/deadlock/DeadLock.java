package cn.jho.juc.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * 死锁示例
 *
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-01 16:46
 */
public class DeadLock {

    static Object lock1 = new Object();
    static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock1) {
                System.out.println("【" + Thread.currentThread().getName() + "】持有lock1，试图获取lock2");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("【" + Thread.currentThread().getName() + "】获取lock2");
                }
            }
        }, "线程A").start();

        new Thread(() -> {
            synchronized (lock2) {
                System.out.println("【" + Thread.currentThread().getName() + "】持有lock2，试图获取lock1");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("【" + Thread.currentThread().getName() + "】获取lock1");
                }
            }
        }, "线程B").start();
    }

}
