package stopthreads;

public class CatchWhenSleep {
    public static void main(String[] args) throws InterruptedException {
//        SleepOnly();
        SleepInLoop();
    }

    private static void SleepInLoop() throws InterruptedException {
        Thread t2 = new Thread(() -> {
            int num = 0;
            try {
                while (num <= 10000) {
                    if (num % 100 == 0) {
                        System.out.println(num);
                    }
                    num++;
                    // 可以只使用这个 而不判断IsInterrupted
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            };
        });
        t2.start();
        Thread.sleep(5000);
        t2.interrupt();
    }

    private static void SleepOnly() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            int num = 0;
            while (num <= 300 && !Thread.currentThread().isInterrupted()) {
                if (num % 100 == 0) {
                    System.out.println(num);
                }
                num++;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
//        Thread.sleep(500);
        t1.interrupt();
        t1.join();
    }
}
