package cn.jho.juc.helper;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 演示{@link Semaphore}
 * 6辆汽车，停3个车位
 *
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-02 9:41
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        // 创建Semaphore，设置许可证数量为3
        Semaphore semaphore = new Semaphore(3);

        // 模拟6辆汽车
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    // 抢占
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到了车位>>>");
                    // 设置随机停车时间
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println(Thread.currentThread().getName() + "离开了车位《《《");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放
                    semaphore.release();
                }
            }, i + "车").start();
        }

    }

}
