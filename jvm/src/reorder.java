public class reorder {
    static int a = 1, b = 0, x = 0, y = 0;


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            a = b = x = y = 0;
            Thread t1 = new Thread(() -> {
                a = 1;
                x = b;
            });
            Thread t2 = new Thread(() -> {
                b = 2;
                y = a;
            });
            t2.start();
            t1.start();
            t1.join();
            t2.join();
            System.out.println("x=" + x + ", y=" + y);
        }
    }
}
