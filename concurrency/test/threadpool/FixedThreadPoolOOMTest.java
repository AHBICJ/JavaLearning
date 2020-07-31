package threadpool;

import org.junit.Test;

import static org.junit.Assert.*;

public class FixedThreadPoolOOMTest {

    // add "-Xmx32m -Xms32m" in VM options
    // Exception: java.lang.OutOfMemoryError thrown from
    // the UncaughtExceptionHandler in thread "main"
    @Test
    public void OOM(){
        for (int i=0;i<Integer.MAX_VALUE;i++){
            FixedThreadPoolOOM.execute(new Task());
        }
    }
    class Task implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(100000000000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}