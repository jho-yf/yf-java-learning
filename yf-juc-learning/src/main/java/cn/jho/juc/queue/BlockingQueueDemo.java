package cn.jho.juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-30 9:14
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        // 创建阻塞队列
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

        // 添加元素，若超出长度抛出异常
        System.out.println(queue.add("a"));
        // 移除元素，若队列为空抛出异常
        System.out.println(queue.remove());

        // 添加元素，添加成功返回true，添加失败返回false
        System.out.println(queue.offer("b"));
        // 移除元素，移除成功返回元素，移除失败返回null
        System.out.println(queue.poll());

        // 添加元素，若超出长度则一直阻塞
        queue.put("c");
        // 移除元素，若队列为空则一直阻塞
        System.out.println(queue.take());

        // 添加元素，若超出长度则一直阻塞直到超时自动退出
        System.out.println(queue.offer("d", 3L, TimeUnit.SECONDS));
        // 移除元素，若队列为空则一直阻塞直到超时自动退出
        System.out.println(queue.poll(3L, TimeUnit.SECONDS));
    }

}
