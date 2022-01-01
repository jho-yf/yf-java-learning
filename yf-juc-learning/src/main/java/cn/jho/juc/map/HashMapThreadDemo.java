package cn.jho.juc.map;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * HashMap是线程不安全的
 *
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-01 15:04
 */
public class HashMapThreadDemo {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            String key = String.valueOf(i);
            new Thread(() -> {
                map.put(key, UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, key).start();
        }
    }

}
