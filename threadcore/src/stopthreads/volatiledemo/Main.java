package stopthreads.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> storage = new ArrayBlockingQueue<>(10);
//        Producer producer = new Producer(storage);
        ProducerFix producer = new ProducerFix(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);
        Consumer consumer = new Consumer(storage);
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();
        consumerThread.join();
        producerThread.interrupt();
        producerThread.join();
        System.out.println("main finished");


        Thread.interrupted();
        consumerThread.interrupt();
        consumerThread.interrupted();
    }
}
