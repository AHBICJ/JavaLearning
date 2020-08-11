package stopthreads.volatiledemo;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    public volatile boolean canceled = false;
    BlockingQueue<Integer> storage;

    public Consumer(BlockingQueue<Integer> storage) {
        this.storage = storage;
    }

    public boolean needMoreNums() {
        return !(Math.random() > 0.8);
    }

    @Override
    public void run() {
        try {
            while (needMoreNums()) {
                System.out.println(storage.take() + "consumed");
                Thread.sleep(1000);
            }
            System.out.println("consumer finished");
        } catch (InterruptedException e) {
            System.out.println("consumer Interrupted");
        }
    }
}