package atomic;

import java.security.Provider;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderDemo {
    public static void main(String[] args) throws InterruptedException {
        useLongAdder();
    }

    private static void useLongAdder() {
        LongAdder counter = new LongAdder();
        ExecutorService pool = Executors.newFixedThreadPool(20);
        long start = System.currentTimeMillis();
        for (int i=0;i<10000;i++){
            pool.submit(()->{
                for (int j=0;j<10000;j++){
                    counter.increment();
                }
            });
        }
        pool.shutdown();
        while (!pool.isTerminated()){
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        System.out.println(counter.sum());
    }

    private static void useAtomicLong() {
        AtomicLong counter = new AtomicLong(0);
        ExecutorService pool = Executors.newFixedThreadPool(20);
        long start = System.currentTimeMillis();
        for (int i=0;i<10000;i++){
            pool.submit(()->{
               for (int j=0;j<10000;j++){
                   counter.getAndIncrement();
               }
            });
        }
        pool.shutdown();
        while (!pool.isTerminated()){
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        System.out.println(counter.get());
    }
}
