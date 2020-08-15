package lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        access();
    }

    public static void access(){
        lock.lock();
        try{
            System.out.println("do it");
            if (lock.getHoldCount()<5){
                access();
            }
        }finally {
            lock.unlock();
        }
    }

    public static void getHoldCount(){
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
        System.out.println(lock.getHoldCount());
    }
}
