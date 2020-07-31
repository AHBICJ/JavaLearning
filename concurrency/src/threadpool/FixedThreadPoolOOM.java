package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolOOM {
    private static ExecutorService pool = Executors.newFixedThreadPool(1);

    public static void execute(Runnable cmd) {
        pool.execute(cmd);
    }
}
