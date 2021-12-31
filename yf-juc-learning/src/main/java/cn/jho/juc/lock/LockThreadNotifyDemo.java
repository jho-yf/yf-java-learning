package cn.jho.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Resource {

    /** 初始化值 */
    private int number = 0;

    private final Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    /**
     * +1操作
     */
    public void incr() throws InterruptedException {
        // 加锁
        lock.lock();
        try {
            // 判断
            while (number != 0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "::" + number);
            // 通知
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    /**
     * -1操作
     */
    public void decr() throws InterruptedException {
        // 加锁
        lock.lock();
        try {
            // 判断
            while (number != 1) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "::" + number);
            // 通知
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

}


/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-31 20:31
 */
public class LockThreadNotifyDemo {

    public static void main(String[] args) {
        Resource resource = new Resource();
        new Thread(() -> {
            for (int i = 0; i < 9; i++) {
                try {
                    resource.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程A").start();

        new Thread(() -> {
            for (int i = 0; i < 9; i++) {
                try {
                    resource.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程B").start();

        new Thread(() -> {
            for (int i = 0; i < 9; i++) {
                try {
                    resource.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程C").start();

        new Thread(() -> {
            for (int i = 0; i < 9; i++) {
                try {
                    resource.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程D").start();
    }

}
