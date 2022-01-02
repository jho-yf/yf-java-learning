package cn.jho.juc.collections.list;

import java.util.List;
import java.util.UUID;
import java.util.Vector;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-01 14:13
 */
public class VectorThreadDemo {

    public static void main(String[] args) {
        List<String> list = new Vector<>();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

}
