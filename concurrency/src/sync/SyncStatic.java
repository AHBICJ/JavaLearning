package sync;

import threadpool.FixedThreadPoolTest;

public class SyncStatic implements Runnable {

    static Class<?> clazz = SyncStatic.class;

    // synchronized on *this* by synchronized
//    public synchronized void method(){
    // synchronized on Class by static synchronized
    public static synchronized void method() {
//    public void method() { // synchronized on Class by clazz or *.class
//        synchronized (clazz){
//        synchronized(FixedThreadPoolTest.class){
        System.out.println("static " + Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("static finish " + Thread.currentThread().getName());
//        }
    }

    @Override
    public void run() {
        method();
    }

    public static void main(String[] args) {
        SyncStatic task1 = new SyncStatic();
        SyncStatic task2 = new SyncStatic();
        new Thread(task1, "obj_task1").start();
        new Thread(task2, "obj_task2").start();
        new Thread(task1, "obj_task1-copy").start();
    }
}
