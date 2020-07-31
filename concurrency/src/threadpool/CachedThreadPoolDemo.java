package threadpool;

import threadpool.FixedThreadPoolTest.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i=0;i<1000;i++){
            pool.execute(new Task());
        }
    }
}
