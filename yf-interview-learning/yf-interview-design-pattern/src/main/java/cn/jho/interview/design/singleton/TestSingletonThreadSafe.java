package cn.jho.interview.design.singleton;

import java.util.concurrent.*;

/**
 * 演示懒汉式单例模式线程不安全问题
 *
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-05 23:46
 */
public class TestSingletonThreadSafe {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<SingletonDemo4> callable1 = SingletonDemo4::getInstance;
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<SingletonDemo4> future1 = executorService.submit(callable1);
        Future<SingletonDemo4> future2 = executorService.submit(callable1);
        SingletonDemo4 s1 = future1.get();
        SingletonDemo4 s2 = future2.get();

        // 这里有可能打印出false
        System.out.println(s1 == s2);
        System.out.println(s1);
        System.out.println(s2);

        executorService.shutdown();

        executorService = Executors.newFixedThreadPool(2);
        Callable<SingletonDemo5> callable2 = SingletonDemo5::getInstance;
        Future<SingletonDemo5> future3 = executorService.submit(callable2);
        Future<SingletonDemo5> future4 = executorService.submit(callable2);
        SingletonDemo5 s3 = future3.get();
        SingletonDemo5 s4 = future4.get();
        // 这里不可能打印出false
        System.out.println(s3 == s4);
        System.out.println(s3);
        System.out.println(s4);
        executorService.shutdown();
    }

}
