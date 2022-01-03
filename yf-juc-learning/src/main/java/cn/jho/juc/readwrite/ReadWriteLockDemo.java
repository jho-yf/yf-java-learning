package cn.jho.juc.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Cache {

    private volatile Map<String, Object> map = new HashMap<>();

    /** 读写锁 */
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        // 加写锁
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + ":正在写操作..." + key);
            TimeUnit.MILLISECONDS.sleep(300);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + ":完成写操作..." + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放写锁
            rwLock.writeLock().unlock();
        }
    }

    public Object get(String key) {
        // 加读锁
        rwLock.readLock().lock();
        Object result = null;
        try {
            System.out.println(Thread.currentThread().getName() + ":正在读取操作..." + key);
            TimeUnit.MILLISECONDS.sleep(300);
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + ":完成读取操作..." + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
        return result;
    }

}

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-02 11:13
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) throws InterruptedException {
        Cache cache = new Cache();
        for (int i = 1; i <= 3; i++) {
            final int num = i;
            new Thread(() -> {
                cache.put("key" + num, "value" + num);
            }, "ThreadPut" + i).start();
        }

        TimeUnit.SECONDS.sleep(1);

        for (int i = 1; i <= 3; i++) {
            final int num = i;
            new Thread(() -> {
                cache.get("key" + num);
            }, "ThreadPut" + i).start();
        }
    }

}
