package cn.jho.juc.set;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * HashSet是线程不安全的
 *
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-01 14:54
 */
public class HashSetThreadDemo {

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

}
