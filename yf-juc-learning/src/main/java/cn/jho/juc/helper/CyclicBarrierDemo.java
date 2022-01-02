package cn.jho.juc.helper;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 演示{@link CyclicBarrier}
 * 集齐7颗龙珠召唤神龙
 *
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-02 9:24
 */
public class CyclicBarrierDemo {

    private static final int NUMBER = 7;

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, () -> {
            System.out.println("集齐7颗龙珠，召唤神龙...");
        });

        for (int i = 1; i <= 7; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "收集到...");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, i + "星龙珠").start();
        }

    }

}
