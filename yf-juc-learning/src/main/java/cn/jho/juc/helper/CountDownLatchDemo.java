package cn.jho.juc.helper;

import java.util.concurrent.CountDownLatch;

/**
 * 演示{@link CountDownLatch}
 * 6个同学陆续离开之后，班长锁门
 *
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-02 8:59
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        // 创建CountDownLatch对象，设置初始值
        CountDownLatch countDownLatch = new CountDownLatch(6);

        new Thread(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "锁门...");
        }, "班长").start();

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "离开教室...");

                // 计数-1
                countDownLatch.countDown();
            }, i + "号同学").start();
        }

    }

}
