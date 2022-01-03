package cn.jho.jdk8.lambda;

import java.util.concurrent.RecursiveTask;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-11-03 23:18
 */
public class ForkJoinCalculate extends RecursiveTask<Long> {

    private static final long THRESHOLD = 10000;

    private long start;

    private long end;

    public ForkJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;

    }

    @Override
    protected Long compute() {
        long length = end - start;

        if (length <= THRESHOLD) {
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end) / 2;
            ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
            left.fork();

            ForkJoinCalculate right = new ForkJoinCalculate(middle + 1, end);
            right.fork();
            return left.join() + right.join();
        }
    }


}
