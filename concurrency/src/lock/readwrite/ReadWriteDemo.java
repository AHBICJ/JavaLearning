package lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteDemo {
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(false);
//    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    private void read() {
        System.out.println(Thread.currentThread().getName() + " try to get the read lock");
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " got the read lock, is reading");
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " released the read lock");
            readLock.unlock();
        }
    }

    private void write() {
        System.out.println(Thread.currentThread().getName() + " try to get the write lock");
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " got the write lock, is writing");
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " released the write lock");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteDemo rwd = new ReadWriteDemo();
        rwd.anotherMain();
    }

    private void realMain() {
        new Thread(this::read, "Thread1_read").start();
        new Thread(this::read, "Thread2_read").start();
        new Thread(this::write, "Thread3_write").start();
        new Thread(this::write, "Thread4_write").start();
    }
    private void anotherMain(){
        new Thread(this::write, "Thread1_write").start();
        new Thread(this::read, "Thread2_read").start();
        new Thread(this::read, "Thread3_read").start();
        new Thread(this::write, "Thread4_write").start();
        new Thread(this::read, "Thread5_read").start();
        new Thread(()->{
            for (int i=0;i<1000;i++){
                new Thread(this::read,"thread_"+i+"_barge_read").start();
            }
        }).start();

        /*
Thread4_write released the write lock
thread_306_barge_read try to get the read lock
thread_307_barge_read try to get the read lock
Thread3_read got the read lock, is reading
thread_306_barge_read got the read lock, is reading
thread_307_barge_read got the read lock, is reading
Thread5_read got the read lock, is reading
         */
    }
}
