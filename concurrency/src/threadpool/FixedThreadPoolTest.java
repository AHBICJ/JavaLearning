package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolTest {
    public static void main(String[] args) {
        new FixedThreadPoolTest().realMain();
    }

    public void realMain(){
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Task());
        }
    }

    // share with singleThreadExecutorDemo
    // where this class should be enclosed
    // so we use public static
    public static class Task implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }
}

