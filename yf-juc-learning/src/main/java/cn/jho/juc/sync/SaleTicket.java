package cn.jho.juc.sync;

import java.util.Random;

/**
 * 资源类，定义属性和操作方法
 *
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-30 19:46
 */
class Ticket {

    private int number = 300;

    private final Random random = new Random();

    /**
     * 卖票
     */
    public synchronized void sale() {
        int i = random.nextInt(5);
        if (i > number) {
            // 若剩余票数小于要出售的票，只能卖出剩余的票数
            i = number;
        }
        if (number > 0) {
            number -= i;
            System.out.println(Thread.currentThread().getName() + "卖出：" + i + "，剩下：" + number);
        }
    }

}

/**
 * 创建多个线程，调用资源类的操作方法
 *
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-30 19:43
 */
public class SaleTicket {

    public static void main(String[] args) {
        // 创建资源对象
        Ticket ticket = new Ticket();

        // 创建三个线程
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "售票员A").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "售票员B").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "售票员C").start();
    }

}
