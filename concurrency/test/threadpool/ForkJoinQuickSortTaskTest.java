package threadpool;

import org.junit.Test;

import java.sql.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class ForkJoinQuickSortTaskTest {

    private long[] getArray() {
        Random random = new Random(47);
        return random.longs(100000000).toArray();
    }

    @Test
    public void test() throws InterruptedException {
        long[] array = getArray();
        System.out.println(System.currentTimeMillis());
        ForkJoinPool pool = new ForkJoinPool();
        long a = System.currentTimeMillis();
        System.out.println(a);
        ForkJoinTask sort = new ForkJoinQuickSortTask(array);
        pool.submit(sort);
        pool.shutdown();
        pool.awaitTermination(30, TimeUnit.SECONDS);
        long b = System.currentTimeMillis();
        System.out.println(b);
        System.out.println(b-a);
        System.out.println(sort.isDone());
//        for (long a : array) System.out.print(a + " ");
    }

    @Test
    public void testNormal() {
        long[] array = getArray();
        long a = System.currentTimeMillis();
        System.out.println(a);
        Arrays.sort(array);
//        for (long a : array) System.out.print(a + " ");
        long b = System.currentTimeMillis();
        System.out.println(b);
        System.out.println(b-a);
    }

}