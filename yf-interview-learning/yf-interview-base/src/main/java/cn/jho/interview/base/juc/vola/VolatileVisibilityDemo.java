package cn.jho.interview.base.juc.vola;

import java.util.concurrent.TimeUnit;

/**
 * 验证volatile关键字的可见性
 *
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-12 9:15
 */
public class VolatileVisibilityDemo {

    public static void main(String[] args) {
        Resource resource = new Resource();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.addNumTo10();
            System.out.println("【" + Thread.currentThread().getName() + "】操作完成");
        }, "线程A").start();
        while (resource.num == 0) {
            // 如果num值是0，main线程就在循环
        }
        System.out.println("【" + Thread.currentThread().getName() + "】num非0");
    }

}

/**
 * 资源类
 */
class Resource {

    /**
     * 使用volatile可以增强num变量在主内存和各内存的可见性，只要一个线程修改了主内存num值，其他线程可以马上收到通知，获取最新值
     * 若将此处的volatile关键字去掉发现，程序一致处于运行状态，即一直在main线程的while中循环等待
     */
    public volatile int num = 0;

    public void addNumTo10() {
        num += 10;
        System.out.println("【" + Thread.currentThread().getName() + "】num＋10");
    }

}