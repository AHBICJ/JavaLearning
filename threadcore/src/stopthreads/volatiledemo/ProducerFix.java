package stopthreads.volatiledemo;

import java.util.concurrent.BlockingQueue;

public class ProducerFix implements Runnable{
    BlockingQueue<Integer> storage;

    public ProducerFix(BlockingQueue<Integer> storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 100000 && !Thread.currentThread().isInterrupted()) {
                if (num % 100 == 0) {
                    // 会在这里因为阻塞队列而阻塞 且不被唤醒
                    storage.put(num);
                    System.out.println(num);
                }
                num++;
            }
        } catch (InterruptedException e) {
            System.out.println("producer interrupted");
        } finally {
            System.out.println("finished");
        }
    }
}
