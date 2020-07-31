package threadpool;

public class EveryTaskOneThread {

    public void realMain() {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Task());
            thread.start();
        }
    }

    class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("Do Task");
        }
    }
}

