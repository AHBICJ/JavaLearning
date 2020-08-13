package objectmethods;

public class PrintInTurn {
    private Integer num = 0;

    public static void main(String[] args) {
        PrintInTurn main = new PrintInTurn();
        main.realMain();
    }

    private void realMain() {
        Runnable runnable = () -> {
            synchronized (this) {
                while (num < 100) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " prints " + ++num);
                        notify();
                        if (num < 100) wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread p1 = new Thread(runnable);
        Thread p2 = new Thread(runnable);
        p1.start();
        p2.start();
        try {
            p1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            synchronized (p2) {
                p2.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
