package cn.jho.juc.collections.list;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * list线程不安全
 *
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-01 13:42
 */
public class ArrayListThreadDemo {


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

}
