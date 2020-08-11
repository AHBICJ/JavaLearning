package stopthreads;

public class UseInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            int num = 0;
            // 没有存在阻塞的情况就简单使用这个判断
            while (!Thread.currentThread().isInterrupted()
                    && num < Integer.MAX_VALUE / 2) {
                if (num % 10000 == 0) System.out.println(num);
                num++;
            }
            System.out.println("finish!");
        });
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }
}
