package cn.jho.juc.sync;


/**
 * 创建资源类，定义属性和操作方法
 */
class Resource {

    /** 初始化值 */
    private int number = 0;

    /**
     * +1 操作
     */
    public synchronized void incr() throws InterruptedException {
        // 判断
        // if (number != 0) {
        while (number != 0) {
            this.wait();        // 在哪里睡，就在哪里醒
        }
        // 干活
        number++;
        System.out.println(Thread.currentThread().getName() + "::" + number);
        // 通知
        this.notifyAll();
    }

    /**
     * -1 操作
     */
    public synchronized void decr() throws InterruptedException {
        // 判断
        // if (number != 1) {
        while (number != 1) {
            this.wait();
        }
        // 干活
        number--;
        System.out.println(Thread.currentThread().getName() + "::" + number);
        // 通知
        this.notifyAll();
    }


}

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-31 6:58
 */
public class SyncThreadNotifyDemo {

    public static void main(String[] args) {
        // 创建多个线程，调用资源类的操作方法
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


