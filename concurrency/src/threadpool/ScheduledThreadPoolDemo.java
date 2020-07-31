package threadpool;

import threadpool.FixedThreadPoolTest.Task;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolDemo {
    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);
        pool.schedule(new Task(),5, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(new Task(),1,3,TimeUnit.SECONDS);
    }
}
