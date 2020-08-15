package lock.spin;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {
    private AtomicReference<Thread> sign = new AtomicReference<>();

    public void lock() {
        Thread cur = Thread.currentThread();
        while (!sign.compareAndSet(null, cur)) {
            System.out.println(Thread.currentThread().getName() + " trying");
        }
    }

    public void unlock() {
        Thread cur = Thread.currentThread();
        sign.compareAndSet(cur, null);
    }

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + "try to get spin lock");
            spinLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " got the spin lock");
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + " released the spin lock");
                spinLock.unlock();
            }
        };
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
    }

}
