package atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

public class LongAccumulatorDemo {
    public static void main(String[] args) {
//        LongAccumulator accumulator = new LongAccumulator(Long::sum, 0);
//        LongAccumulator accumulator = new LongAccumulator(Math::max, 0);
        LongAccumulator accumulator = new LongAccumulator((x,y)->x*y, 1);
        accumulator.accumulate(1);
//        System.out.println(accumulator.getThenReset());
        accumulator.accumulate(2);
        System.out.println(accumulator.getThenReset());
        ExecutorService pool = Executors.newFixedThreadPool(8);
        IntStream.range(1,10).forEach(i->pool.submit(()->accumulator.accumulate(i)));
        pool.shutdown();
        while (!pool.isTerminated()){}
        System.out.println(accumulator.getThenReset());
    }
}
