package cn.jho.juc.lock;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 资源类，定义属性和操作方法
 *
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-30 23:40
 */
class LockTicket {

    private int number = 300;

    private final Random random = new Random();

    /** 可重入锁 */
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * 卖票
     */
    public void sale() {
        int i = random.nextInt(5);
        // 上锁
        lock.lock();
        try {
            if (i > number) {
                // 若剩余票数小于要出售的票，只能卖出剩余的票数
                i = number;
            }
            if (number > 0) {
                number -= i;
                System.out.println(Thread.currentThread().getName() + "卖出：" + i + "，剩下：" + number);
            }
        } finally {
            // 解锁
            lock.unlock();
        }

    }

}

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-30 23:39
 */
public class LockSaleTicket {

    public static void main(String[] args) {
        LockTicket lockTicket = new LockTicket();

        // 创建三个线程
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                lockTicket.sale();
            }
        }, "售票员A").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                lockTicket.sale();
            }
        }, "售票员B").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                lockTicket.sale();
            }
        }, "售票员C").start();

    }

}
