package cn.jho.juc.set;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * CopyOnWriteArraySet是线程安全的
 *
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-01 15:00
 */
public class CopyOnWriteArraySetThreadDemo {

    public static void main(String[] args) {
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

}
