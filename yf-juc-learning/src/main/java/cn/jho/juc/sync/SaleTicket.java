package cn.jho.juc.sync;

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
