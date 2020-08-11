package createthreads;

// 用Thread方式实现线程
public class ThreadStyle {
    static class Task extends Thread {
        @Override
        public void run() {
            System.out.println("extends Thread");
        }
    }

    public static void main(String[] args) {
        Thread task = new Task();
        task.start();
    }
}

