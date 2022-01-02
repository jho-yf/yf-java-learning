package cn.jho.juc.collections.map;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-01 15:10
 */
public class ConcurrentHashMapThreadDemo {

    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 100; i++) {
            String key = String.valueOf(i);
            new Thread(() -> {
                map.put(key, UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, key).start();
        }
    }

}
