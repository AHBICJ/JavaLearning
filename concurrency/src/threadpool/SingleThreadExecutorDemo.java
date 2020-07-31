package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutorDemo {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        for (int i=0;i<1000;i++){
            pool.execute(new FixedThreadPoolTest.Task());
        }
    }
}
