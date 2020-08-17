package atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicArrayDemo {
    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerArray array = new AtomicIntegerArray(1000);
        Runnable r1 = () -> {
            for (int i = 0; i < array.length(); i++) {
                array.getAndIncrement(i);
            }
        };
        Runnable r2 = () -> {
            for (int i = 0; i < array.length(); i++) {
                array.getAndDecrement(i);
            }
        };
        for (int i = 0; i < 100; i++) {
            new Thread(r1).start();
            new Thread(r2).start();
        }
        Thread.sleep(10000);
        for (int i = 0; i < array.length(); i++) {
            if (array.get(i) != 0) {
                System.out.println("find error at " + i + ":" + array.get(i));
            }
            System.out.print(array.get(i)+"*");
        }
    }
}
