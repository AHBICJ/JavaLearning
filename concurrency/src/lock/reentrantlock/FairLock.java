package lock.reentrantlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairLock {

    static class PrintQueue {
        private Lock lock = new ReentrantLock(false);

        public void print(Object doc) {
            // 模拟打印两遍
            for (int i = 0; i < 2; i++) {
                lock.lock();
                try {
                    int duration = new Random().nextInt(4) + 1;
                    System.out.println(Thread.currentThread().getName() + " is printing, please wait " + duration + " seconds");
                    Thread.sleep(duration * 1000);
                } catch (InterruptedException e) {
                    System.out.println("interrupted while printing");
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class Task implements Runnable {
        PrintQueue printQueue;

        public Task(PrintQueue printQueue) {
            this.printQueue = printQueue;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " start print");
            printQueue.print(new Object());
            System.out.println(Thread.currentThread().getName() + " print finished");
        }
    }

    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        Thread[] thread = new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Task(printQueue));
        }
        for (int i = 0; i < 10; i++) {
            try {
                thread[i].start();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
