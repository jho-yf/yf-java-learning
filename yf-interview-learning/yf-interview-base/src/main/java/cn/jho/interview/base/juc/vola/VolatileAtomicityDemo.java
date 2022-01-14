package cn.jho.interview.base.juc.vola;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-13 9:06
 */
public class VolatileAtomicityDemo {

    public static void main(String[] args) {
        MyData data = new MyData();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    data.incr();
                    data.incrAtom();
                }
            }, "第"  + i + "个线程").start();
        }
        // 等待所有线程计算完毕之后，打印最终num的值
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println("num计算结果：" + data.getNum());
        System.out.println("atomNum计算结果：" + data.getAtomicInteger());
    }

}

class MyData {

    private volatile int num = 0;

    private AtomicInteger atomicInteger = new AtomicInteger();

    public void incrAtom() {
        this.atomicInteger.getAndIncrement();
    }

    public void incr() {
        this.num++;
    }

    public int getNum() {
        return this.num;
    }

    public AtomicInteger getAtomicInteger() {
        return this.atomicInteger;
    }
}