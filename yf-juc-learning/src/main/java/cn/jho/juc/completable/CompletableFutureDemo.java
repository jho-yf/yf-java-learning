package cn.jho.juc.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-30 21:19
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 异步调用，没有返回值
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "-runAsync()");
        });
        future1.get();

        // 异步调用，有返回值
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "-supplyAsync()");
            // int i = 1 / 0;
            return 1024;
        });
        future2.whenComplete((t, u) -> {
            // t 返回值
            // u 异常信息
            System.out.println("t=" + t);
            System.out.println("u=" + u);
        }).get();
    }

}
