package cn.jho.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 资源对象
 */
class ShareResource {

    public enum FLAG {
        /**
         * 标记A线程
         */
        A,
        /**
         * 标记B线程
         */
        B,
        /**
         * 标记C线程
         */
        C
    }

    private FLAG flag = FLAG.A;

    private Lock lock = new ReentrantLock();

    private Condition c1 = lock.newCondition();

    private Condition c2 = lock.newCondition();

    private Condition c3 = lock.newCondition();

    public void print5(int loop) throws InterruptedException {
        // 加锁
        lock.lock();
        try {
            // 判断
            while (flag != FLAG.A) {
                c1.await();
            }
            // 操作
            for (int i = 1; i <= 5; i++) {
                System.out.println("【第" + loop + "轮】" + Thread.currentThread().getName() + "::" + i);
            }
            this.flag = FLAG.B;
            c2.signal();
        } finally {
            lock.unlock();
        }
    }

    public void print10(int loop) throws InterruptedException {
        // 加锁
        lock.lock();
        try {
            // 判断
            while (flag != FLAG.B) {
                c2.await();
            }
            // 操作
            for (int i = 1; i <= 10; i++) {
                System.out.println("【第" + loop + "轮】" + Thread.currentThread().getName() + "::" + i);
            }
            this.flag = FLAG.C;
            c3.signal();
        } finally {
            lock.unlock();
        }
    }

    public void print15(int loop) throws InterruptedException {
        // 加锁
        lock.lock();
        try {
            // 判断
            while (flag != FLAG.C) {
                c3.await();
            }
            // 操作
            for (int i = 1; i <= 15; i++) {
                System.out.println("【第" + loop + "轮】" + Thread.currentThread().getName() + "::" + i);
            }
            this.flag = FLAG.A;
            c1.signal();
        } finally {
            lock.unlock();
        }
    }

}

/**
 * 线程间定制化通信，启动三个线程，按照以下要求：
 *
 *      线程A打印5次，线程B打印10次，线程C打印15次
 *
 *      循环10轮
 *
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-31 22:06
 */
public class LockThreadCustomNotifyDemo {

    public static void main(String[] args) {
        ShareResource resource = new ShareResource();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    resource.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程A").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    resource.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程B").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    resource.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程C").start();

    }

}
