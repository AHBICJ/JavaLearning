package lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StateDemo {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Thread t1 = new Thread(()->{
            lock.lock();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                lock.unlock();
            }
        });
        Thread t2 = new Thread(()->{
            lock.lock();
            lock.unlock();
        });
        t1.start();
        t2.start();
        try {
            System.out.println(t1.getState());
            System.out.println(t2.getState());
            Thread.sleep(1500);
            System.out.println(t1.getState());
            System.out.println(t2.getState());
            Thread.sleep(1500);
            System.out.println(t1.getState());
            System.out.println(t2.getState());
            Thread.sleep(1500);
            System.out.println(t1.getState());
            System.out.println(t2.getState());
            Thread.sleep(1500);
            System.out.println(t1.getState());
            System.out.println(t2.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
