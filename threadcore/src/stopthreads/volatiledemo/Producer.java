package stopthreads.volatiledemo;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    public volatile boolean canceled = false;
    BlockingQueue<Integer> storage;

    public Producer(BlockingQueue<Integer> storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 100000 && !canceled) {
                if (num % 100 == 0) {
                    // 会在这里因为阻塞队列而阻塞 且不被唤醒
                    storage.put(num);
                    System.out.println(num);
                }
                num++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("finished");
        }
    }
}
