package cn.jho.juc.lock;

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
