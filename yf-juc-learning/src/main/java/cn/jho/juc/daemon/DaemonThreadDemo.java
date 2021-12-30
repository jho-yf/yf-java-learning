package cn.jho.juc.daemon;

/**
 * 若存在任何一个用户线程未结束，JVM存活。
 * 若只剩下守护线程未结束，JVM结束。
 *
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-28 23:54
 */
public class DaemonThreadDemo {

    public static void main(String[] args) {
        Thread myThread = new Thread(() -> {
            // Thread.currentThread().isDaemon() 判断当前线程是否是守护线程。true：守护线程 false：用户线程
            System.out.println(Thread.currentThread().getName() + "::" + Thread.currentThread().isDaemon());
            while (true) {

            }
        }, "myThread");

        // 设置守护线程
        // myThread.setDaemon(true);

        myThread.start();
        System.out.println(Thread.currentThread().getName() + "结束...");
    }

}
