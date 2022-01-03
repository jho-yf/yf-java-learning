package cn.jho.juc.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 锁降级示例
 *
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-02 14:07
 */
public class ReadWriteLockDegradedDemo {

    public static void main(String[] args) {
        // 可重入读写锁对象
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        // 读锁
        ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
        // 写锁
        ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();

        // 获取写锁
        writeLock.lock();
        System.out.println("正在写入...");

        // 获取读锁
        readLock.lock();
        System.out.println("正在读取...");

        writeLock.unlock();
        System.out.println("写锁释放...");
        readLock.unlock();
        System.out.println("读锁释放...");
    }

}
