package cn.jho.juc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 计算从1加到100的值
 *
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-30 21:03
 */
public class ForkJoinDemo {

    public static void main(String[] args) {
        // 创建任务对象
        MyTask task = new MyTask(0, 100);
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = pool.submit(task);
        try {
            System.out.println(forkJoinTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }

}

class MyTask extends RecursiveTask<Integer> {

    /** 拆分差值 */
    private static final Integer VALUE = 10;

    /** 拆分开始值 */
    private int begin;

    /** 拆分结束值 */
    private int end;

    /** 返回结果 */
    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    /**
     * 拆分和合并的过程
     * @return 结果
     */
    @Override
    protected Integer compute() {
        if (end - begin <= VALUE) {
            // 相加
            for (int i = begin; i <= end; i++) {
                result += i;
            }
        } else {
            // 进一步拆分
            int middle = (begin + end) / 2;
            MyTask task01 = new MyTask(begin, middle);
            MyTask task02 = new MyTask(middle + 1, end);
            // 拆分
            task01.fork();
            task02.fork();
            // 合并结果
            result = task01.join() + task02.join();
        }
        return result;
    }
}
