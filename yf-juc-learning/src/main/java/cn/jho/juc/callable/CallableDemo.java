package cn.jho.juc.callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-01 17:20
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + "::Callable调用...");
            return "Callable返回值...";
        });
        new Thread(futureTask, "futureTask").start();

        while (!futureTask.isDone()) {
            System.out.println("wait...");
        }

        System.out.println(futureTask.get());
    }

}


