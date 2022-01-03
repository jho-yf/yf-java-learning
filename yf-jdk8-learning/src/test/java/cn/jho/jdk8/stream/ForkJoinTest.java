package cn.jho.jdk8.stream;

import cn.jho.jdk8.lambda.ForkJoinCalculate;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-11-03 23:29
 */
public class ForkJoinTest {

    public static final long TOTAL = 100L;

    @Test
    public void test1() {
        Instant start = Instant.now();

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinCalculate task = new ForkJoinCalculate(0, TOTAL);
        Long sum = pool.invoke(task);
        System.out.println(sum);

        Instant end = Instant.now();
        System.out.println("Duration:" + Duration.between(start, end).toMillis());      // 21891    21080
    }

    @Test
    public void test2() {
        Instant start = Instant.now();

        long sum = 0L;

        for (long i = 0; i < TOTAL; i++) {
            sum += i;
        }

        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("Duration:" + Duration.between(start, end).toMillis());      // 33267       32924
    }

    @Test
    public void test3() {
        Instant start = Instant.now();

        long sum = LongStream.rangeClosed(0, TOTAL)
                // 并行流
                .parallel()
                .reduce(0, Long::sum);

        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("Duration:" + Duration.between(start, end).toMillis());      // 12684        13104
    }


}
